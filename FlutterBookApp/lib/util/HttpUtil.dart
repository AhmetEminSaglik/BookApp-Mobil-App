class HttpUtil {
  static Map<String, String> getHeaders() {
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'X-API-KEY': "SECRET_API_KEY", // API Key'i burada ekliyoruz
    };
  }
}
