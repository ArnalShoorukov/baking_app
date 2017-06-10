package com.mosquefinder.arnal.bakingapp.widget;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.mosquefinder.arnal.bakingapp.R;
import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;
import com.mosquefinder.arnal.bakingapp.data.remote.ApiUtils;
import com.mosquefinder.arnal.bakingapp.data.remote.SOService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mosquefinder.arnal.bakingapp.MainActivity.nameList;
/**
 * Created by arnal on 6/6/17.
 */

public class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;

    public MyWidgetRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
            getData();
    }

    public void getData() {

        SOService mService = ApiUtils.getSOService();
        mService.getAnswers().enqueue(new Callback<List<SOAnswersResponse>>() {

            @Override
            public void onResponse(Call<List<SOAnswersResponse>> call, Response<List<SOAnswersResponse>> response) {

                if(response.isSuccessful()) {

                    List<SOAnswersResponse> result = response.body();

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
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Log.v(mContext.getClass().getSimpleName(), "position = "+position);

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget_item);
        rv.setTextViewText(R.id.recipe_name, nameList.get(position).getName());
        rv.removeAllViews(R.id.ingerdient_list);

        for (int i=0;i<nameList.get(position).getIngredients().size();i++){
            RemoteViews  ing= new RemoteViews(mContext.getPackageName(), R.layout.ingredient_widget_item);
            ing.setTextViewText(R.id.ingredient,nameList.get(position).getIngredients().get(i).getIngredient());
            ing.setTextViewText(R.id.measure,nameList.get(position).getIngredients().get(i).getMeasure());
            ing.setTextViewText(R.id.quantity,nameList.get(position).getIngredients().get(i).getQuantity()+"");

            rv.addView(R.id.ingerdient_list,ing);
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
