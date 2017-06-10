package com.mosquefinder.arnal.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mosquefinder.arnal.bakingapp.adapter.IngredientAdapter;
import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {

    private List<Ingredient> ingredientList;
    private IngredientAdapter ingredientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

      if(savedInstanceState == null){

         ingredientList = getIntent().getParcelableArrayListExtra("ingredient");

         ingredientAdapter = new IngredientAdapter(this, ingredientList);
         RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_detail_ingredient);
         RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
         recyclerView.setLayoutManager(layoutManager);
         recyclerView.setAdapter(ingredientAdapter);
       }
    }
}
