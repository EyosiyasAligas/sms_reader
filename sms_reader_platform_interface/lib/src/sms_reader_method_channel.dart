import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:sms_reader_platform_interface/sms_reader_platform_interface.dart';

/// An implementation of [SmsReaderPlatform] that uses method channels.
class MethodChannelSmsReader extends SmsReaderPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('sms_reader');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
