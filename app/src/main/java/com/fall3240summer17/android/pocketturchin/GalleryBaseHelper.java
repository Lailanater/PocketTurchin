package com.fall3240summer17.android.pocketturchin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fall3240summer17.android.pocketturchin.GalleryDbSchema.GalleryTable;

/**
 * Created by Chris Lail on 6/28/2017.
 */

public class GalleryBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "galleryBase.db";

    public GalleryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + GalleryTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                GalleryTable.Cols.UUID + ", " +
                GalleryTable.Cols.PICTURE + ", " +
                GalleryTable.Cols.TITLE + ", " +
                GalleryTable.Cols.ARTIST + ", " +
                GalleryTable.Cols.FAVORITE + ", " +
                GalleryTable.Cols.GALLERY + ", " +
                GalleryTable.Cols.DESCRIPTION + ", " +
                GalleryTable.Cols.BEGIN_DATE + ", " +
                GalleryTable.Cols.END_DATE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
