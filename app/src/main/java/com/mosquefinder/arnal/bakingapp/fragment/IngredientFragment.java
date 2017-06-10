package com.mosquefinder.arnal.bakingapp.fragment;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mosquefinder.arnal.bakingapp.R;
import com.mosquefinder.arnal.bakingapp.adapter.IngredientAdapter;
import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientFragment extends Fragment {

    private List<Ingredient> ingredientList;
    private IngredientAdapter ingredientAdapter;
    public static final String LIST_IN = "ingredient";

    public IngredientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredient, container, false);

        if(savedInstanceState!= null){
            ingredientList = savedInstanceState.getParcelableArrayList(LIST_IN);
        }

        if(ingredientList!=null){
            ingredientAdapter = new IngredientAdapter(getContext(), ingredientList);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_detail_ingredient);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(ingredientAdapter);
        }
        else{
            Toast.makeText(getContext(),"no Ingredients present",Toast.LENGTH_SHORT).show();
        }


        return rootView;


    }
    public void setIngList(List<Ingredient> ingredientList){
        this.ingredientList = ingredientList;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_IN, (ArrayList<? extends Parcelable>) ingredientList);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.ingredients);
    }
}
