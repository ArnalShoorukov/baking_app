package com.mosquefinder.arnal.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosquefinder.arnal.bakingapp.data.model.Ingredient;
import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnal on 5/23/17.
 */

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

private List<Ingredient> mItems;
private Context mContext;
private PostItemListener mItemListener;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView titleTv;
    PostItemListener mItemListener;

    public ViewHolder(View itemView, PostItemListener postItemListener) {
        super(itemView);
        titleTv = (TextView) itemView.findViewById(android.R.id.text1);

        this.mItemListener = postItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Ingredient item = getItem(getAdapterPosition());
        this.mItemListener.onPostClick(item.getQuantity());

        notifyDataSetChanged();
    }
}

    public AnswersAdapter(Context context, List<Ingredient> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AnswersAdapter.ViewHolder holder, int position) {

        Ingredient item = mItems.get(position);
        TextView textView = holder.titleTv;
        textView.setText(item.getIngredient());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Ingredient> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Ingredient getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

public interface PostItemListener {
    void onPostClick(long id);
}
}
