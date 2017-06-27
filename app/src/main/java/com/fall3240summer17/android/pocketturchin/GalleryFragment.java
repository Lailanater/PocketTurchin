package com.fall3240summer17.android.pocketturchin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private RecyclerView mGalleryRecycler;
    private GalleryAdapter mAdapter;


    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        mGalleryRecycler = (RecyclerView) v.findViewById(R.id.gallery_recycler_view);
        mGalleryRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        List<GalleryItem> galleryItems = new ArrayList<GalleryItem>();

        //////////////////////// TEST VALUES /////////////////////////////

        GalleryItem item = new GalleryItem();
        item.setArtist("Chris Lail");
        item.setTitle("My Life");
        item.setGalleryName("Main Plaza");

        galleryItems.add(item);

        GalleryItem item2 = new GalleryItem();
        item2.setArtist("Justin Perry");
        item2.setTitle("I Can't Draw");
        item2.setGalleryName("East-side Atlanta Theater");

        galleryItems.add(item2);
        //////////////////////////////////////////////////////////////////

        if (mAdapter == null) {
            mAdapter = new GalleryAdapter(galleryItems);
            mGalleryRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.setGalleryItems(galleryItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class GalleryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImage;
        private TextView mTitle;
        private TextView mArtist;
        private TextView mGalleryName;
        private ImageView mRightArrow;

        private GalleryItem mGalleryItem;

        public GalleryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_gallery, parent, false));
            itemView.setOnClickListener(this);

            mImage = (ImageView) itemView.findViewById(R.id.gallery_image_placeholder);
            mTitle = (TextView) itemView.findViewById(R.id.title_placeholder);
            mArtist = (TextView) itemView.findViewById(R.id.artist_placeholder);
            mGalleryName = (TextView) itemView.findViewById(R.id.gallery_name_placeholder);
            mRightArrow = (ImageView) itemView.findViewById(R.id.right_arrow);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "GalleryRecycler clicked!", Toast.LENGTH_SHORT).show();
        }

        public void bind(GalleryItem galleryItem) {
            mGalleryItem = galleryItem;

            mTitle.setText(mGalleryItem.getTitle());
            mArtist.setText(mGalleryItem.getArtist());
            mGalleryName.setText(mGalleryItem.getGalleryName());
        }

    }

    private class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> {
        private List<GalleryItem> mGalleryItems;

        public GalleryAdapter(List<GalleryItem> items) {
            mGalleryItems = items;
        }

        @Override
        public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new GalleryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(GalleryHolder holder, int position) {
            GalleryItem item = mGalleryItems.get(position);
            holder.bind(item);
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }

        public void setGalleryItems(List<GalleryItem> items) {
            mGalleryItems = items;
        }
    }

}
