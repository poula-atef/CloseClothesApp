package com.example.closeclothes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<item> items;
    Context context;
    private itemClickListener listener;

    @NonNull
    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        Glide.with(context).load(items.get(position).getImage()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        if(items ==null)
            return 0;
        return items.size();
    }

    public List<item> getItems() {
        return items;
    }

    public void setItems(List<item> items) {
        this.items = items;
    }

    interface itemClickListener{
        void onItemClick(item it);
    }

    public itemClickListener getListener() {
        return listener;
    }

    public void setListener(itemClickListener listener) {
        this.listener = listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView img;
        TextView name;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.itemImg);
            name = itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(items.get(getLayoutPosition()));
        }
    }
}
