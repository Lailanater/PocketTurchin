package com.fall3240summer17.android.pocketturchin;


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

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavsFragment extends Fragment {

    private RecyclerView mFavRecycler;
    private GalleryAdapter mAdapter;

    public FavsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);

        mFavRecycler = (RecyclerView) v.findViewById(R.id.gallery_recycler_view);
        mFavRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        GalleryList galleryList = GalleryList.get(getActivity());
        List<GalleryItem> galleryItems = galleryList.getFavGalleryItems();

        if (mAdapter == null) {
            mAdapter = new GalleryAdapter(galleryItems);
            mFavRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.setGalleryItems(galleryItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class GalleryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImage;
        private TextView mTitle;

        private GalleryItem mGalleryItem;

        public GalleryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_fav, parent, false));
            itemView.setOnClickListener(this);

            mImage = (ImageView) itemView.findViewById(R.id.image_list_item_fav);
            mTitle = (TextView) itemView.findViewById(R.id.title_list_item_fav);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), "GalleryRecycler clicked!", Toast.LENGTH_SHORT).show();

            Fragment fragment = GalleryDetailFragment.newInstance(mGalleryItem.getId());
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content, fragment)
                    .addToBackStack("FavsFragment")
                    .commit();
        }

        public void bind(GalleryItem galleryItem) {
            mGalleryItem = galleryItem;

            mImage.setImageResource(galleryItem.getPicture());
            mTitle.setText(mGalleryItem.getTitle());
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
