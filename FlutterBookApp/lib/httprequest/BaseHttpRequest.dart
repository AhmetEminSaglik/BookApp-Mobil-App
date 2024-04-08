class BaseHttpRequestConfig {
  // static const String _localhost = "http://10.0.2.2:";
  static const String _localhost = "http://192.168.1.40:";
  static const String _port = "8080";
  static const String _baseUrl = _localhost + _port;

  static String get localhost => _localhost;

  static String get port => _port;

  static String get baseUrl => _baseUrl;
}
