package com.fall3240summer17.android.pocketturchin;

import android.graphics.drawable.Drawable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Chris Lail on 6/27/2017.
 */

public class GalleryItem {

    private UUID mId;
    private int mPicture;
    private String mTitle;
    private String mArtist;
    private String mGalleryName;
    private boolean mFavorited;
    private String mDescription;
    private String mBeginDate;
    private String mEndDate;

    public GalleryItem() {
        this(UUID.randomUUID());
    }

    public GalleryItem(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public int getPicture() {
        return mPicture;
    }

    public void setPicture(int picture) {
        mPicture = picture;
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

    public boolean isFavorited() {
        return mFavorited;
    }

    public void setFavorited(boolean favorited) {
        mFavorited = favorited;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getBeginDate() {
        return mBeginDate;
    }

    public void setBeginDate(String beginDate) {
        mBeginDate = beginDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }
}
