//package com.eyosiyas.sms_reader_android
//
//import android.Manifest
//import android.app.Activity
//import android.content.Context
//import android.content.pm.PackageManager
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import io.flutter.plugin.common.BinaryMessenger
//import io.flutter.plugin.common.MethodChannel
//
///**
// * Handles checking and requesting SMS permissions.
// */
//class PermissionHandler(applicationContext: Context, binaryMessenger: BinaryMessenger) {
//    companion object {
//        private const val REQUEST_CODE_SMS = 12345
//    }
//
//    private var pendingResult: MethodChannel.Result? = null
//
//    private var activity: Activity? = null
//
//    fun setActivity(activity: Activity) {
//        this.activity = activity
//    }
//
//    /**
//     * Check if both READ_SMS and RECEIVE_SMS are granted.
//     */
//    fun checkSmsPermissions(context: Context): Boolean {
//        val read = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS)
//        val receive = ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_SMS)
//        return read == PackageManager.PERMISSION_GRANTED &&
//                receive == PackageManager.PERMISSION_GRANTED
//    }
//
//    /**
//     * Request SMS permissions at runtime.
//     */
//    fun requestSmsPermissions(activity: Activity?, result: MethodChannel.Result) {
//        if (activity == null) {
//            result.error("NO_ACTIVITY", "SmsReaderPlugin: Activity is null", null)
//            return
//        }
//        if (checkSmsPermissions(activity)) {
//            result.success(true)
//            return
//        }
//        pendingResult = result
//        ActivityCompat.requestPermissions(
//            activity,
//            arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
//            REQUEST_CODE_SMS
//        )
//        // The result will be delivered via onRequestPermissionsResult
//        activity.overridePendingTransition(0, 0)
//        activity.application.registerActivityLifecycleCallbacks(object : android.app.Application.ActivityLifecycleCallbacks {
//            override fun onActivityRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//                if (requestCode == REQUEST_CODE_SMS) {
//                    val granted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
//                    pendingResult?.success(granted)
//                    pendingResult = null
//                    activity.application.unregisterActivityLifecycleCallbacks(this)
//                }
//            }
//            // Other lifecycle callbacks no-op
//            override fun onActivityCreated(p0: Activity, p1: android.os.Bundle?) {}
//            override fun onActivityStarted(p0: Activity) {}
//            override fun onActivityResumed(p0: Activity) {}
//            override fun onActivityPaused(p0: Activity) {}
//            override fun onActivityStopped(p0: Activity) {}
//            override fun onActivitySaveInstanceState(p0: Activity, p1: android.os.Bundle) {}
//            override fun onActivityDestroyed(p0: Activity) {}
//        })
//    }
//}