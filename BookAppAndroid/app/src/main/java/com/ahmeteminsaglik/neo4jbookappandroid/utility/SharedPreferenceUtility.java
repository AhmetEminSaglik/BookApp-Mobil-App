package com.ahmeteminsaglik.neo4jbookappandroid.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ahmeteminsaglik.neo4jbookappandroid.model.EnumUser;
import com.ahmeteminsaglik.neo4jbookappandroid.model.User;

public class SharedPreferenceUtility {

    public static final String SHARED_PREFRENCE_NAME = "prefName";
    private static SharedPreferences sharedPreferences;


    public static void clearSharedPreference(Activity activity) {
        sharedPreferences = activity.getSharedPreferences(SHARED_PREFRENCE_NAME, Context.MODE_PRIVATE);

        sharedPreferences.getLong(EnumUser.ID.getName(), -1);
        sharedPreferences.getString(EnumUser.NAME.getName(), null);
        sharedPreferences.getString(EnumUser.LASTNAME.getName(), null);
        sharedPreferences.getInt(EnumUser.TOTALFOLLOWERS.getName(), -1);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static void setDataToSharedPreference(Activity activity, User user) {
        sharedPreferences = activity.getSharedPreferences(SHARED_PREFRENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(EnumUser.ID.getName(), user.getId());
        editor.putString(EnumUser.NAME.getName(), user.getName());
        editor.putString(EnumUser.LASTNAME.getName(), user.getName());
        editor.putInt(EnumUser.TOTALFOLLOWERS.getName(), user.getTotalFollowers());
        editor.apply();
    }

    public static String getStringDataFromSharedPreference(Context context, EnumUser enumUser) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFRENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(enumUser.getName(), null);
    }

    public static int getIntegerDataFromSharedPreference(Context context, EnumUser enumUser) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFRENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(enumUser.getName(), -1);
    }

    public static long getLongDataFromSharedPreference(Context context, EnumUser enumUser) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFRENCE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(enumUser.getName(), -1);
    }
}
