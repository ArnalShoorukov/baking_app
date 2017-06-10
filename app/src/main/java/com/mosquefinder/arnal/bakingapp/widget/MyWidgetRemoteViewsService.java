package com.mosquefinder.arnal.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by arnal on 6/6/17.
 */

public class MyWidgetRemoteViewsService extends RemoteViewsService

    {
        @Override
        public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());
    }
}
