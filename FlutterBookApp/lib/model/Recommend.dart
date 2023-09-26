import 'package:flutter_book_app/enum/EnumRecommendBy.dart';

class RecommendData<T> {
  late String _by;
  late T _data;

  RecommendData({required by, required data}) {
    _by = by;
    _data = data;
  }

  T get data => _data;

  set data(T value) {
    _data = value;
  }

  String get by => _by;

  set by(String value) {
    _by = value;
  }

  @override
  String toString() {
    return 'RecommendData{_by: $_by, _data: $_data}';
  }
}
