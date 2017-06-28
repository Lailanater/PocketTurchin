package com.fall3240summer17.android.pocketturchin;

/**
 * Created by Chris Lail on 6/28/2017.
 */

public class GalleryDbSchema {

    public static final class GalleryTable {
        public static final String NAME = "gallery";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PICTURE = "picture";
            public static final String TITLE = "title";
            public static final String ARTIST = "artist";
            public static final String FAVORITE = "favorite";
            public static final String GALLERY = "gallery";
            public static final String DESCRIPTION = "description";
            public static final String BEGIN_DATE = "begin_date";
            public static final String END_DATE = "end_date";
        }
    }
}
