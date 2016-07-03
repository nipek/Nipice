package org.apache.cordova.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.res.Configuration;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import org.apache.cordova.BuildConfig;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SplashScreen extends CordovaPlugin {
    private static final boolean HAS_BUILT_IN_SPLASH_SCREEN;
    private static final String LOG_TAG = "SplashScreen";
    private static boolean firstShow;
    private static ProgressDialog spinnerDialog;
    private static Dialog splashDialog;
    private int orientation;
    private ImageView splashImageView;

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.1 */
    class C00311 implements Runnable {
        final /* synthetic */ SplashScreen this$0;

        C00311(SplashScreen splashScreen) {
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
        }

        public void run() {
            Object postMessage = this.this$0.webView.postMessage("splashscreen", "hide");
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.2 */
    class C00322 implements Runnable {
        final /* synthetic */ SplashScreen this$0;

        C00322(SplashScreen splashScreen) {
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
        }

        public void run() {
            Object postMessage = this.this$0.webView.postMessage("splashscreen", "show");
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.3 */
    class C00333 implements Runnable {
        final /* synthetic */ SplashScreen this$0;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        C00333(SplashScreen splashScreen, String str, String str2) {
            String str3 = str;
            String str4 = str2;
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
            String str5 = str3;
            this.val$title = str5;
            str5 = str4;
            this.val$message = str5;
        }

        public void run() {
            this.this$0.spinnerStart(this.val$title, this.val$message);
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.4 */
    class C00344 implements Runnable {
        final /* synthetic */ SplashScreen this$0;

        C00344(SplashScreen splashScreen) {
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
        }

        public void run() {
            C00344 this = this;
            if (SplashScreen.splashDialog != null && SplashScreen.splashDialog.isShowing()) {
                SplashScreen.splashDialog.dismiss();
                Dialog access$102 = SplashScreen.access$102(null);
                ImageView access$202 = SplashScreen.access$202(this.this$0, null);
            }
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.5 */
    class C00365 implements Runnable {
        final /* synthetic */ SplashScreen this$0;
        final /* synthetic */ int val$drawableId;
        final /* synthetic */ boolean val$hideAfterDelay;
        final /* synthetic */ int val$splashscreenTime;

        /* renamed from: org.apache.cordova.splashscreen.SplashScreen.5.1 */
        class C00351 implements Runnable {
            final /* synthetic */ C00365 this$1;

            C00351(C00365 c00365) {
                C00365 c003652 = c00365;
                this.this$1 = c003652;
            }

            public void run() {
                this.this$1.this$0.removeSplashScreen();
            }
        }

        C00365(SplashScreen splashScreen, int i, boolean z, int i2) {
            int i3 = i;
            boolean z2 = z;
            int i4 = i2;
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
            int i5 = i3;
            this.val$drawableId = i5;
            boolean z3 = z2;
            this.val$hideAfterDelay = z3;
            i5 = i4;
            this.val$splashscreenTime = i5;
        }

        public void run() {
            Display display = this.this$0.cordova.getActivity().getWindowManager().getDefaultDisplay();
            Context context = this.this$0.webView.getContext();
            SplashScreen splashScreen = this.this$0;
            ImageView imageView = r10;
            ImageView imageView2 = new ImageView(context);
            ImageView access$202 = SplashScreen.access$202(splashScreen, imageView);
            this.this$0.splashImageView.setImageResource(this.val$drawableId);
            LayoutParams layoutParams = r10;
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
            LayoutParams layoutParams3 = layoutParams;
            this.this$0.splashImageView.setLayoutParams(layoutParams3);
            this.this$0.splashImageView.setMinimumHeight(display.getHeight());
            this.this$0.splashImageView.setMinimumWidth(display.getWidth());
            this.this$0.splashImageView.setBackgroundColor(this.this$0.preferences.getInteger("backgroundColor", -16777216));
            if (this.this$0.isMaintainAspectRatio()) {
                this.this$0.splashImageView.setScaleType(ScaleType.CENTER_CROP);
            } else {
                this.this$0.splashImageView.setScaleType(ScaleType.FIT_XY);
            }
            Dialog dialog = r10;
            Dialog dialog2 = new Dialog(context, 16973840);
            dialog = SplashScreen.access$102(dialog);
            if ((this.this$0.cordova.getActivity().getWindow().getAttributes().flags & 1024) == 1024) {
                SplashScreen.splashDialog.getWindow().setFlags(1024, 1024);
            }
            SplashScreen.splashDialog.setContentView(this.this$0.splashImageView);
            SplashScreen.splashDialog.setCancelable(SplashScreen.HAS_BUILT_IN_SPLASH_SCREEN);
            SplashScreen.splashDialog.show();
            if (this.val$hideAfterDelay) {
                Handler handler = r10;
                Handler handler2 = new Handler();
                handler = handler;
                C00351 c00351 = r10;
                C00351 c003512 = new C00351(this);
                boolean postDelayed = handler.postDelayed(c00351, (long) this.val$splashscreenTime);
            }
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.6 */
    class C00386 implements Runnable {
        final /* synthetic */ SplashScreen this$0;
        final /* synthetic */ String val$message;
        final /* synthetic */ String val$title;

        /* renamed from: org.apache.cordova.splashscreen.SplashScreen.6.1 */
        class C00371 implements OnCancelListener {
            final /* synthetic */ C00386 this$1;

            C00371(C00386 c00386) {
                C00386 c003862 = c00386;
                this.this$1 = c003862;
            }

            public void onCancel(DialogInterface dialogInterface) {
                DialogInterface dialog = dialogInterface;
                ProgressDialog access$702 = SplashScreen.access$702(null);
            }
        }

        C00386(SplashScreen splashScreen, String str, String str2) {
            String str3 = str;
            String str4 = str2;
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
            String str5 = str3;
            this.val$title = str5;
            str5 = str4;
            this.val$message = str5;
        }

        public void run() {
            this.this$0.spinnerStop();
            Context context = this.this$0.webView.getContext();
            CharSequence charSequence = this.val$title;
            CharSequence charSequence2 = this.val$message;
            C00371 c00371 = r9;
            C00371 c003712 = new C00371(this);
            ProgressDialog access$702 = SplashScreen.access$702(ProgressDialog.show(context, charSequence, charSequence2, true, true, c00371));
        }
    }

    /* renamed from: org.apache.cordova.splashscreen.SplashScreen.7 */
    class C00397 implements Runnable {
        final /* synthetic */ SplashScreen this$0;

        C00397(SplashScreen splashScreen) {
            SplashScreen splashScreen2 = splashScreen;
            this.this$0 = splashScreen2;
        }

        public void run() {
            if (SplashScreen.spinnerDialog != null && SplashScreen.spinnerDialog.isShowing()) {
                SplashScreen.spinnerDialog.dismiss();
                ProgressDialog access$702 = SplashScreen.access$702(null);
            }
        }
    }

    public SplashScreen() {
        CordovaPlugin cordovaPlugin = this;
    }

    static /* synthetic */ Dialog access$102(Dialog dialog) {
        Dialog dialog2 = dialog;
        dialog2 = r3;
        splashDialog = r3;
        return dialog2;
    }

    static /* synthetic */ ImageView access$202(SplashScreen splashScreen, ImageView imageView) {
        SplashScreen splashScreen2 = splashScreen;
        ImageView imageView2 = imageView;
        ImageView imageView3 = r6;
        r5.splashImageView = r6;
        return imageView3;
    }

    static /* synthetic */ ProgressDialog access$702(ProgressDialog progressDialog) {
        ProgressDialog progressDialog2 = progressDialog;
        progressDialog2 = r3;
        spinnerDialog = r3;
        return progressDialog2;
    }

    static {
        HAS_BUILT_IN_SPLASH_SCREEN = Integer.valueOf(CordovaWebView.CORDOVA_VERSION.split("\\.")[0]).intValue() < 4 ? true : HAS_BUILT_IN_SPLASH_SCREEN;
        firstShow = true;
    }

    private View getView() {
        try {
            return (View) this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (Exception e) {
            Exception exception = e;
            return (View) this.webView;
        }
    }

    protected void pluginInitialize() {
        SplashScreen this = this;
        if (!HAS_BUILT_IN_SPLASH_SCREEN && firstShow) {
            getView().setVisibility(4);
            int drawableId = this.preferences.getInteger("SplashDrawableId", 0);
            if (r3 == 0) {
                String splashResource = this.preferences.getString(LOG_TAG, "screen");
                if (splashResource != null) {
                    drawableId = this.cordova.getActivity().getResources().getIdentifier(splashResource, "drawable", this.cordova.getActivity().getClass().getPackage().getName());
                    if (drawableId == 0) {
                        drawableId = this.cordova.getActivity().getResources().getIdentifier(splashResource, "drawable", this.cordova.getActivity().getPackageName());
                    }
                    this.preferences.set("SplashDrawableId", drawableId);
                }
            }
            this.orientation = this.cordova.getActivity().getResources().getConfiguration().orientation;
            firstShow = HAS_BUILT_IN_SPLASH_SCREEN;
            loadSpinner();
            showSplashScreen(true);
        }
    }

    private boolean isMaintainAspectRatio() {
        return this.preferences.getBoolean("SplashMaintainAspectRatio", HAS_BUILT_IN_SPLASH_SCREEN);
    }

    public void onPause(boolean z) {
        SplashScreen this = this;
        boolean multitasking = z;
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen();
        }
    }

    public void onDestroy() {
        SplashScreen this = this;
        if (!HAS_BUILT_IN_SPLASH_SCREEN) {
            removeSplashScreen();
        }
    }

    public boolean execute(String str, JSONArray jSONArray, CallbackContext callbackContext) throws JSONException {
        SplashScreen this = this;
        String action = str;
        JSONArray args = jSONArray;
        CallbackContext callbackContext2 = callbackContext;
        Activity activity;
        if (action.equals("hide")) {
            activity = this.cordova.getActivity();
            C00311 c00311 = r12;
            C00311 c003112 = new C00311(this);
            activity.runOnUiThread(c00311);
        } else if (action.equals("show")) {
            activity = this.cordova.getActivity();
            C00322 c00322 = r12;
            C00322 c003222 = new C00322(this);
            activity.runOnUiThread(c00322);
        } else if (!action.equals("spinnerStart")) {
            return null;
        } else {
            if (!HAS_BUILT_IN_SPLASH_SCREEN) {
                String title = args.getString(0);
                String message = args.getString(1);
                activity = this.cordova.getActivity();
                C00333 c00333 = r12;
                C00333 c003332 = new C00333(this, title, message);
                activity.runOnUiThread(c00333);
            }
        }
        callbackContext2.success();
        return 1;
    }

    public Object onMessage(String str, Object obj) {
        SplashScreen this = this;
        String id = str;
        Object data = obj;
        if (HAS_BUILT_IN_SPLASH_SCREEN) {
            return null;
        }
        if ("splashscreen".equals(id)) {
            if ("hide".equals(data.toString())) {
                removeSplashScreen();
            } else {
                showSplashScreen(HAS_BUILT_IN_SPLASH_SCREEN);
            }
        } else if ("spinner".equals(id)) {
            if ("stop".equals(data.toString())) {
                spinnerStop();
                getView().setVisibility(0);
            }
        } else if ("onReceivedError".equals(id)) {
            spinnerStop();
        }
        return null;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration newConfig = configuration;
        if (newConfig.orientation != this.orientation) {
            this.orientation = newConfig.orientation;
            if (this.splashImageView != null) {
                int drawableId = this.preferences.getInteger("SplashDrawableId", 0);
                if (drawableId != 0) {
                    this.splashImageView.setImageDrawable(this.cordova.getActivity().getResources().getDrawable(drawableId));
                }
            }
        }
    }

    private void removeSplashScreen() {
        Activity activity = this.cordova.getActivity();
        C00344 c00344 = r5;
        C00344 c003442 = new C00344(this);
        activity.runOnUiThread(c00344);
    }

    private void showSplashScreen(boolean z) {
        boolean hideAfterDelay = z;
        int splashscreenTime = this.preferences.getInteger("SplashScreenDelay", 3000);
        int drawableId = this.preferences.getInteger("SplashDrawableId", 0);
        if (splashDialog != null && splashDialog.isShowing()) {
            return;
        }
        if (drawableId != 0 && (splashscreenTime > 0 || !hideAfterDelay)) {
            Activity activity = this.cordova.getActivity();
            C00365 c00365 = r11;
            C00365 c003652 = new C00365(this, drawableId, hideAfterDelay, splashscreenTime);
            activity.runOnUiThread(c00365);
        }
    }

    private void loadSpinner() {
        String loading = null;
        if (this.webView.canGoBack()) {
            loading = this.preferences.getString("LoadingDialog", null);
        } else {
            loading = this.preferences.getString("LoadingPageDialog", null);
        }
        if (loading != null) {
            String title = BuildConfig.VERSION_NAME;
            String message = "Loading Application...";
            if (loading.length() > 0) {
                int comma = loading.indexOf(44);
                if (comma > 0) {
                    title = loading.substring(0, comma);
                    message = loading.substring(comma + 1);
                } else {
                    title = BuildConfig.VERSION_NAME;
                    message = loading;
                }
            }
            spinnerStart(title, message);
        }
    }

    private void spinnerStart(String str, String str2) {
        String title = str;
        String message = str2;
        Activity activity = this.cordova.getActivity();
        C00386 c00386 = r9;
        C00386 c003862 = new C00386(this, title, message);
        activity.runOnUiThread(c00386);
    }

    private void spinnerStop() {
        Activity activity = this.cordova.getActivity();
        C00397 c00397 = r5;
        C00397 c003972 = new C00397(this);
        activity.runOnUiThread(c00397);
    }
}
