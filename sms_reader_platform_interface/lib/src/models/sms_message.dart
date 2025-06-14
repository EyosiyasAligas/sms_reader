import 'sms_type.dart';

/// A single SMS/MMS record.
class SmsMessage {
  final String id;
  final String address;
  final String body;
  final SmsType type;
  final DateTime date;
  final bool read;
  final Map<String, dynamic>? extras;

  SmsMessage({
    required this.id,
    required this.address,
    required this.body,
    required this.type,
    required this.date,
    required this.read,
    this.extras,
  });

  /// Deserialize from the MethodChannel map.
  factory SmsMessage.fromMap(Map<dynamic, dynamic> map) {
    return SmsMessage(
      id: map['id'].toString(),
      address: map['address'] as String,
      body: map['body'] as String,
      type: SmsType.values.firstWhere(
            (e) => e.name == map['type'],
        orElse: () => SmsType.inbox,
      ),
      date: DateTime.fromMillisecondsSinceEpoch(map['date'] as int),
      read: map['read'] as bool,
      extras: (map['extras'] as Map?)?.cast<String, dynamic>(),
    );
  }

  /// Convert to map (if needed).
  Map<String, dynamic> toMap() => {
    'id': id,
    'address': address,
    'body': body,
    'type': type.name,
    'date': date.millisecondsSinceEpoch,
    'read': read,
    'extras': extras,
  };
}
