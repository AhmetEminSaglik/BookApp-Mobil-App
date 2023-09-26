import 'package:flutter/material.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:logger/logger.dart';

import '../model/User.dart';

class RecommendScreen extends StatefulWidget {
  const RecommendScreen({Key? key}) : super(key: key);

  @override
  State<RecommendScreen> createState() => _RecommendScreenState();
}

class _RecommendScreenState extends State<RecommendScreen> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  List<User> userList = [];

  retrieveUserList() async {
    userList = await HttpRequestUser.getRecommendUserList();
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    retrieveUserList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: ProductColor.blue,
      body: Column(
        children: [],
      ),
    );
  }
}
