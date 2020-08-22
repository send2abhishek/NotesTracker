package com.devinsight.taskstracker.Views;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devinsight.taskstracker.ApiRequests.Models.HomeRouteRequestResponse;
import com.devinsight.taskstracker.R;

public class HomePageViewHolder extends RecyclerView.ViewHolder {

    private TextView email;
    private TextView name;
    private TextView country;
    public HomePageViewHolder(@NonNull View itemView) {
        super(itemView);
        email=itemView.findViewById(R.id.home_activity_view_email);
        name=itemView.findViewById(R.id.home_activity_view_name);
        country=itemView.findViewById(R.id.home_activity_view_country);
    }

    public void populate(HomeRouteRequestResponse response){

        email.setText(response.getEmail());
        name.setText(response.getName());
        country.setText(response.getCountry());
    }

}
