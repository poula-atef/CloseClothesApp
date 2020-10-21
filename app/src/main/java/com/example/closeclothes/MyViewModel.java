package com.example.closeclothes;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<item>> mutableLiveData = new MutableLiveData<List<item>>();

    public MutableLiveData<List<item>> getMutableLiveData() {
        return mutableLiveData;
    }
    public void setMutableLiveData(MutableLiveData<List<item>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }


    private void getCardItemsData(){
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://closeclothes.herokuapp.com/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        api service = retrofit.create(api.class);
        Call<List<item>> cards = service.getCards();
        cards.enqueue(new Callback<List<item>>() {
            @Override
            public void onResponse(Call<List<item>> call, Response<List<item>> response) {
                mutableLiveData.setValue(response.body());
            }


            @Override
            public void onFailure(Call<List<item>> call, Throwable t) {

            }
        });
    }
    public void getCard(){
     getCardItemsData();
    }

    private void getItemsData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://closeclothes.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api service = retrofit.create(api.class);
        Call<List<item>> cards = service.getItems();
        cards.enqueue(new Callback<List<item>>() {
            @Override
            public void onResponse(Call<List<item>> call, Response<List<item>> response) {
                mutableLiveData.setValue(response.body());
            }


            @Override
            public void onFailure(Call<List<item>> call, Throwable t) {

            }
        });
    }
    public void getItems(){
        getItemsData();
    }


    private void getCategoryItemsData(String category){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://closeclothes.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api service = retrofit.create(api.class);
        Call<List<item>> cards = service.getCategoryItems(category);
        cards.enqueue(new Callback<List<item>>() {
            @Override
            public void onResponse(Call<List<item>> call, Response<List<item>> response) {
                mutableLiveData.setValue(response.body());
            }


            @Override
            public void onFailure(Call<List<item>> call, Throwable t) {

            }
        });
    }
    public void getBabyItems(){
        getCategoryItemsData("baby");
    }
    public void getKidsItems(){
        getCategoryItemsData("kids");
    }
    public void getWomenItems(){
        getCategoryItemsData("women");
    }
    public void getMenItems(){
        getCategoryItemsData("men");
    }

    private void addItemDataToCard(item it){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://closeclothes.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api service = retrofit.create(api.class);
        Call<String> cards = service.addToCard(it);
        cards.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
    public void addCard(item it){
        addItemDataToCard(it);
    }


    private void removeItemDataFromCard(String id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://closeclothes.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api service = retrofit.create(api.class);
        Call<String> cards = service.removeFromCard(id);
        cards.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
    public void removeCard(String id){
        removeItemDataFromCard(id);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////


}
