package com.example.shilpak.sampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ViewSwitcher;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

  private static final String TAG = MainActivity.class.getSimpleName();
  private static final int RC_SIGN_IN = 100;
  private ViewSwitcher viewSwitcher;
  private BeaconGoogleApiClient beaconGoogleApiClient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.sign_in_button).setOnClickListener(this);
    findViewById(R.id.sign_out).setOnClickListener(this);

    viewSwitcher = (ViewSwitcher) findViewById(R.id.authentication_switcher);
    beaconGoogleApiClient = new BeaconGoogleApiClient(this);
    beaconGoogleApiClient.initialiseGoogleSign();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.sign_in_button:
        signIn();
        break;
      case R.id.sign_out:
        signOut();
    }
  }

  private void signOut() {
    Auth.GoogleSignInApi.signOut(beaconGoogleApiClient.getGoogleClient());
    viewSwitcher.showPrevious();
  }

  private void signIn() {
    Auth.GoogleSignInApi.getSignInIntent(beaconGoogleApiClient.getGoogleClient());
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(beaconGoogleApiClient.getGoogleClient());
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleSignInResult(result);
    }
  }

  private void handleSignInResult(GoogleSignInResult result) {
    Log.d(TAG, "handleSignInResult:" + result.isSuccess());
    if (result.isSuccess()) {
      GoogleSignInAccount acct = result.getSignInAccount();
      Log.d(TAG, "successful" + acct.getDisplayName());
      viewSwitcher.showNext();
//      beaconGoogleApiClient.getFileFromDrive();
    } else {
      Log.d(TAG, "Unsuccessful");
    }
  }

}
