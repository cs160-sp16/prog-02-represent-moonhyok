package com.cs160.joleary.catnip;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late in the night. (early in the morning?)
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watchmessages
//    private static final String FRED_FEED = "/Fred";
//    private static final String LEXY_FEED = "/Lexy";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases
        //(here, fred vs lexy)

//        if( messageEvent.getPath().equalsIgnoreCase( FRED_FEED ) ) {
        String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
        Intent intent = new Intent(this, MainActivity.class );
        value.split("!");

        String[] info = value.split("&");

        Log.d("KJIJEIFJ",value);
        Log.d("traking listener",info[4]);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        System.out.println(value);
        //you need to add this flag since you're starting a new activity from a service
        intent.putExtra("senate_1", info[0]);
        intent.putExtra("senate_2", info[1]);
        intent.putExtra("house_1", info[2]);
        intent.putExtra("house_2", info[3]);
        intent.putExtra("county",info[4]);
        intent.putExtra("election_data",info[5]);

//        Log.d("T", "about to start watch MainActivity with CAT_NAME: Fred");
        startActivity(intent);

    }
}