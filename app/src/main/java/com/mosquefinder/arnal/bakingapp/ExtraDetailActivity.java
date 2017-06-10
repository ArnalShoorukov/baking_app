package com.mosquefinder.arnal.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mosquefinder.arnal.bakingapp.fragment.StepFragment;


public class ExtraDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_steps);

        if (savedInstanceState == null) {

            StepFragment stepFragment = new StepFragment();

            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, stepFragment)
                    .commit();
            }
    }
}
