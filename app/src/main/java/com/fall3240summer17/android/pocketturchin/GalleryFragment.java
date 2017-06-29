package com.fall3240summer17.android.pocketturchin;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private RecyclerView mGalleryRecycler;
    private GalleryAdapter mAdapter;

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

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        GalleryList galleryList = GalleryList.get(getActivity());
        List<GalleryItem> galleryItems = galleryList.getGalleryItems();

        if(galleryItems.size() == 0) {
            populateData();
        }

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
            //Toast.makeText(getActivity(), "GalleryRecycler clicked!", Toast.LENGTH_SHORT).show();

            Fragment fragment = GalleryDetailFragment.newInstance(mGalleryItem.getId());
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment)
                    .addToBackStack("GalleryDetailFragment")
                    .commit();
        }

        public void bind(GalleryItem galleryItem) {
            mGalleryItem = galleryItem;

            mImage.setImageResource(galleryItem.getPicture());
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

    private void populateData() {
        GalleryList galleryList = GalleryList.get(getActivity());


        GalleryItem item = new GalleryItem();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 3);
        Date date = cal.getTime();

        item.setPicture(R.drawable.collective_vigilance_1200x630);
        item.setArtist("Maggie Flanigan");
        item.setTitle("The Banks of the New River");
        item.setGalleryName("Mayer Gallery");
        item.setFavorited(false);
        item.setDescription(getString(R.string.gallery_description_1));
        item.setBeginDate(date);

        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 29);
        date = cal.getTime();

        item.setEndDate(date);


        galleryList.addGalleryItem(item);

        ///////////////////////////////////////////////////////////////

        GalleryItem item2 = new GalleryItem();

        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.APRIL);
        cal.set(Calendar.DAY_OF_MONTH, 7);
        date = cal.getTime();

        item2.setPicture(R.drawable.wayne_trapp_a_life_in_the_arts_1200x630);
        item2.setArtist("Wayne Trapp");
        item2.setTitle("A Life in the Arts");
        item2.setGalleryName("Bridge Gallery");
        item2.setFavorited(false);
        item2.setDescription(getString(R.string.gallery_description_2));
        item2.setBeginDate(date);

        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 4);
        date = cal.getTime();

        item2.setEndDate(date);

        galleryList.addGalleryItem(item2);
    }

}
