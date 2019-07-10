package com.example.android.viewpager.Data;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {
    public Contract(){

    }

    public static final class Entry implements BaseColumns {

        public static final String _ID = BaseColumns._ID;
        public static final String TABLE_NAME = "newsFav1";
        public static final String TABLE_API = "newsApi";

        public static final String COLUMN_ARTICLE = "article";
        public static final String COLUMN_IMAGE = "img";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_PAPER = "paperName";
        public static final String COLUMN_LINK = "url";

        public static final String COLUMN_TYPE = "newsType";

        public static final String CONTENT_AUTHORITY = "com.example.android.viewpager";
        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" +CONTENT_AUTHORITY);
        public static final String PATH_NEWS = "viewpager";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NEWS);
    }
}
