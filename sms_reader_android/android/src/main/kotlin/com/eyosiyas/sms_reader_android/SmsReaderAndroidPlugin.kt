package com.eyosiyas.sms_reader_android

import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.EventChannel
//import com.eyosiyas.sms_reader_platform_interface.SmsReaderPlatform


/**
 * SmsReaderPlugin
 *
 * Registers MethodChannel and EventChannel, delegates calls to AndroidSmsReader.
 */
class SmsReaderAndroidPlugin: FlutterPlugin {
  private lateinit var methodChannel : MethodChannel
  private lateinit var eventChannel: EventChannel
//  private lateinit var smsReader: AndroidSmsReader

  override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    val messenger = binding.binaryMessenger
    methodChannel = MethodChannel(messenger, "sms_reader_method")
    eventChannel = EventChannel(messenger, "sms_reader_incoming_events")

    // Initialize handlers
    val context = binding.applicationContext
//    val permissionHandler = PermissionHandler(binding.applicationContext, binding.binaryMessenger)
//    smsReader = AndroidSmsReader(context, permissionHandler)

    // Set platform interface instance
//    SmsReaderPlatform.instance = smsReader

    // Setup channel handlers
//    methodChannel.setMethodCallHandler(smsReader)
//    eventChannel.setStreamHandler(smsReader)
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    methodChannel.setMethodCallHandler(null)
    eventChannel.setStreamHandler(null)
  }
}
