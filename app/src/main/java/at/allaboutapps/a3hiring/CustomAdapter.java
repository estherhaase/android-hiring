package at.allaboutapps.a3hiring;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import at.allaboutapps.a3hiring.api.models.Club;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{



    private Context context;
    private List<Club> data;

    CustomAdapter(Context context, List<Club> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_club, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //Method to manipulate the views
        String value = context.getResources().getString(R.string.value) + " " + data.get(position).getValue();
        holder.clubName.setText(data.get(position).getName());
        holder.clubCountry.setText(data.get(position).getCountry());
        holder.clubInfos.setText(value);

        Picasso.get().load(data.get(holder.getAdapterPosition()).getImage())
                .placeholder(R.drawable.club_placeholder)
                .resize(200, 200)
                .into(holder.clubImage, new Callback() {

                    @Override
                    public void onSuccess() {
                        Picasso.get().load(data.get(holder.getAdapterPosition()).getImage()).into(holder.clubImage);
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(R.drawable.club_placeholder).into(holder.clubImage);
                    }

                });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView clubImage;
        TextView clubName;
        TextView clubCountry;
        TextView clubInfos;


        ViewHolder(View itemView) {

            super(itemView);
            clubInfos = itemView.findViewById(R.id.club_infos);
            clubImage = itemView.findViewById(R.id.club_image);
            clubName = itemView.findViewById(R.id.club_name);
            clubCountry = itemView.findViewById(R.id.club_country);
        }
    }


}
