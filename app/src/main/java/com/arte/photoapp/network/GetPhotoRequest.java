package com.arte.photoapp.network;

import android.content.Context;

import com.arte.photoapp.model.Photo;

public class GetPhotoRequest {

    public interface Callbacks {
        void onGetPhotoSuccess(Photo photo);

        void onGetPhotoError();
    }

    private Context mContext;
    private Callbacks mCallbacks;

    public GetPhotoRequest(Context context, Callbacks callbacks) {
        mContext = context;
        mCallbacks = callbacks;
    }

    public void execute() {
        // TODO do JSONObject volley request
        // TODO transform JSONObject to Photo
        // TODO call mCallbacks methods
    }
}
