package com.example.closeclothes;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface api {
    @GET("findAllCard")
    Call<List<item>> getCards();

    @GET("findAllItems")
    Call<List<item>> getItems();

    @GET("findItemsWithCategory")
    Call<List<item>> getCategoryItems(@Query("categ") String category);

    @POST("insertCard")
    Call<String> addToCard(@Body item it);

    @POST("deleteOneCard")
    Call<String> removeFromCard(@Query("id") String id);

}
