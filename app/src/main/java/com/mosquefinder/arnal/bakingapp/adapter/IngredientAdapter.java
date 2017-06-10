package com.mosquefinder.arnal.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosquefinder.arnal.bakingapp.R;
import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnal on 5/27/17.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    private Listener listener;
    private Context context;
    private List<Ingredient> ingredients;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredients.clear();
        this.ingredients.addAll(ingredientList);
        notifyDataSetChanged();
    }
    public IngredientAdapter(Context context, List<Ingredient> ingredients1) {
        this.context = context;
        this.ingredients = ingredients1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public TextView textView2;
        public TextView textView3;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.ingredient_text);
            textView2 = (TextView)itemView.findViewById(R.id.ingredient_text2);
            textView3 = (TextView)itemView.findViewById(R.id.ingredient_text3);
        }
    }

    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_cardview, parent, false);

        IngredientAdapter.ViewHolder view = new IngredientAdapter.ViewHolder(inflate);
        return view;
    }

    @Override
    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, final int position) {

        Ingredient name = ingredients.get(position);
        final TextView textView = holder.textView;
        textView.setText(name.getIngredient());

        final TextView textView2 = holder.textView2;
        textView2.setText(name.getQuantity().toString());

        final TextView textView3 = holder.textView3;
        textView3.setText(name.getMeasure());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}





