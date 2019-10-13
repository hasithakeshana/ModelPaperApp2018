package com.example.modelpaperapp;

import android.provider.BaseColumns;

public final class UserProfile {

    private UserProfile() {
    }

    public static class Users implements BaseColumns
    {

        public static final String TABLE_NAME = "UserInfo";
        public static final String C1 = "username";
        public static final String C2 = "dateofBirth";
        public static final String C3 = "password";
        public static final String C4 = "gender";

    }
}
