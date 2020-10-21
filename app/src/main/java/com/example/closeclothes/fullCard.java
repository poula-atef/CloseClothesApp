package com.example.closeclothes;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class fullCard extends Fragment{

    item it;
    String type;
    ImageView img;
    TextView name,sizes,price,buy,addToCard,offer,remove;
    RatingBar rb;
    LinearLayout removeLayout;
    changeListener listener;
    ConstraintLayout defaultLayout, infoLayout;


    boolean appInfo=false;

    public fullCard(){
        appInfo = true;
    }

    public fullCard(item it, String type, changeListener listener) {
        this.it = it;
        this.type = type;
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_full_card, container, false);
        defaultLayout = view.findViewById(R.id.default_layout);
        infoLayout = view.findViewById(R.id.info_layout);

        if(appInfo){
            defaultLayout.setVisibility(View.GONE);
            infoLayout.setVisibility(View.VISIBLE);
        }
        else{
            defaultLayout.setVisibility(View.VISIBLE);
            infoLayout.setVisibility(View.GONE);
            img = view.findViewById(R.id.itemImg);
            name = view.findViewById(R.id.name);
            sizes = view.findViewById(R.id.sizes);
            price = view.findViewById(R.id.price);
            buy = view.findViewById(R.id.buy);
            offer = view.findViewById(R.id.offer);
            addToCard = view.findViewById(R.id.add_to_card);
            remove = view.findViewById(R.id.remove);
            rb = view.findViewById(R.id.ratingBar);
            removeLayout = view.findViewById(R.id.remove_from_card_layout);

            name.setSelected(true);
            Glide.with(getContext()).load(it.getImage()).into(img);
            name.setText(it.getName());
            sizes.setText(it.getSizes());
            float rating = Float.parseFloat(it.getRating());
            rb.setRating(rating);
            price.setText(it.getPrice() + " EGP");
            buy.setText(it.getBuy());
            if(it.getOffer() != 0){
                offer.setVisibility(View.VISIBLE);
                offer.setText(String.valueOf(it.getOffer()) + "%");
            }

            if(type.equals("CART")) {
                addToCard.setVisibility(View.GONE);
                removeLayout.setVisibility(View.VISIBLE);
            }
            else{
                addToCard.setVisibility(View.VISIBLE);
                removeLayout.setVisibility(View.GONE);
            }

            MyViewModel vm = ViewModelProviders.of(fullCard.this).get(MyViewModel.class);
            addToCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.addCard(it);
                    Toast.makeText(getContext(), "Added!!", Toast.LENGTH_SHORT).show();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.removeCard(it.get_id());
                    listener.onItemRemoved();
                }
            });
        }

        return view;
    }




}