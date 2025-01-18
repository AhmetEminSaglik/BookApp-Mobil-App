import 'package:flutter/material.dart';

import '../core/ResponsiveDesign.dart';

class CustomAlertDialog {
  static AlertDialog getAlertDialogHowToLogin(
      {required BuildContext context,
      required String title,
      // required String subTitle,
      required String msg}) {
    // int subTitleLines = _calculateLines(subTitle, ResponsiveDesign.getScreenWidth() / 20);
    // int msgLines = _calculateLines(msg, 300);
    // int totalLines = subTitleLines + msgLines;
    double contentHeight = 250;
    // double contentHeight = (msgLines * lineHeight) + 100;

    return AlertDialog(
      title: Text(
        "$title",
        textAlign: TextAlign.center,
        style: TextStyle(
            fontSize: 30,
            color: Colors.red),
      ),
      content: SizedBox(
        height: contentHeight,
        child: SingleChildScrollView(
          child: Column(
            children: [
              // Text(subTitle, style: TextStyle(fontSize: ResponsiveDesign.getScreenWidth() / 20,  color:Colors.green )),
              SizedBox(height: 10),
              Text(msg, style: TextStyle(fontSize: 17))
            ],
          ),
        ),
      ),
      actions: [
        TextButton(
          onPressed: () {
            Navigator.pop(context);
          },
          child: Text(
            "Ok",
            style: TextStyle(fontSize: 15),
          ),
        ),
      ],
    );
  }

  static int _calculateLines(String text, double fontSize) {
    // Satır sayısını hesapla
    double textWidth = 100;
    double textHeight = 100;
    TextPainter textPainter = TextPainter(
      text: TextSpan(text: text, style: TextStyle(fontSize: fontSize)),
      maxLines: 100,
      textDirection: TextDirection.ltr,
    );
    textPainter.layout(minWidth: textWidth, maxWidth: textWidth);
    return textPainter.computeLineMetrics().length;
  }

  static AlertDialog getAlertDialogValidateProcess({
    required BuildContext context,
    required String title,
    // required String subTitle,
    required String selectedItemName,
    required String msg,
    required int roleId,
  }) {
    return AlertDialog(
      title: Text(title, textAlign: TextAlign.center),
      content: SizedBox(
        height: ResponsiveDesign.getScreenHeight() / 8,
        child: SingleChildScrollView(
          child: Column(
            children: [
              /*Text(subTitle,
                  style: TextStyle(
                      fontSize: ResponsiveDesign.getScreenWidth() / 20,
                      color: Colors.deepOrange)),*/
              SizedBox(height: ResponsiveDesign.getScreenHeight() / 20),
              Text(msg,
                  style: TextStyle(
                      fontSize: ResponsiveDesign.getScreenWidth() / 22)),
              Text(selectedItemName,
                  style: TextStyle(
                      fontSize: ResponsiveDesign.getScreenWidth() / 19,
                      color: Colors.redAccent)),
            ],
          ),
        ),
      ),
      actions: [
        Row(
          children: [
            TextButton(
                onPressed: () {
                  Navigator.of(context).pop(false);
                },
                child: Text(
                  "No",
                  style: TextStyle(
                      fontSize: ResponsiveDesign.getScreenWidth() / 20),
                )),
            Spacer(),
            TextButton(
                onPressed: () {
                  Navigator.of(context).pop(true);
                  //Navigator.pop(context);
                },
                child: Text(
                  "Yes",
                  style: TextStyle(
                      fontSize: ResponsiveDesign.getScreenWidth() / 20),
                )),
          ],
        )
      ],
    );
  }
}
