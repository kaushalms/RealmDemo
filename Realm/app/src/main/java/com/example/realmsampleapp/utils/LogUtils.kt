package com.example.realmsampleapp.utils

import android.util.Log
import com.example.realmsampleapp.BuildConfig

@Suppress("PointlessBooleanExpression")
object LogUtils {
    private const val TAG_PREFIX = "UIP-"
    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun v(tag: String, msg: String): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.v(TAG_PREFIX + tag, msg)
    }

    /**
     * Send a {@link Log#VERBOSE} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun v(tag: String, msg: String, tr: Throwable): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.v(TAG_PREFIX + tag, msg, tr)
    }

    /**
     * Send a {@link Log#DEBUG} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun d(tag: String, msg: String): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.d(TAG_PREFIX + tag, msg)
    }

    /**
     * Send a {@link Log#DEBUG} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun d(tag: String, msg: String, tr: Throwable): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.d(TAG_PREFIX + tag, msg, tr)
    }

    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun i(tag: String, msg: String): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.i(TAG_PREFIX + tag, msg)
    }

    /**
     * Send a {@link Log#INFO} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun i(tag: String, msg: String, tr: Throwable): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.i(TAG_PREFIX + tag, msg, tr)
    }

    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun w(tag: String, msg: String): Int {
        return Log.w(TAG_PREFIX + tag, msg)
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun w(tag: String, msg: String, tr: Throwable): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.w(TAG_PREFIX + tag, msg, tr)
    }

    /**
     * Send a {@link Log#WARN} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param tr An exception to log
     */
    fun w(tag: String, tr: Throwable): Int {
        if (!BuildConfig.DEBUG) {
            return 0
        }
        return Log.w(TAG_PREFIX + tag, tr)
    }

    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun e(tag: String, msg: String): Int {
        return Log.e(TAG_PREFIX + tag, msg)
    }

    /**
     * Send a {@link Log#ERROR} log message and log the exception.
     *
     * @param tag Used to identify the source of a log message. It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     */
    fun e(tag: String, msg: String, tr: Throwable): Int {
        return Log.e(TAG_PREFIX + tag, msg, tr)
    }
    // end region
}