package com.example.android.bakingapp.myappwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingapp.activity.MainActivity;
import com.example.android.bakingapp.R;

import java.util.ArrayList;


public class MyBakingWidgetProvider extends AppWidgetProvider {


    static String TitleName;

    static ArrayList<String> ingredientsList = new ArrayList<>();

    public static String REMOTEVIEW_INGREDIENT_ARRAYLIST="REMOTEVIEW_INGREDIENT_ARRAYLIST";
    public static String REMOTEVIEW_BUNDLE="REMOTEVIEW_BUNDLE";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view);

        Intent appIntent = new Intent(context, MainActivity.class);
        appIntent.addCategory(Intent.ACTION_MAIN);
        appIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        appIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent appPendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_grid_view, appPendingIntent);


        Intent intent = new Intent(context, MyGridViewWidgetService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, intent);
        views.setTextViewText(R.id.textView,TitleName);


        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

    }

    public static void updateBakingWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MyBakingWidgetProvider.class));

        final String action = intent.getAction();

        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE2")) {

            ingredientsList = intent.getExtras().getStringArrayList(UpdateBakingIntentService.MY_INGREDIENTS_ARRAYLIST);
            TitleName=intent.getExtras().getString(UpdateBakingIntentService.TITLE);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);
            MyBakingWidgetProvider.updateBakingWidgets(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);
        }
    }
}

