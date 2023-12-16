package com.example.thedecors.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thedecors.Interfaces.OnItemClickListener;
import com.example.thedecors.Models.ItemModel;
import com.example.thedecors.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ItemModel> itemList;
    private OnItemClickListener onItemClickListener;

    public ItemAdapter(List<ItemModel> itemList,OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = itemList.get(position);
        holder.tv1.setText(item.getItemName());

        // Load and display the image using Glide (or your preferred image loading library)
        Picasso.get()
                .load(item.getItemId())
                .placeholder(R.drawable.baseline_nothing_24) // Placeholder image while loading
                .error(R.drawable.baseline_error_outline_24) // Error image if loading fails
                .into(holder.iv1);

        holder.ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getBindingAdapterPosition(),item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv1;
        private ImageView iv1;
        private LinearLayout ll1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            iv1 = itemView.findViewById(R.id.iv1);
            ll1 = itemView.findViewById(R.id.ll1);
        }
    }
}
