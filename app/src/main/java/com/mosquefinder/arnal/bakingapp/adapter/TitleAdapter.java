package com.mosquefinder.arnal.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mosquefinder.arnal.bakingapp.R;
import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnal on 5/27/17.
 */

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.ViewHolder> {

    private Listener listener;
    private Context context;
    private List<SOAnswersResponse> nameList;

    public interface Listener {
        void onClick(int position);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void setMovieList(List<SOAnswersResponse> namesList) {
        this.nameList.clear();
        this.nameList.addAll(namesList);
        notifyDataSetChanged();
    }

    public TitleAdapter(Context context, List<SOAnswersResponse> nameList) {
        this.context = context;
        this.nameList = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.title_cardview);
        }
    }

    @Override
    public TitleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_cardview, parent, false);

        ViewHolder view = new ViewHolder(inflate);
        return view;
    }

    @Override
    public void onBindViewHolder(TitleAdapter.ViewHolder holder, final int position) {
        SOAnswersResponse name = nameList.get(position);
        final TextView textView = holder.textView;
        textView.setText(name.getName());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (nameList.isEmpty() ? 0: nameList.size());
    }
}
