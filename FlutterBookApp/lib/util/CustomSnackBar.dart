import 'package:flutter/material.dart';

import 'ProductColor.dart';
import 'ResponsiveDesign.dart';

class CustomSnackBar {
  static SnackBar getSnackBar(String msg) {
    return SnackBar(
      content: Text(
        msg,
        style: TextStyle(
            color: ProductColor.white,
            fontSize: ResponsiveDesign.width() / 27),
      ),
      closeIconColor: ProductColor.white,
      showCloseIcon: true,
      backgroundColor: ProductColor.black,
      duration: const Duration(seconds: 3),
    );
  }
}
