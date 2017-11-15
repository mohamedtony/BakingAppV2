package com.example.android.bakingapp.myappwidget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
public class UpdateBakingIntentService extends IntentService {
    public static String MY_INGREDIENTS_ARRAYLIST ="MY_INGREDIENTS_ARRAYLIST";
    public static String TITLE ="FROM_ACTIVITY_TITLE";

    public UpdateBakingIntentService() {
        super("UpdateBakingIntentService");
    }

    public static void startBakingService(Context context,ArrayList<String> fromActivityIngredientsList,String title) {
        Intent intent = new Intent(context, UpdateBakingIntentService.class);
        intent.putExtra(MY_INGREDIENTS_ARRAYLIST,fromActivityIngredientsList);
        intent.putExtra(TITLE,title);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            ArrayList<String> fromActivityIngredientsList = intent.getExtras().getStringArrayList(MY_INGREDIENTS_ARRAYLIST);
            String titleName=intent.getExtras().getString(TITLE);

            handleActionUpdateBakingWidgets(fromActivityIngredientsList,titleName);

        }
    }



    private void handleActionUpdateBakingWidgets(ArrayList<String> fromActivityIngredientsList,String title) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE2");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE2");

        intent.putExtra(MY_INGREDIENTS_ARRAYLIST,fromActivityIngredientsList);
        intent.putExtra(TITLE,title);

        sendBroadcast(intent);
    }
}
