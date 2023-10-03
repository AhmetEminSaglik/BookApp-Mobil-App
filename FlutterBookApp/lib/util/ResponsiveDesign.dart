import 'package:flutter/cupertino.dart';
import 'package:logger/logger.dart';

class ResponsiveDesign {
  static var log = Logger(printer: PrettyPrinter(colors: false));

  MediaQueryData mediaQueryData;
  static double _screenWidth = 0;
  static double _screenHeight = 0;

  ResponsiveDesign({required this.mediaQueryData}) {
    _screenWidth = mediaQueryData.size.width;
    _screenHeight = mediaQueryData.size.height;
  }

  static double width() {
    return _screenWidth;
  }

  static double height() {
    return _screenHeight;
  }
}
