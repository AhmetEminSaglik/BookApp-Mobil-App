import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/pages/profile/FollowerTab.dart';
import 'package:flutter_book_app/pages/profile/FollowingTab.dart';
import 'package:flutter_book_app/product/RecommendBookCard.dart';
import 'package:flutter_book_app/product/RecommendUserCard.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:logger/logger.dart';
import '../model/Book.dart';
import '../model/Recommend.dart';
import '../model/User.dart';
import '../util/ResponsiveDesign.dart';

class RecommendScreen extends StatefulWidget {
  const RecommendScreen({Key? key}) : super(key: key);

  @override
  State<RecommendScreen> createState() => _RecommendScreenState();
}

class _RecommendScreenState extends State<RecommendScreen> {
  final double _fontSize = ResponsiveDesign.height() / 50;
  var log = Logger(printer: PrettyPrinter(colors: false));
  List<UserFriendDTO> userFrienDTOList = [];
  List<Book> bookList = [];
  List<RecommendData<Book>> recBookArr = [];
  List<RecommendData<UserFriendDTO>> recUserFriendDTOArr = [];
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
    userFrienDTOList = await HttpRequestUser.getRecommendUserList();
    userFrienDTOList.forEach((element) {
      recUserFriendDTOArr.add(RecommendData(
          by: EnumRecommendBy.BY_FRIEND.name,
          data: element,
          color: ProductColor.BY_FRIEND));
      list.add(RecommendData(
          by: EnumRecommendBy.BY_FRIEND.name,
          data: element,
          color: ProductColor.BY_FRIEND));
    });
    log.i("recUser data recUserFriendDTOArr length:${recUserFriendDTOArr.length} userFrienDTOList length: ${userFrienDTOList.length}");
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

  /*
  * Todo: User Card Column : 0 geliyor. muhtemelen user to DTO'da sorun var.  User listesi almak yerine direk
  *  dto almak daha dogru olucak*/
  void addUserListToRecUserList(
      {required EnumRecommendBy recommendBy,
        required List<User> userList,
        required Color color}) {
    userList.forEach((element) {
      recUserFriendDTOArr.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
      log.i("User ID : ${element.name}: ${element.imgUrl}");
      list.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
    });
  }

  @override
  void initState() {
    super.initState();
    retrieveRecommendData();
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: ProductColor.darkWhite,
        body:DefaultTabController(
          length: 2,
          child: Scaffold(
            appBar: AppBar(
              toolbarHeight: 0,
              leading: null,
              automaticallyImplyLeading: false,
              bottom: TabBar(
                indicatorColor: ProductColor.pink,
                dividerColor: ProductColor.lightBlue,
                labelColor: ProductColor.pink,
                // labelColor: ProductColor.pink ,
                overlayColor: MaterialStateColor.resolveWith(
                        (states) => ProductColor.lightPurple),
                unselectedLabelColor: ProductColor.black,
                tabs: [
                  Tab(
                      child: Text("Book",
                          style: TextStyle(
                              fontSize: _fontSize+5))),
                  Tab(child: Text("User",
                      style: TextStyle(
                          fontSize: _fontSize+5))),
                ],


              ),
            ),
            body:
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: TabBarView(
                children: [
                  SingleChildScrollView(child: _BookCardColumn()),
                  SingleChildScrollView(child: _UserCardColumn()),
                ],
              ),
            )
            /*
            isLoading
                ? const Center(child: CircularProgressIndicator())
                : SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.only(left: 20,right: 10,top: 10,bottom: 10),
                child: Column(
                  children: [
                    SingleChildScrollView(child: _UserCardColumn()),
                    SingleChildScrollView(child: _BookCardColumn()),
                  ],
                ),
              ),
            )
          */
          ),
        )
    );
  }

  /*
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        backgroundColor: ProductColor.darkWhite,
        body: isLoading
            ? const Center(child: CircularProgressIndicator())
            : SingleChildScrollView(
                child: Padding(
                  padding: const EdgeInsets.only(left: 20,right: 10,top: 10,bottom: 10),
                  child: Column(
                    children: [
                      SingleChildScrollView(child: _UserCardColumn()),
                      SingleChildScrollView(child: _BookCardColumn()),
                    ],
                  ),
                ),
              ));
  }
*/

  Column _BookCardColumn() {
    Column column = Column(children: []);
    for (int i = 0; i < recBookArr.length; i++) {
      RecommendBookCard _recBookCard = RecommendBookCard(
        recData: recBookArr[i],
        index: recBookArr.length - i,
      );
      column.children.add(Padding(
        padding: const EdgeInsets.only(left: 10,top: 10),
        child: _recBookCard,
      ));
    }
    return column;
  }

  Column _UserCardColumn() {

    Column column = Column(children: []);
    for (int i = 0; i < recUserFriendDTOArr.length; i++) {
      RecommendUserCard _recUserCard = RecommendUserCard(
        recData:recUserFriendDTOArr[i],
        index:/* recUserFriendDTOArr.length - */i+1,
      );
      column.children.add(Padding(
        padding: const EdgeInsets.only(left: 20,top: 20),
        child: _recUserCard,
      ));
    }
    return column;
  }
}

