package com.fall3240summer17.android.pocketturchin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Gallery;

import com.fall3240summer17.android.pocketturchin.GalleryDbSchema.GalleryTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Chris Lail on 6/28/2017.
 */

public class GalleryList {

    private List<GalleryItem> mGalleryItems;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static GalleryList sGalleryList;

    private GalleryList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new GalleryBaseHelper(mContext)
                .getWritableDatabase();
    }

    public static GalleryList get(Context context) {
        if (sGalleryList == null) {
            return new GalleryList(context);
        }
        return sGalleryList;
    }

    public List<GalleryItem> getGalleryItems() {
        List<GalleryItem> items = new ArrayList<>();

        GalleryItemCursorWrapper cursor = queryGalleryItems(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                items.add(cursor.getGalleryItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return items;
    }

    public GalleryItem getGalleryItem(UUID id) {
        GalleryItemCursorWrapper cursor = queryGalleryItems(
                GalleryTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();

            return cursor.getGalleryItem();
        } finally {
            cursor.close();
        }
    }

    public List<GalleryItem> getFavGalleryItems() {
        List<GalleryItem> items = new ArrayList<>();

        GalleryItemCursorWrapper cursor = queryGalleryItems(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (cursor.getGalleryItem().isFavorited()) {
                    items.add(cursor.getGalleryItem());
                }
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return items;
    }

    public void addGalleryItem(GalleryItem item) {
        ContentValues values = getContentValues(item);

        mDatabase.insert(GalleryTable.NAME, null, values);
    }

    public void updateGalleryItem(GalleryItem galleryItem) {
        String uuidString = galleryItem.getId().toString();
        ContentValues values = getContentValues(galleryItem);

        mDatabase.update(GalleryTable.NAME, values,
                GalleryTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private GalleryItemCursorWrapper queryGalleryItems(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                GalleryTable.NAME,
                null,   // columns - null selects all columns
                whereClause,
                whereArgs,
                null,   // groupBy
                null,   // having
                null    // orderBy
        );

        return new GalleryItemCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(GalleryItem galleryItem) {
        ContentValues values = new ContentValues();
        values.put(GalleryTable.Cols.UUID, galleryItem.getId().toString());
        values.put(GalleryTable.Cols.PICTURE, galleryItem.getPicture());
        values.put(GalleryTable.Cols.TITLE, galleryItem.getTitle());
        values.put(GalleryTable.Cols.ARTIST, galleryItem.getArtist());
        values.put(GalleryTable.Cols.FAVORITE, galleryItem.isFavorited() ? 1 : 0);
        values.put(GalleryTable.Cols.GALLERY, galleryItem.getGalleryName());
        values.put(GalleryTable.Cols.DESCRIPTION, galleryItem.getDescription());
        values.put(GalleryTable.Cols.BEGIN_DATE, galleryItem.getBeginDate().toString());
        values.put(GalleryTable.Cols.END_DATE, galleryItem.getEndDate().toString());

        return values;
    }
}
