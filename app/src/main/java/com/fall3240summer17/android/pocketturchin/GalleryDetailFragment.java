package com.fall3240summer17.android.pocketturchin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryDetailFragment extends Fragment {

    private static final String ARG_GALLERY_ID = "gallery_id";

    private ImageView mImage;
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
        mImage.setImageResource(R.drawable.collective_vigilance_1200x630);

        mFavoriteButton = (ImageButton) v.findViewById(R.id.favorite_button);
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
