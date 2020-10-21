package com.example.closeclothes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyFragment extends Fragment implements ItemsAdapter.itemClickListener{

    String category;
    TextView tv;
    MyViewModel vm;
    ProgressBar pb;
    ImageView img;
    RecyclerView rec;
    changeListener listener;

    public MyFragment(String category, changeListener listener) {
        this.category = category;
        this.listener= listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my, container, false);
        pb = view.findViewById(R.id.pb);
        rec = view.findViewById(R.id.rec);

        if(category.equals("INFO")){
            pb.setVisibility(View.GONE);
            Toast.makeText(getContext(), "INFO PAGE !!!", Toast.LENGTH_SHORT).show();
        }
        else{
            vm = ViewModelProviders.of(this).get(MyViewModel.class);
            rec.setVisibility(View.GONE);
            if(category.equals("HOME"))
                vm.getItems();
            else if(category.equals("CART"))
                vm.getCard();
            else if(category.equals("MEN"))
                vm.getMenItems();
            else if(category.equals("WOMEN"))
                vm.getWomenItems();
            else if(category.equals("KIDS"))
                vm.getKidsItems();
            else if(category.equals("BABY"))
                vm.getBabyItems();

            ItemsAdapter adapter = new ItemsAdapter();
            vm.getMutableLiveData().observe(this, new Observer<List<item>>() {
                @Override
                public void onChanged(List<item> items) {
                    pb.setVisibility(View.GONE);
                    rec.setVisibility(View.VISIBLE);
                    adapter.setItems(items);
                    adapter.setListener(MyFragment.this);
                    rec.setAdapter(adapter);
                    rec.setLayoutManager(new GridLayoutManager(getContext(),2));
                    rec.setHasFixedSize(true);
                }
            });
        }


        return view;
    }

    @Override
    public void onItemClick(item it) {
        listener.onClick(it,category);
    }
}