package com.mosquefinder.arnal.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;
import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;
import com.mosquefinder.arnal.bakingapp.data.remote.ApiUtils;
import com.mosquefinder.arnal.bakingapp.data.remote.SOService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    static List<SOAnswersResponse> movieList;
    private AnswersAdapter answersAdapter;
    private RecyclerView recyclerView;
    private SOService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);

        mService = ApiUtils.getSOService();
        recyclerView = (RecyclerView)findViewById(R.id.bake_recycler_view);
        answersAdapter = new AnswersAdapter(this, new ArrayList<Ingredient>(0), new AnswersAdapter.PostItemListener() {
            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });
       //nameAdapters = new NameAdapter(this, movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(answersAdapter);
        recyclerView.setAdapter(answersAdapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        loadAnswers();


    }

   public void loadAnswers() {

       Log.d(TAG, mService.toString());
        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {

            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {

                if(response.isSuccessful()) {
                    answersAdapter.updateAnswers(response.body().getIngredients());

                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();

                }

            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {

                /// showErrorMessage();
                Log.d("MainActivity", "error loading from API");
            }
        });

    }
}
