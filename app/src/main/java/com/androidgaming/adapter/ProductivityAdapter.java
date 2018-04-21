package com.androidgaming.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidgaming.R;
import com.androidgaming.model.HostProductivity;

import java.util.ArrayList;

/**
 * Created by Manish Patidar on 21-04-2018.
 */
public class ProductivityAdapter extends RecyclerView.Adapter<ProductivityAdapter.ViewHolder> {

    private Context context;


    private ArrayList<HostProductivity> listOfTiming;


    public ProductivityAdapter(Context context, ArrayList<HostProductivity> listOfTiming) {
        this.listOfTiming = listOfTiming;
        this.context = context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_productivity, viewGroup, false);
        return new ProductivityAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductivityAdapter.ViewHolder holder, int position) {

        holder.productivityCount.setText(listOfTiming.get(position).getCount());
        holder.productivityName.setText(listOfTiming.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listOfTiming.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productivityName;
        TextView productivityCount;

        public ViewHolder(View view) {
            super(view);
            productivityCount = (TextView) view.findViewById(R.id.productivityCount);
            productivityName = (TextView) view.findViewById(R.id.productivityName);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
