package com.arte.photoapp.network;


import android.content.Context;

import com.arte.photoapp.model.Photo;

import java.util.List;

public class GetPhotoListRequest {

    public interface Callbacks {
        void onGetPhotoListSuccess(List<Photo> photoList);

        void onGetPhotoListError();
    }

    private Context mContext;
    private Callbacks mCallbacks;

    public GetPhotoListRequest(Context context, Callbacks callbacks) {
        mContext = context;
        mCallbacks = callbacks;
    }

    public void execute() {
        // TODO do JSONArray volley request
        // TODO transform JSONArray to List<Photo>
        // TODO call mCallbacks methods
    }
}
