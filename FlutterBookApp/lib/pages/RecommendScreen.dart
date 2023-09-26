import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:logger/logger.dart';

import '../model/Book.dart';
import '../model/Recommend.dart';
import '../model/User.dart';

class RecommendScreen extends StatefulWidget {
  const RecommendScreen({Key? key}) : super(key: key);

  @override
  State<RecommendScreen> createState() => _RecommendScreenState();
}

class _RecommendScreenState extends State<RecommendScreen> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  List<User> userList = [];
  List<Book> bookList = [];
  List<RecommendData<Book>> recBook = [];
  List<RecommendData<Object>> list = [];

  retrieveRecommendData() async {
    await retrieveUserList();
    await retrieveBookList();
    print("-------------- USER LIST :  ");
    userList.forEach((element) {print(element);});
    print("-------------- Book LIST :  ");
    bookList.forEach((element) {print(element);});

    print("-------------- Recommend Object LIST :  ");
    list.forEach((element) {print(element);});
  }

  retrieveUserList() async {
    List<RecommendData<User>> recUser = [];
    userList = await HttpRequestUser.getRecommendUserList();
    userList.forEach((element) {
      recUser.add(RecommendData(by: EnumRecommendBy.BY_FRIEND.name, data: element));
      list.add(RecommendData(by: EnumRecommendBy.BY_FRIEND.name, data: element));
    });
  }

  retrieveBookList() async {
    bookList = await HttpRequestBook.getRecommendedBookListByPoint();
    addBookListToRecBookList(EnumRecommendBy.HIGH_POINT, bookList);

    bookList = await HttpRequestBook.getRecommendedBookListByTotalRead();
    addBookListToRecBookList(EnumRecommendBy.TOTAL_READ, bookList);

    bookList = await HttpRequestBook.getRecommendedBookListByFriendRead();
    addBookListToRecBookList(EnumRecommendBy.BY_FRIEND, bookList);
/*
    bookList.forEach((element) {
      list.add(element);
    });*/
  }

  void addBookListToRecBookList(
      EnumRecommendBy recommendBy, List<Book> bookList) {
    bookList.forEach((element) {
      recBook.add(RecommendData(by: recommendBy.name, data: element));
      list.add(RecommendData(by: recommendBy.name, data: element));
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    // retrieveUserList();
    // retrieveBookList();
    retrieveRecommendData();
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
