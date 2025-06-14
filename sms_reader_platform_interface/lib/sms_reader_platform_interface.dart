library sms_reader_platform_interface;

export 'src/models/sms_message.dart';
export 'src/models/sms_type.dart';

import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:sms_reader_platform_interface/src/sms_reader_method_channel.dart';
import 'src/models/sms_message.dart';
import 'src/models/sms_type.dart';

/// The interface that implementations must extend.
abstract class SmsReaderPlatform extends PlatformInterface {
  /// Constructs a SmsReaderPlatform.
  SmsReaderPlatform() : super(token: _token);

  static final Object _token = Object();

  static SmsReaderPlatform _instance = MethodChannelSmsReader();

  /// The default instance, injected via the Android/iOS package.
  static SmsReaderPlatform get instance => _instance;

  /// Platform implementations should call this to register themselves.
  static set instance(SmsReaderPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  /// --- API methods ---

  /// Returns `true` if READ_SMS & RECEIVE_SMS are granted.
  Future<bool> hasPermissions() {
    throw UnimplementedError('hasPermissions() has not been implemented.');
  }

  /// Requests runtime SMS permissions.
  Future<bool> requestPermissions() {
    throw UnimplementedError('requestPermissions() has not been implemented.');
  }

  /// Fetch past SMS with rich filters.
  Future<List<SmsMessage>> getMessages({
    List<String>? senders,
    int? startDateMillis,
    int? endDateMillis,
    List<SmsType>? messageTypes,
    bool? onlyUnread,
    String? bodyContains,
    int? limit,
    int? offset,
    bool sortOrderDesc = true,
  }) {
    throw UnimplementedError('getMessages() has not been implemented.');
  }

  /// Stream of incoming SMS as they arrive.
  Stream<SmsMessage> getIncomingSmsStream() {
    throw UnimplementedError('getIncomingSmsStream() has not been implemented.');
  }

  /// (Optional) Delete a message by its ID.
  Future<bool> deleteMessage(String id) {
    throw UnimplementedError('deleteMessage() has not been implemented.');
  }

  /// (Optional) Mark a message read/unread.
  Future<bool> markMessageRead(String id, bool read) {
    throw UnimplementedError('markMessageRead() has not been implemented.');
  }
}
