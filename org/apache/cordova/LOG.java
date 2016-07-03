package org.apache.cordova;

import android.util.Log;

public class LOG {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static int LOGLEVEL = 0;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    static {
        LOGLEVEL = ERROR;
    }

    public static void setLogLevel(int logLevel) {
        LOGLEVEL = logLevel;
        Log.i("CordovaLog", "Changing log level to " + logLevel);
    }

    public static void setLogLevel(String logLevel) {
        if ("VERBOSE".equals(logLevel)) {
            LOGLEVEL = VERBOSE;
        } else if ("DEBUG".equals(logLevel)) {
            LOGLEVEL = DEBUG;
        } else if ("INFO".equals(logLevel)) {
            LOGLEVEL = INFO;
        } else if ("WARN".equals(logLevel)) {
            LOGLEVEL = WARN;
        } else if ("ERROR".equals(logLevel)) {
            LOGLEVEL = ERROR;
        }
        Log.i("CordovaLog", "Changing log level to " + logLevel + "(" + LOGLEVEL + ")");
    }

    public static boolean isLoggable(int logLevel) {
        return logLevel >= LOGLEVEL;
    }

    public static void m9v(String tag, String s) {
        if (VERBOSE >= LOGLEVEL) {
            Log.v(tag, s);
        }
    }

    public static void m0d(String tag, String s) {
        if (DEBUG >= LOGLEVEL) {
            Log.d(tag, s);
        }
    }

    public static void m6i(String tag, String s) {
        if (INFO >= LOGLEVEL) {
            Log.i(tag, s);
        }
    }

    public static void m12w(String tag, String s) {
        if (WARN >= LOGLEVEL) {
            Log.w(tag, s);
        }
    }

    public static void m3e(String tag, String s) {
        if (ERROR >= LOGLEVEL) {
            Log.e(tag, s);
        }
    }

    public static void m10v(String tag, String s, Throwable e) {
        if (VERBOSE >= LOGLEVEL) {
            Log.v(tag, s, e);
        }
    }

    public static void m1d(String tag, String s, Throwable e) {
        if (DEBUG >= LOGLEVEL) {
            Log.d(tag, s, e);
        }
    }

    public static void m7i(String tag, String s, Throwable e) {
        if (INFO >= LOGLEVEL) {
            Log.i(tag, s, e);
        }
    }

    public static void m13w(String tag, String s, Throwable e) {
        if (WARN >= LOGLEVEL) {
            Log.w(tag, s, e);
        }
    }

    public static void m4e(String tag, String s, Throwable e) {
        if (ERROR >= LOGLEVEL) {
            Log.e(tag, s, e);
        }
    }

    public static void m11v(String tag, String s, Object... args) {
        if (VERBOSE >= LOGLEVEL) {
            Log.v(tag, String.format(s, args));
        }
    }

    public static void m2d(String tag, String s, Object... args) {
        if (DEBUG >= LOGLEVEL) {
            Log.d(tag, String.format(s, args));
        }
    }

    public static void m8i(String tag, String s, Object... args) {
        if (INFO >= LOGLEVEL) {
            Log.i(tag, String.format(s, args));
        }
    }

    public static void m14w(String tag, String s, Object... args) {
        if (WARN >= LOGLEVEL) {
            Log.w(tag, String.format(s, args));
        }
    }

    public static void m5e(String tag, String s, Object... args) {
        if (ERROR >= LOGLEVEL) {
            Log.e(tag, String.format(s, args));
        }
    }
}
