package org.apache.cordova.device;

import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import java.util.TimeZone;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Device extends CordovaPlugin {
    private static final String AMAZON_DEVICE = "Amazon";
    private static final String AMAZON_PLATFORM = "amazon-fireos";
    private static final String ANDROID_PLATFORM = "Android";
    public static final String TAG = "Device";
    public static String platform;
    public static String uuid;

    public Device() {
        CordovaPlugin cordovaPlugin = this;
    }

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        super.initialize(cordovaInterface, cordovaWebView);
        uuid = getUuid();
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        Device this = this;
        JSONArray args = jSONArray;
        CallbackContext callbackContext2 = callbackContext;
        if (!str.equals("getDeviceInfo")) {
            return null;
        }
        JSONObject jSONObject = r8;
        JSONObject jSONObject2 = new JSONObject();
        JSONObject r = jSONObject;
        jSONObject = r.put("uuid", uuid);
        jSONObject = r.put("version", getOSVersion());
        jSONObject = r.put("platform", getPlatform());
        jSONObject = r.put("model", getModel());
        jSONObject = r.put("manufacturer", getManufacturer());
        callbackContext2.success(r);
        return 1;
    }

    public String getPlatform() {
        String platform;
        if (isAmazonDevice()) {
            platform = AMAZON_PLATFORM;
        } else {
            platform = ANDROID_PLATFORM;
        }
        return platform;
    }

    public String getUuid() {
        String uuid = Secure.getString(this.cordova.getActivity().getContentResolver(), "android_id");
        return this;
    }

    public String getModel() {
        String model = Build.MODEL;
        return this;
    }

    public String getProductName() {
        String productname = Build.PRODUCT;
        return this;
    }

    public String getManufacturer() {
        String manufacturer = Build.MANUFACTURER;
        return this;
    }

    public String getOSVersion() {
        String osversion = VERSION.RELEASE;
        return this;
    }

    public String getSDKVersion() {
        String sdkversion = VERSION.SDK;
        return this;
    }

    public String getTimeZoneID() {
        return TimeZone.getDefault().getID();
    }

    public boolean isAmazonDevice() {
        if (Build.MANUFACTURER.equals(AMAZON_DEVICE)) {
            return 1;
        }
        return null;
    }
}
