package com.fall3240summer17.android.pocketturchin;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.fall3240summer17.android.pocketturchin.GalleryDbSchema.GalleryTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Chris Lail on 6/28/2017.
 */

public class GalleryItemCursorWrapper extends CursorWrapper {

    public GalleryItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public GalleryItem getGalleryItem() {
        String uuidString = getString(getColumnIndex(GalleryTable.Cols.UUID));
        int picture = getInt(getColumnIndex(GalleryTable.Cols.PICTURE));
        String title = getString(getColumnIndex(GalleryTable.Cols.TITLE));
        String artist = getString(getColumnIndex(GalleryTable.Cols.ARTIST));
        int favorited = getInt(getColumnIndex(GalleryTable.Cols.FAVORITE));
        String gallery = getString(getColumnIndex(GalleryTable.Cols.GALLERY));
        String description = getString(getColumnIndex(GalleryTable.Cols.DESCRIPTION));
        long beginDate = getLong(getColumnIndex(GalleryTable.Cols.BEGIN_DATE));
        long endDate = getLong(getColumnIndex(GalleryTable.Cols.END_DATE));

        GalleryItem item = new GalleryItem(UUID.fromString(uuidString));
        item.setPicture(picture);
        item.setTitle(title);
        item.setArtist(artist);
        item.setFavorited(favorited != 0);
        item.setGalleryName(gallery);
        item.setDescription(description);
        item.setBeginDate(new Date(beginDate));
        item.setEndDate(new Date(endDate));

        return item;
    }
}
