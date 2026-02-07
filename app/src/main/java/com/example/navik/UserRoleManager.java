package com.example.navik;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

public final class UserRoleManager {

    private UserRoleManager() {}

    public static final String PREFS_NAME = "NavikPrefs";

    public static final String KEY_USER_ROLE = "userRole";

    public static final String ROLE_USER = "user";
    public static final String ROLE_MENTOR = "mentor";

    public static boolean isValidRole(@Nullable String role) {
        return ROLE_USER.equals(role) || ROLE_MENTOR.equals(role);
    }

    public static void setRole(Context context, String role) {
        if (!isValidRole(role)) {
            return;
        }
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_USER_ROLE, role).apply();
    }

    @Nullable
    public static String getRole(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_ROLE, null);
    }

    public static boolean hasRole(Context context) {
        return isValidRole(getRole(context));
    }

    public static boolean isMentor(Context context) {
        return ROLE_MENTOR.equals(getRole(context));
    }
}
