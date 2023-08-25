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

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    ArrayList<CatHelperClass> CatViewLocation;

    public CatAdapter(ArrayList<CatHelperClass> CatViewLocation) {
        this.CatViewLocation = CatViewLocation;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        CatViewHolder catViewHolder = new CatAdapter.CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatAdapter.CatViewHolder holder, int position) {
        CatHelperClass catHelperClass = CatViewLocation.get(position);

        holder.image.setImageResource(catHelperClass.getImage());
        holder.title.setText(catHelperClass.getTitle());
    }

    @Override
    public int getItemCount() {

        return CatViewLocation.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;


        public CatViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.cat_image);
            title = itemView.findViewById(R.id.cat_recycler_title);
        }
    }
}

