package com.arte.photoapp.fragments;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.arte.photoapp.R;
import com.arte.photoapp.activities.PhotoDetailActivity;
import com.arte.photoapp.activities.PhotoListActivity;
import com.arte.photoapp.model.Photo;
import com.arte.photoapp.network.RequestQueueManager;

public class PhotoDetailFragment extends Fragment {

    public static final String ARG_PHOTO_ID = "photo_id";

    private Photo mPhoto;
    private NetworkImageView mImage;

    public PhotoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PHOTO_ID)) {
            String photoId = getArguments().getString(ARG_PHOTO_ID);
            // TODO get photo from API
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail, container, false);

        mImage = (NetworkImageView) rootView.findViewById(R.id.photo_image);
        if (mPhoto != null) {
            loadPhotoDetails(mPhoto);
        }

        return rootView;
    }

    private void loadPhotoDetails(Photo photo) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(mPhoto.getTitle());
        }
        mImage.setImageUrl(photo.getUrl(), RequestQueueManager.getInstance(activity).getImageLoader());
    }
}
