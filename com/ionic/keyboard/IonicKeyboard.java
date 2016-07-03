package com.ionic.keyboard;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.ExecutorService;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class IonicKeyboard extends CordovaPlugin {

    /* renamed from: com.ionic.keyboard.IonicKeyboard.1 */
    class C00001 implements OnGlobalLayoutListener {
        int previousHeightDiff;
        final /* synthetic */ IonicKeyboard this$0;
        final /* synthetic */ CordovaWebView val$appView;
        final /* synthetic */ float val$density;
        final /* synthetic */ View val$rootView;

        C00001(IonicKeyboard ionicKeyboard, View view, float f, CordovaWebView cordovaWebView) {
            View view2 = view;
            float f2 = f;
            CordovaWebView cordovaWebView2 = cordovaWebView;
            IonicKeyboard ionicKeyboard2 = ionicKeyboard;
            this.this$0 = ionicKeyboard2;
            View view3 = view2;
            this.val$rootView = view3;
            float f3 = f2;
            this.val$density = f3;
            CordovaWebView cordovaWebView3 = cordovaWebView2;
            this.val$appView = cordovaWebView3;
            this.previousHeightDiff = 0;
        }

        public void onGlobalLayout() {
            Rect rect = r7;
            Rect rect2 = new Rect();
            Rect r = rect;
            this.val$rootView.getWindowVisibleDisplayFrame(r);
            int pixelHeightDiff = (int) (((float) (this.val$rootView.getRootView().getHeight() - (r.bottom - r.top))) / this.val$density);
            if (pixelHeightDiff > 100 && pixelHeightDiff != this.previousHeightDiff) {
                this.val$appView.sendJavascript("cordova.plugins.Keyboard.isVisible = true");
                CordovaWebView cordovaWebView = this.val$appView;
                StringBuilder stringBuilder = r7;
                StringBuilder stringBuilder2 = new StringBuilder();
                cordovaWebView.sendJavascript(stringBuilder.append("cordova.fireWindowEvent('native.keyboardshow', { 'keyboardHeight':").append(Integer.toString(pixelHeightDiff)).append("});").toString());
                cordovaWebView = this.val$appView;
                stringBuilder = r7;
                stringBuilder2 = new StringBuilder();
                cordovaWebView.sendJavascript(stringBuilder.append("cordova.fireWindowEvent('native.showkeyboard', { 'keyboardHeight':").append(Integer.toString(pixelHeightDiff)).append("});").toString());
            } else if (pixelHeightDiff != this.previousHeightDiff && this.previousHeightDiff - pixelHeightDiff > 100) {
                this.val$appView.sendJavascript("cordova.plugins.Keyboard.isVisible = false");
                this.val$appView.sendJavascript("cordova.fireWindowEvent('native.keyboardhide')");
                this.val$appView.sendJavascript("cordova.fireWindowEvent('native.hidekeyboard')");
            }
            this.previousHeightDiff = pixelHeightDiff;
        }
    }

    /* renamed from: com.ionic.keyboard.IonicKeyboard.2 */
    class C00012 implements Runnable {
        final /* synthetic */ IonicKeyboard this$0;
        final /* synthetic */ CallbackContext val$callbackContext;

        C00012(IonicKeyboard ionicKeyboard, CallbackContext callbackContext) {
            CallbackContext callbackContext2 = callbackContext;
            IonicKeyboard ionicKeyboard2 = ionicKeyboard;
            this.this$0 = ionicKeyboard2;
            CallbackContext callbackContext3 = callbackContext2;
            this.val$callbackContext = callbackContext3;
        }

        public void run() {
            InputMethodManager inputManager = (InputMethodManager) this.this$0.cordova.getActivity().getSystemService("input_method");
            View v = this.this$0.cordova.getActivity().getCurrentFocus();
            if (v == null) {
                this.val$callbackContext.error("No current focus");
                return;
            }
            boolean hideSoftInputFromWindow = inputManager.hideSoftInputFromWindow(v.getWindowToken(), 2);
            this.val$callbackContext.success();
        }
    }

    /* renamed from: com.ionic.keyboard.IonicKeyboard.3 */
    class C00023 implements Runnable {
        final /* synthetic */ IonicKeyboard this$0;
        final /* synthetic */ CallbackContext val$callbackContext;

        C00023(IonicKeyboard ionicKeyboard, CallbackContext callbackContext) {
            CallbackContext callbackContext2 = callbackContext;
            IonicKeyboard ionicKeyboard2 = ionicKeyboard;
            this.this$0 = ionicKeyboard2;
            CallbackContext callbackContext3 = callbackContext2;
            this.val$callbackContext = callbackContext3;
        }

        public void run() {
            ((InputMethodManager) this.this$0.cordova.getActivity().getSystemService("input_method")).toggleSoftInput(0, 1);
            this.val$callbackContext.success();
        }
    }

    public IonicKeyboard() {
        CordovaPlugin cordovaPlugin = this;
    }

    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        CordovaInterface cordova = cordovaInterface;
        CordovaWebView webView = cordovaWebView;
        super.initialize(cordova, webView);
        DisplayMetrics displayMetrics = r14;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        DisplayMetrics dm = displayMetrics;
        cordova.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        CordovaWebView appView = webView;
        View rootView = cordova.getActivity().getWindow().getDecorView().findViewById(16908290).getRootView();
        C00001 c00001 = r14;
        C00001 c000012 = new C00001(this, rootView, density, appView);
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(c00001);
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        IonicKeyboard this = this;
        String action = str;
        JSONArray args = jSONArray;
        CallbackContext callbackContext2 = callbackContext;
        ExecutorService threadPool;
        if ("close".equals(action)) {
            threadPool = this.cordova.getThreadPool();
            C00012 c00012 = r9;
            C00012 c000122 = new C00012(this, callbackContext2);
            threadPool.execute(c00012);
            return 1;
        } else if (!"show".equals(action)) {
            return null;
        } else {
            threadPool = this.cordova.getThreadPool();
            C00023 c00023 = r9;
            C00023 c000232 = new C00023(this, callbackContext2);
            threadPool.execute(c00023);
            return 1;
        }
    }
}
