package com.ionicframework.nipice257132;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;

public class MainActivity extends CordovaActivity {
    public MainActivity() {
        CordovaActivity cordovaActivity = this;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadUrl(this.launchUrl);
    }
}
