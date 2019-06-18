package com.example.sonukg.movieapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sonukg.movieapp.adapter.MoviesAdapter;
import com.example.sonukg.movieapp.retroclient.RetroClient;
import com.example.sonukg.movieapp.retrointerface.RetroInterface;
import com.example.sonukg.movieapp.model.*;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Result> results;
    private MoviesAdapter adapter;
    private String receiveToken;
    private final String api_key="b7cd3340a794e5a2f35e3abb820b497f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetroInterface retroInterface=new RetroClient().getRetrofit().create(RetroInterface.class);
        retroInterface.getDetails(this.api_key).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                UserModel userModel=response.body();
             if (userModel!=null && userModel.getResults()!=null  &&isConnected())
             {

                 Toast.makeText(MainActivity.this,"Internet Connected",Toast.LENGTH_LONG).show();
                  results= (ArrayList<Result>) userModel.getResults();

                 showRecycleView();
                  //Toast.makeText(getApplicationContext(),""+results,Toast.LENGTH_LONG).show();
                 //TextView resul=findViewById(R.id.resul);
                 //resul.setText(response.body().getResults().get(0).getTitle());
             }
             else{
                 Toast.makeText(MainActivity.this,"Internet Not Connected",Toast.LENGTH_LONG)
                         .show();
             }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            Log.d("msg",t.getMessage());
            }
            });
    }

    private void showRecycleView() {
        recyclerView=findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new MoviesAdapter(MainActivity.this, results, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String title;
                String synopsis;
                String rating;
                String img;

                title=results.get(position).getOriginalTitle();
                synopsis=results.get(position).getOverview();
                rating=results.get(position).getPopularity().toString();
                img="https://image.tmdb.org/t/p/original"+results.get(position).getPosterPath();

                Intent intent=new Intent(MainActivity.this,MovieActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("synopsis",synopsis);
                intent.putExtra("rating",rating);
                intent.putExtra("img",img);
                startActivity(intent);

                /*Intent intent1=new Intent(MainActivity.this,ViewPagerAdapter.class);
                img="https://image.tmdb.org/t/p/original"+results.get(position).getPosterPath();
                intent1.putExtra("img",img);*/

            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public boolean isConnected(){
        boolean connected=false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
}
