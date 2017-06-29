package com.fall3240summer17.android.pocketturchin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class PocketTurchinActivity extends AppCompatActivity {

    private static final int FRAGMENT_GALLERY = 0;
    private static final int FRAGMENT_FAVS = 1;
    private static final int FRAGMENT_ABOUT = 2;

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_gallery:
                    updateFragment(FRAGMENT_GALLERY);
                    return true;
                case R.id.navigation_favs:
                    updateFragment(FRAGMENT_FAVS);
                    return true;
                case R.id.navigation_about:
                    updateFragment(FRAGMENT_ABOUT);
                    return true;
            }
            return false;
        }

    };

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() != 0) {
            boolean galleryReturn = mFragmentManager.popBackStackImmediate("GalleryDetailFragment", 1);
            boolean favReturn = mFragmentManager.popBackStackImmediate("FavsFragment", 1);
            if (galleryReturn)
                updateFragment(FRAGMENT_GALLERY);
            else if (favReturn)
                updateFragment(FRAGMENT_FAVS);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_turchin);
        updateFragment(FRAGMENT_GALLERY);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void updateFragment(int fragmentView) {
        if (fragmentView == FRAGMENT_GALLERY) {
            mFragment = new GalleryFragment();
        } else if (fragmentView == FRAGMENT_FAVS) {
            mFragment = new FavsFragment();
        } else if (fragmentView == FRAGMENT_ABOUT){
            mFragment = new AboutFragment();
        }
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(R.id.content, mFragment)
                .commit();
    }

}
