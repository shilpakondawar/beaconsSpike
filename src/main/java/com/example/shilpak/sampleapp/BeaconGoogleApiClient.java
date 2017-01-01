package com.example.shilpak.sampleapp;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

//import com.google.android.gms.drive.Drive;
//import com.google.android.gms.drive.query.Filters;
//import com.google.android.gms.drive.query.Query;
//import com.google.android.gms.drive.query.SearchableField;
//
//import com.google.android.gms.drive.Drive;
//import com.google.android.gms.drive.DriveApi;
//import com.google.android.gms.drive.query.Filters;

public class BeaconGoogleApiClient implements OnConnectionFailedListener{
  private static final String TAG = BeaconGoogleApiClient.class.getSimpleName();
  private GoogleApiClient mGoogleApiClient;
  private FragmentActivity activity;

  public BeaconGoogleApiClient(FragmentActivity activity){
    this.activity = activity;
  }

  public void initialiseGoogleSign() {
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
    mGoogleApiClient = new GoogleApiClient.Builder(activity)
        .enableAutoManage(activity, this)
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//        .addApi(Drive.API)
//        .addScope(Drive.SCOPE_FILE)
        .build();
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Log.e(TAG, "Connection failed with google client");
  }

  public void signOut() {
    ;
  }

  public void getSignInIntent() {
    Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
  }

  public GoogleApiClient getGoogleClient() {
    return mGoogleApiClient;
  }


//  public void getFileFromDrive() {
//    Query query = new Query.Builder()
//        .addFilter(Filters.eq(SearchableField.TITLE, "no_resut"))
//        .build();
//    // Invoke the query asynchronously with a callback method
//    Drive.DriveApi.query(mGoogleApiClient, query)
//        .setResultCallback(new ResultCallback<DriveApi.MetadataBufferResult>() {
//          @Override
//          public void onResult(DriveApi.MetadataBufferResult result) {
//            Log.d("**In result", result.getMetadataBuffer().toString());
//            // Success! Handle the query result.
//            // ...
//          }
//        });
//  }
}
