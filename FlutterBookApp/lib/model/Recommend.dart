import 'dart:ui';

import 'package:flutter_book_app/enum/EnumRecommendBy.dart';

class RecommendData<T> {
  late String _by;
  late T _data;
  late Color _color;

  RecommendData({required by, required data, required Color color}) {
    _by = by;
    _data = data;
    _color = color;
  }

  T get data => _data;

  set data(T value) {
    _data = value;
  }

  String get by => _by;

  set by(String value) {
    _by = value;
  }

  Color get color => _color;

  set color(Color value) {
    _color = value;
  }

  @override
  String toString() {
    return 'RecommendData{_by: $_by, _data: $_data}';
  }
}
