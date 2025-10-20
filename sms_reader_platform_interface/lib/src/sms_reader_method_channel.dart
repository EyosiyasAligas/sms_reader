import 'dart:async';
import 'package:flutter/services.dart';

import '../sms_reader_platform_interface.dart';

const MethodChannel _kMethodChannel = MethodChannel('sms_reader_method');
const EventChannel _kEventChannel = EventChannel('sms_reader_incoming_events');

/// The default, MethodChannel–based implementation of [SmsReaderPlatform].
class MethodChannelSmsReader extends SmsReaderPlatform {
  @override
  Future<bool> hasPermissions() =>
      _kMethodChannel.invokeMethod<bool>('hasPermissions')
          .then((v) => v ?? false);

  @override
  Future<bool> requestPermissions() =>
      _kMethodChannel.invokeMethod<bool>('requestPermissions')
          .then((v) => v ?? false);

  @override
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
  }) async {
    final args = <String, dynamic>{
      if (senders != null) 'senders': senders,
      if (startDateMillis != null) 'startDate': startDateMillis,
      if (endDateMillis != null) 'endDate': endDateMillis,
      if (messageTypes != null)
        'types': messageTypes.map((e) => e.name).toList(),
      if (onlyUnread != null) 'onlyUnread': onlyUnread,
      if (bodyContains != null) 'bodyContains': bodyContains,
      if (limit != null) 'limit': limit,
      if (offset != null) 'offset': offset,
      'sortDesc': sortOrderDesc,
    };
    final List<dynamic> list = await _kMethodChannel
        .invokeMethod<List<dynamic>>('getMessages', args) ?? [];
    return list
        .cast<Map<dynamic, dynamic>>()
        .map((m) => SmsMessage.fromMap(m))
        .toList();
  }

  @override
  Stream<SmsMessage> getIncomingSmsStream() {
    return _kEventChannel
        .receiveBroadcastStream()
        .map((dynamic event) => SmsMessage.fromMap(event));
  }

  @override
  Future<bool> deleteMessage(String id) =>
      _kMethodChannel.invokeMethod<bool>('deleteMessage', {'id': id})
          .then((v) => v ?? false);

  @override
  Future<bool> markMessageRead(String id, bool read) =>
      _kMethodChannel
          .invokeMethod<bool>('markMessageRead', {'id': id, 'read': read})
          .then((v) => v ?? false);
}
