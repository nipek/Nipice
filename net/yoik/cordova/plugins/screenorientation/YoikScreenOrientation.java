package net.yoik.cordova.plugins.screenorientation;

import android.app.Activity;
import android.util.Log;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class YoikScreenOrientation extends CordovaPlugin {
    private static final String LANDSCAPE = "landscape";
    private static final String LANDSCAPE_PRIMARY = "landscape-primary";
    private static final String LANDSCAPE_SECONDARY = "landscape-secondary";
    private static final String PORTRAIT = "portrait";
    private static final String PORTRAIT_PRIMARY = "portrait-primary";
    private static final String PORTRAIT_SECONDARY = "portrait-secondary";
    private static final String TAG = "YoikScreenOrientation";
    private static final String UNLOCKED = "unlocked";

    public YoikScreenOrientation() {
        CordovaPlugin cordovaPlugin = this;
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) {
        YoikScreenOrientation this = this;
        String action = str;
        JSONArray args = jSONArray;
        CallbackContext callbackContext2 = callbackContext;
        String str2 = TAG;
        StringBuilder stringBuilder = r7;
        StringBuilder stringBuilder2 = new StringBuilder();
        int d = Log.d(str2, stringBuilder.append("execute action: ").append(action).toString());
        if (action.equals("screenOrientation")) {
            return routeScreenOrientation(args, callbackContext2);
        }
        callbackContext2.error("action not recognised");
        return null;
    }

    private boolean routeScreenOrientation(JSONArray jSONArray, CallbackContext callbackContext) {
        YoikScreenOrientation this = this;
        JSONArray args = jSONArray;
        CallbackContext callbackContext2 = callbackContext;
        if (args.optString(0).equals("set")) {
            String orientation = args.optString(1);
            String str = TAG;
            StringBuilder stringBuilder = r9;
            StringBuilder stringBuilder2 = new StringBuilder();
            int d = Log.d(str, stringBuilder.append("Requested ScreenOrientation: ").append(orientation).toString());
            Activity activity = this.cordova.getActivity();
            if (orientation.equals(UNLOCKED)) {
                activity.setRequestedOrientation(-1);
            } else if (orientation.equals(LANDSCAPE_PRIMARY)) {
                activity.setRequestedOrientation(0);
            } else if (orientation.equals(PORTRAIT_PRIMARY)) {
                activity.setRequestedOrientation(1);
            } else if (orientation.equals(LANDSCAPE)) {
                activity.setRequestedOrientation(6);
            } else if (orientation.equals(PORTRAIT)) {
                activity.setRequestedOrientation(7);
            } else if (orientation.equals(LANDSCAPE_SECONDARY)) {
                activity.setRequestedOrientation(8);
            } else if (orientation.equals(PORTRAIT_SECONDARY)) {
                activity.setRequestedOrientation(9);
            }
            callbackContext2.success();
            return 1;
        }
        callbackContext2.error("ScreenOrientation not recognised");
        return null;
    }
}
