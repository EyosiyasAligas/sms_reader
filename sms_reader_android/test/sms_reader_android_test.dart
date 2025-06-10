// class MockSmsReaderAndroidPlatform
//     with MockPlatformInterfaceMixin
//     implements SmsReaderAndroidPlatform {
//
//   @override
//   Future<String?> getPlatformVersion() => Future.value('42');
// }
//
// void main() {
//   final SmsReaderAndroidPlatform initialPlatform = SmsReaderAndroidPlatform.instance;
//
//   test('$MethodChannelSmsReaderAndroid is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelSmsReaderAndroid>());
//   });
//
//   test('getPlatformVersion', () async {
//     SmsReaderAndroid smsReaderAndroidPlugin = SmsReaderAndroid();
//     MockSmsReaderAndroidPlatform fakePlatform = MockSmsReaderAndroidPlatform();
//     SmsReaderAndroidPlatform.instance = fakePlatform;
//
//     expect(await smsReaderAndroidPlugin.getPlatformVersion(), '42');
//   });
// }
