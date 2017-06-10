package com.mosquefinder.arnal.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosquefinder.arnal.bakingapp.R;
import com.mosquefinder.arnal.bakingapp.data.model.Step;

import java.util.List;

/**
 * Created by arnal on 5/27/17.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {


    private Listener listener;
    private Context context;
    private List<Step> steps;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void setIngredientList(List<Step> stepList) {
        this.steps.clear();
        this.steps.addAll(stepList);
        notifyDataSetChanged();
    }
    public StepsAdapter(Context context, List<Step> steps) {
        this.context = context;
        this.steps = steps;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.steps_text_cardview);
        }
    }

    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_cardview, parent, false);

        StepsAdapter.ViewHolder view = new StepsAdapter.ViewHolder(inflate);
        return view;
    }

    @Override
    public void onBindViewHolder(StepsAdapter.ViewHolder holder, final int position) {

        Step  stepName = steps.get(position);
        final TextView textView = holder.textView;
        textView.setText(stepName.getShortDescription());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (steps.isEmpty() ? 0: steps.size());
    }
}