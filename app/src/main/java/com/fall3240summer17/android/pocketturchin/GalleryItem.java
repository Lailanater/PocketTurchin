package com.fall3240summer17.android.pocketturchin;

import java.util.UUID;

/**
 * Created by Chris Lail on 6/27/2017.
 */

public class GalleryItem {

    private UUID mId;
    private String mTitle;
    private String mArtist;
    private String mGalleryName;

    public GalleryItem() {
        this(UUID.randomUUID());
    }

    public GalleryItem(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getGalleryName() {
        return mGalleryName;
    }

    public void setGalleryName(String galleryName) {
        mGalleryName = galleryName;
    }
}
