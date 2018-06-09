package com.arkhipov.ayur.rbplants.any.utils;


import android.text.TextUtils;

public final class Log {

    private final static String mock = "\t\t\tDEBUGG";

    public static void w(Exception e) {
        android.util.Log.w(getLocation(), mock, e);
    }

    public static void w(String message, Exception e) {
        android.util.Log.w(getLocation(), message, e);
    }

    public static void l() {
        android.util.Log.d(getLocation(), "" + mock);
    }

    public static void d(String msg) {
        android.util.Log.d(getLocation(),/* getLocation() + */msg + mock);
    }

    private static String getLocation() {
        final String className = Log.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;

        for (int i = 0; i < traces.length; i++) {
            StackTraceElement trace = traces[i];

            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                    continue;
                }
            } catch (ClassNotFoundException e) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }
}
