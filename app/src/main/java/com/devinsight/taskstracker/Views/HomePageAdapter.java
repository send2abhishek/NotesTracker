package com.devinsight.taskstracker.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.devinsight.taskstracker.Activities.BaseActivity;
import com.devinsight.taskstracker.ApiRequests.Models.HomeRouteRequestResponse;
import com.devinsight.taskstracker.R;
import java.util.ArrayList;
import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageViewHolder> {

    private final LayoutInflater layoutInflater;
    private final BaseActivity activity;
    private final ArrayList<HomeRouteRequestResponse> dataList;

    public HomePageAdapter(BaseActivity activity) {
        this.activity = activity;
        layoutInflater=activity.getLayoutInflater();
        dataList=new ArrayList<>();
    }

    public void AddDataToView(List<HomeRouteRequestResponse> data){

        dataList.addAll(data);
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.activity_home_view_holder,parent,false);
        return new HomePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder holder, int position) {

        HomeRouteRequestResponse response=dataList.get(position);
        holder.populate(response);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
