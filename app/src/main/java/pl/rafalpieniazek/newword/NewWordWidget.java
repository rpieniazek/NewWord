package pl.rafalpieniazek.newword;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import pl.rafalpieniazek.newword.model.FlashCard;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link NewWordWidgetConfigureActivity NewWordWidgetConfigureActivity}
 */
public class NewWordWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        String timeString =
                DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());


        FlashCard randomFlashCard = FlashCardService.getInstance(context).getNextRandomElement();

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_word_widget);
        views.setTextViewText(R.id.appwidget_keyword, randomFlashCard.getKeyword());
        views.setTextViewText(R.id.appwidget_description, randomFlashCard.getDescription());


        //Create an Intent with the AppWidgetManager.ACTION_APPWIDGET_UPDATE action//
        Intent intentUpdate = new Intent(context, NewWordWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        //Update the current widget instance only, by creating an array that contains the widget’s unique ID//
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        //Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context, appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        //Send the pending intent in response to the user tapping the ‘Refresh’ TextView//
        views.setOnClickPendingIntent(R.id.refresh, pendingUpdate);

        views.setTextViewText(R.id.last_modified,
                context.getResources().getString(
                        R.string.last_modified, timeString));

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Toast.makeText(context, "WidgetUpdated!", Toast.LENGTH_SHORT).show();

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            NewWordWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

