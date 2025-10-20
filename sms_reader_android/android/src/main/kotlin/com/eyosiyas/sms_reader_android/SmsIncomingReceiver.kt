//package com.eyosiyas.sms_reader_android
//
//import android.content.BroadcastReceiver
//import android.content.Context
//import android.content.Intent
//import android.content.IntentFilter
//import android.provider.Telephony
//import io.flutter.plugin.common.EventChannel.EventSink
//
///**
// * Receives incoming SMS PDUs and forwards them to the Flutter side.
// */
//class SmsIncomingReceiver(
//    private val context: Context,
//    private val eventSink: EventSink
//) : BroadcastReceiver() {
//    fun register() {
//        val filter = android.content.IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
//        filter.priority = IntentFilter.SYSTEM_HIGH_PRIORITY
//        context.registerReceiver(this, filter)
//    }
//
//    fun unregister() {
//        context.unregisterReceiver(this)
//    }
//
//    override fun onReceive(context: Context?, intent: Intent?) {
//        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
//            val pdus = intent.extras?.get("pdus") as? Array<*>
//            val format = intent.extras?.getString("format")
//            if (pdus != null) {
//                for (pdu in pdus) {
//                    val sms = Telephony.Sms.Intents.getMessagesFromIntent(intent)[0]
//                    val map = mapOf(
//                        "id" to sms.timestampMillis.toString(),
//                        "address" to sms.originatingAddress.orEmpty(),
//                        "body" to sms.messageBody,
//                        "type" to "inbox",
//                        "date" to sms.timestampMillis,
//                        "read" to false
//                    )
//                    eventSink.success(map)
//                }
//            }
//        }
//    }
//}
