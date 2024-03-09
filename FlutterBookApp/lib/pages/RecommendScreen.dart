import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/product/RecommendBookCard.dart';
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
  List<RecommendData<Book>> recBookArr = [];
  List<RecommendData<Object>> list = [];

  // late Book book;
  late RecommendData<Book> recBook;
  bool isLoading = true;

  retrieveRecommendData() async {
    await retrieveUserList();
    await retrieveBookList();
    print(" recbookArr size : ${recBookArr.length}");
    recBook = recBookArr[recBookArr.length - 1];
    setState(() {
      isLoading = false;
    });
  }

  retrieveUserList() async {
    List<RecommendData<User>> recUser = [];
    userList = await HttpRequestUser.getRecommendUserList();
    userList.forEach((element) {
      recUser.add(RecommendData(
          by: EnumRecommendBy.BY_FRIEND.name,
          data: element,
          color: ProductColor.BY_FRIEND));
      list.add(RecommendData(
          by: EnumRecommendBy.BY_FRIEND.name,
          data: element,
          color: ProductColor.BY_FRIEND));
    });
  }

  retrieveBookList() async {
    bookList = await HttpRequestBook.getRecommendedBookListByPoint();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.HIGHEST_RATING,
        bookList: bookList,
        color: ProductColor.HIGHEST_RATING);

    bookList = await HttpRequestBook.getRecommendedBookListByTotalRead();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.MOST_READ,
        bookList: bookList,
        color: ProductColor.MOST_READ);

    bookList = await HttpRequestBook.getRecommendedBookListByFriendRead();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.BY_FRIEND,
        bookList: bookList,
        color: ProductColor.BY_FRIEND);
/*
    bookList.forEach((element) {
      list.add(element);
    });*/
  }

  void addBookListToRecBookList(
      {required EnumRecommendBy recommendBy,
      required List<Book> bookList,
      required Color color}) {
    bookList.forEach((element) {
      recBookArr.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
      log.i("Book ID : ${element.name}: ${element.imgUrl}");
      list.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
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
    // print("book img : ${recBook.data.imgUrl}");
    return Scaffold(
        backgroundColor: ProductColor.darkWhite,
        body: isLoading
            ? const Center(child: CircularProgressIndicator())
            : SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.only(left: 20,right: 10,top: 10,bottom: 10),
                  child: Column(
                    children: [
                      SingleChildScrollView(child: _BookCardColumn()),
                    ],
                  ),
                ),
              ));
  }

  Column _BookCardColumn() {
    Column column = Column(children: []);
    for (int i = 0; i < recBookArr.length; i++) {
      RecommendBookCard _recBookCard = RecommendBookCard(
        recData: recBookArr[i],
        index: recBookArr.length - i,
      );
      column.children.add(_recBookCard);
    }
    return column;
  }
}

