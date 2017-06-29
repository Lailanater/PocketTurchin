package com.fall3240summer17.android.pocketturchin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryDetailFragment extends Fragment {

    private static final String ARG_GALLERY_ID = "gallery_id";

    private ImageView mImage;
    private TextView mTitle;
    private TextView mArtist;
    private TextView mDisplayedIn;
    private TextView mExhibitionDates;
    private TextView mDescription;
    private ImageButton mFavoriteButton;
    private GalleryItem mGalleryItem;


    public static GalleryDetailFragment newInstance(UUID galleryId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_GALLERY_ID, galleryId);

        GalleryDetailFragment fragment = new GalleryDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID galleryId = (UUID) getArguments().getSerializable(ARG_GALLERY_ID);
        mGalleryItem = GalleryList.get(getActivity()).getGalleryItem(galleryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery_detail, container, false);

        mImage = (ImageView) v.findViewById(R.id.image_detail_gallery);
        mImage.setImageResource(mGalleryItem.getPicture());

        mTitle = (TextView) v.findViewById(R.id.title_detail_gallery);
        mTitle.append(mGalleryItem.getTitle());

        mArtist = (TextView) v.findViewById(R.id.artist_detail_gallery);
        mArtist.append(mGalleryItem.getArtist());

        mDisplayedIn = (TextView) v.findViewById(R.id.displayed_detail_gallery);
        mDisplayedIn.append(mGalleryItem.getGalleryName());

        mExhibitionDates = (TextView) v.findViewById(R.id.exhibition_dates_detail_gallery);
        mExhibitionDates.append(mGalleryItem.getBeginDate().toString() + " - " + mGalleryItem.getEndDate().toString());

        mDescription = (TextView) v.findViewById(R.id.description);
        mDescription.append(mGalleryItem.getDescription());

        mFavoriteButton = (ImageButton) v.findViewById(R.id.favorite_button);
        if (mGalleryItem.isFavorited()) {
            mFavoriteButton.setImageResource(R.drawable.ic_favs_colored);
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_favs);
        }
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mGalleryItem.isFavorited()) {
                    mFavoriteButton.setImageResource(R.drawable.ic_favs);
                } else {
                    mFavoriteButton.setImageResource(R.drawable.ic_favs_colored);
                }
                mGalleryItem.setFavorited(!mGalleryItem.isFavorited());
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        GalleryList.get(getActivity()).updateGalleryItem(mGalleryItem);
    }

}
