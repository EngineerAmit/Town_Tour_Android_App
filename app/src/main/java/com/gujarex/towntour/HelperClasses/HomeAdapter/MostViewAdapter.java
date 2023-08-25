package com.gujarex.towntour.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gujarex.towntour.R;

import java.util.ArrayList;

public class MostViewAdapter extends RecyclerView.Adapter<MostViewAdapter.MostViewHolder> {

    ArrayList<MostViewHelper> MostViewLocation;

    public MostViewAdapter(ArrayList<MostViewHelper> MostViewLocation) {
        this.MostViewLocation = MostViewLocation;
    }

    @NonNull
    @Override
    public MostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design, parent, false);
        MostViewHolder mostViewHolder = new MostViewAdapter.MostViewHolder(view);
        return mostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewHolder holder, int position) {
        MostViewHelper mostViewHelper = MostViewLocation.get(position);

        holder.image.setImageResource(mostViewHelper.getImage());
        holder.title.setText(mostViewHelper.getTitle());
        holder.desc.setText(mostViewHelper.getDescription());

    }

    @Override
    public int getItemCount() {

        return MostViewLocation.size();
    }

    public static class MostViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title, desc;


        public MostViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.mv_image);
            title = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
        }
    }
}

