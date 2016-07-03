package org.apache.cordova;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.widget.EditText;

public class CordovaDialogsHelper {
    private final Context context;
    private AlertDialog lastHandledDialog;

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.1 */
    class C00081 implements OnClickListener {
        final /* synthetic */ Result val$result;

        C00081(Result result) {
            this.val$result = result;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.gotResult(true, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.2 */
    class C00092 implements OnCancelListener {
        final /* synthetic */ Result val$result;

        C00092(Result result) {
            this.val$result = result;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$result.gotResult(false, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.3 */
    class C00103 implements OnKeyListener {
        final /* synthetic */ Result val$result;

        C00103(Result result) {
            this.val$result = result;
        }

        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode != 4) {
                return true;
            }
            this.val$result.gotResult(true, null);
            return false;
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.4 */
    class C00114 implements OnClickListener {
        final /* synthetic */ Result val$result;

        C00114(Result result) {
            this.val$result = result;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.gotResult(true, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.5 */
    class C00125 implements OnClickListener {
        final /* synthetic */ Result val$result;

        C00125(Result result) {
            this.val$result = result;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.gotResult(false, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.6 */
    class C00136 implements OnCancelListener {
        final /* synthetic */ Result val$result;

        C00136(Result result) {
            this.val$result = result;
        }

        public void onCancel(DialogInterface dialog) {
            this.val$result.gotResult(false, null);
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.7 */
    class C00147 implements OnKeyListener {
        final /* synthetic */ Result val$result;

        C00147(Result result) {
            this.val$result = result;
        }

        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode != 4) {
                return true;
            }
            this.val$result.gotResult(false, null);
            return false;
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.8 */
    class C00158 implements OnClickListener {
        final /* synthetic */ EditText val$input;
        final /* synthetic */ Result val$result;

        C00158(EditText editText, Result result) {
            this.val$input = editText;
            this.val$result = result;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.gotResult(true, this.val$input.getText().toString());
        }
    }

    /* renamed from: org.apache.cordova.CordovaDialogsHelper.9 */
    class C00169 implements OnClickListener {
        final /* synthetic */ Result val$result;

        C00169(Result result) {
            this.val$result = result;
        }

        public void onClick(DialogInterface dialog, int which) {
            this.val$result.gotResult(false, null);
        }
    }

    public interface Result {
        void gotResult(boolean z, String str);
    }

    public CordovaDialogsHelper(Context context) {
        this.context = context;
    }

    public void showAlert(String message, Result result) {
        Builder dlg = new Builder(this.context);
        dlg.setMessage(message);
        dlg.setTitle("Alert");
        dlg.setCancelable(true);
        dlg.setPositiveButton(17039370, new C00081(result));
        dlg.setOnCancelListener(new C00092(result));
        dlg.setOnKeyListener(new C00103(result));
        this.lastHandledDialog = dlg.show();
    }

    public void showConfirm(String message, Result result) {
        Builder dlg = new Builder(this.context);
        dlg.setMessage(message);
        dlg.setTitle("Confirm");
        dlg.setCancelable(true);
        dlg.setPositiveButton(17039370, new C00114(result));
        dlg.setNegativeButton(17039360, new C00125(result));
        dlg.setOnCancelListener(new C00136(result));
        dlg.setOnKeyListener(new C00147(result));
        this.lastHandledDialog = dlg.show();
    }

    public void showPrompt(String message, String defaultValue, Result result) {
        Builder dlg = new Builder(this.context);
        dlg.setMessage(message);
        EditText input = new EditText(this.context);
        if (defaultValue != null) {
            input.setText(defaultValue);
        }
        dlg.setView(input);
        dlg.setCancelable(false);
        dlg.setPositiveButton(17039370, new C00158(input, result));
        dlg.setNegativeButton(17039360, new C00169(result));
        this.lastHandledDialog = dlg.show();
    }

    public void destroyLastDialog() {
        if (this.lastHandledDialog != null) {
            this.lastHandledDialog.cancel();
        }
    }
}
