package at.allaboutapps.a3hiring.api;


import java.util.ArrayList;

import at.allaboutapps.a3hiring.api.models.Club;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ClubService {

    @GET("clubs.json")
    Call<ArrayList<Club>> getClubs();



}