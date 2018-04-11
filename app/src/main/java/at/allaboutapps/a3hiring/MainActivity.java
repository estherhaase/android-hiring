package at.allaboutapps.a3hiring;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import at.allaboutapps.a3hiring.api.ClubService;
import at.allaboutapps.a3hiring.api.models.Club;
import at.allaboutapps.a3hiring.api.models.SortByValue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
  RecyclerView recyclerView;
  private CustomAdapter adapter;
  ArrayList<Club> clubs;
  private final String LOG_TAG = this.getClass().toString();
  private static final String BASE_URL = "https://public.allaboutapps.at/hiring/";



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 1, false);
    recyclerView.setLayoutManager(linearLayoutManager);


    if(savedInstanceState == null){
      if(isConnected()){
        clubs = new ArrayList<>();
        requestClubs();
      }else{
        String error = getResources().getString(R.string.connection_error);
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
      }

    }else{
      clubs =  savedInstanceState.getParcelableArrayList("clubList");
      Collections.sort(clubs, new SortByValue());
      adapter = new CustomAdapter(MainActivity.this, clubs);
      recyclerView.setAdapter(adapter);

    }

  }

  public void requestClubs(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClubService clubService = retrofit.create(ClubService.class);
    Call<ArrayList<Club>> call = clubService.getClubs();

    call.enqueue(new Callback<ArrayList<Club>>() {
      @Override
      public void onResponse(Call<ArrayList<Club>> call, Response<ArrayList<Club>> response) {
        Log.d(LOG_TAG, "onResponse: " + response.toString());
        Log.d(LOG_TAG, "onResponse: " + response.body().toString());

        clubs = response.body();
        adapter = new CustomAdapter(MainActivity.this, clubs);
        recyclerView.setAdapter(adapter);

      }

      @Override
      public void onFailure(Call<ArrayList<Club>> call, Throwable t) {
        Log.e(LOG_TAG, "onFailure: " + t.getMessage());
      }
    });
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putParcelableArrayList("clubList", clubs);
    super.onSaveInstanceState(outState);
  }

  private boolean isConnected() {
    ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    assert cm != null;
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    return netInfo != null && netInfo.isConnectedOrConnecting();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.menuSort){

     Collections.sort(clubs, new SortByValue());
    }

    return super.onOptionsItemSelected(item);
  }
}
