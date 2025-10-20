//package com.eyosiyas.sms_reader_android
//
//import android.content.Context
//import io.flutter.plugin.common.MethodCall
//import io.flutter.plugin.common.MethodChannel
//import io.flutter.plugin.common.EventChannel
//import io.flutter.plugin.common.EventChannel.EventSink
//import io.flutter.plugin.common.EventChannel.StreamHandler
//import com.eyosiyas.sms_reader_platform_interface.SmsReaderPlatform
//import com.eyosiyas.sms_reader_platform_interface.models.SmsMessage
//
///**
// * Android implementation of SmsReaderPlatform.
// * Implements MethodCallHandler and StreamHandler.
// */
//class AndroidSmsReader(
//    private val context: Context,
//    private val permissionHandler: PermissionHandler
//) : SmsReaderPlatform(), MethodChannel.MethodCallHandler, StreamHandler {
//
//    private var eventSink: EventSink? = null
//    private var smsReceiver: SmsIncomingReceiver? = null
//
//    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
//        when (call.method) {
//            "hasPermissions" -> permissionHandler.hasSmsPermissions(result)
//            "requestPermissions" -> permissionHandler.requestSmsPermissions(result)
//            "getMessages" -> {
//                // TODO: implement query handler
//            }
//            "deleteMessage" -> {
//                // TODO: implement delete logic
//            }
//            "markMessageRead" -> {
//                // TODO: implement mark-read logic
//            }
//            else -> result.notImplemented()
//        }
//    }
//
//    override fun onListen(arguments: Any?, sink: EventSink) {
//        eventSink = sink
//        smsReceiver = SmsIncomingReceiver(context, sink)
//        smsReceiver?.register()  // registers BroadcastReceiver
//    }
//
//    override fun onCancel(arguments: Any?) {
//        smsReceiver?.unregister()
//        smsReceiver = null
//        eventSink = null
//    }
//
//    // SmsReaderPlatform interface methods (optional override if needed)
//    override fun hasPermissions(): Boolean = permissionHandler.checkSmsPermissions()
//
//    override fun requestPermissions(): Boolean {
//        // This is async: handled in onMethodCall above
//        throw UnsupportedOperationException("Use requestPermissions via method channel")
//    }
//
//    override fun getIncomingSmsStream(): Stream<SmsMessage> {
//        // Not used; event channel covers this
//        throw UnsupportedOperationException("Use getIncomingSmsStream via stream channel")
//    }
//
//    // More platform interface methods getMessages, delete, markRead can be routed here if needed
//}
