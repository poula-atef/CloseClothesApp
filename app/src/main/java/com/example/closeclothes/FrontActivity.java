package com.example.closeclothes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;

public class FrontActivity extends AppCompatActivity implements changeListener {

    FrameLayout container;
    changeListener listener = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
        container = findViewById(R.id.container);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("HOME",listener)).commit();
    }

    public void onComponentClick(View view){
        int id = view.getId();
        if(id == R.id.home_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("HOME",listener)).commit();
        }
        else if(id == R.id.men_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("MEN",listener)).commit();
        }
        else if(id == R.id.women_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("WOMEN",listener)).commit();
        }
        else if(id == R.id.kids_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("KIDS",listener)).commit();
        }
        else if(id == R.id.baby_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("BABY",listener)).commit();
        }
        else if(id == R.id.cart_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("CART",listener)).commit();
        }
        else if(id == R.id.login_img){
            Intent intent = new Intent(getApplicationContext(),loginActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.info_img){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new fullCard()).commit();
        }
    }

    public void onClick(item it,String type){
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count >= 1)
            getSupportFragmentManager().popBackStackImmediate();
        getSupportFragmentManager().beginTransaction().add(R.id.container,new fullCard(it,type,listener)).addToBackStack("item").commit();
    }

    public void onItemRemoved(){
        getSupportFragmentManager().getBackStackEntryCount();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,new MyFragment("CART",listener)).commit();
            }
        }, 800);
    }

    @Override
    public void onBackPressed() {
        if (!getSupportFragmentManager().popBackStackImmediate())
            super.onBackPressed();
    }
}