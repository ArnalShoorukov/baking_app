package com.mosquefinder.arnal.bakingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mosquefinder.arnal.bakingapp.adapter.StepsAdapter;
import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;
import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;
import com.mosquefinder.arnal.bakingapp.data.model.Step;
import com.mosquefinder.arnal.bakingapp.fragment.IngredientFragment;
import com.mosquefinder.arnal.bakingapp.fragment.StepFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private StepsAdapter stepsAdapter;
    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    @BindView(R.id.steps_recycler_view)
    RecyclerView recyclerViewSteps;

    @BindView(R.id.ingredients)
    TextView textView;

    public static boolean tablet;

    private String LIST_STATE_KEY = "list_state";
    private Parcelable mListState;
    private LinearLayoutManager layoutManagerSteps;

    private SOAnswersResponse soAnswersResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            soAnswersResponse = bundle.getParcelable("object");
        }

        if (findViewById(R.id.item_detail_container_tablet) != null) {
            tablet = true;
        }

        ingredientList = soAnswersResponse.getIngredients();
        stepList = soAnswersResponse.getSteps();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ingredient", (ArrayList<? extends Parcelable>) ingredientList);

                if(tablet){

                    IngredientFragment newFragment = new IngredientFragment();
                    newFragment.setIngList(ingredientList);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container_tablet, newFragment)
                            .commit();
                }else {

                    Intent intent = new Intent(getBaseContext(), IngredientActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

        stepsAdapter = new StepsAdapter(this, stepList);
        layoutManagerSteps = new LinearLayoutManager(this);
        recyclerViewSteps.setLayoutManager(layoutManagerSteps);
        recyclerViewSteps.setAdapter(stepsAdapter);

        recyclerViewSteps.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerViewSteps.addItemDecoration(itemDecoration);

        stepsAdapter.setListener(new StepsAdapter.Listener() {
            @Override
            public void onClick(int position) {

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("steps", (ArrayList<? extends Parcelable>) stepList);
                bundle.putInt("positions", position);

                if (tablet){


                    StepFragment stepFragment = new StepFragment();
                    stepFragment.setStepList(stepList);
                    stepFragment.setPosition(position);
                    // Add the fragment to its container using a FragmentManager and a Transaction
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    fragmentManager.beginTransaction()
                            .replace(R.id.item_detail_container_tablet, stepFragment)
                            .commit();

                }else {
                    Intent intentSteps = new Intent(getBaseContext(), ExtraDetailActivity.class);
                    intentSteps.putExtras(bundle);
                    startActivity(intentSteps);

                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mListState = layoutManagerSteps.onSaveInstanceState();
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
        this.setTitle(soAnswersResponse.getName());
        if (mListState != null) {
            layoutManagerSteps.onRestoreInstanceState(mListState);
        }
    }
}
