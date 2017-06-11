package com.mosquefinder.arnal.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mosquefinder.arnal.bakingapp.adapter.TitleAdapter;
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


    private static boolean tablet;

    private TitleAdapter titleAdapter;
    private SOService mService;
    public static List<SOAnswersResponse> nameList = new ArrayList<>();
    private LinearLayoutManager mLayoutManager;
    private String LIST_STATE_KEY = "list_state";
    private Parcelable mListState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tablet = getResources().getBoolean(R.bool.is_tablet);
        mService = ApiUtils.getSOService();
        if (tablet) {
            mLayoutManager = new GridLayoutManager(this, 3);
        } else {
            mLayoutManager = new GridLayoutManager(this, 1);
        }

        loadAnswers();
        titleAdapter = new TitleAdapter(this, nameList);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.bake_recycler_view);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(titleAdapter);

        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        titleAdapter.setListener(new TitleAdapter.Listener() {
            @Override
            public void onClick(int position) {

                SOAnswersResponse response = nameList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("object" , response );
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

   public void loadAnswers() {

        mService.getAnswers().enqueue(new Callback<List<SOAnswersResponse>>() {

            @Override
            public void onResponse(Call<List<SOAnswersResponse>> call, Response<List<SOAnswersResponse>> response) {

                if(response.isSuccessful()) {

                    List<SOAnswersResponse> result = response.body();

                    titleAdapter.setMovieList(result);
                    nameList = result;

                }else {
                    int statusCode  = response.code();

                }
            }

            @Override
            public void onFailure(Call<List<SOAnswersResponse>> call, Throwable t) {
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mListState = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(LIST_STATE_KEY, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListState != null) {
            mLayoutManager.onRestoreInstanceState(mListState);
        }
    }
}
