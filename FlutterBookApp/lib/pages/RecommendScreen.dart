import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/product/BookCard.dart';
import 'package:flutter_book_app/product/RecommendUserCard.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:logger/logger.dart';
import '../model/Book.dart';
import '../model/Recommend.dart';
import '../model/User.dart';
import '../util/ResponsiveDesign.dart';

class RecommendScreen extends StatefulWidget {
  RecommendScreen({Key? key}) : super(key: key);

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

  // List<RecommendData<Object>> list = [];

  // late Book book;
  late RecommendData<Book> recBook;
  bool isLoading = true;

  retrieveRecommendData() async {
    await retrieveUserList();
    await retrieveBookList();
    print(" recbookArr size : ${recBookArr.length}");
    recBook = recBookArr[recBookArr.length - 1];
    if (mounted) {
      setState(() {
        isLoading = false;
      });
    } else {
      print("Elseye girdi mounted: $mounted");
    }
    // setState(() {
    //   isLoading = false;
    // });
  }

  retrieveUserList() async {
    List<UserFriendDTO> recommendedUserByFriend =
        await HttpRequestUser.getRecommendUserList();
    List<UserFriendDTO> randomRecommendedUser =
        await HttpRequestUser.getRandomRecommendUserList();
    for (var element in recommendedUserByFriend) {
      recUserFriendDTOArr.add(RecommendData(
          by: EnumRecommendBy.BY_FRIEND.name,
          data: element,
          color: ProductColor.BY_FRIEND));
    }

    for (var element in randomRecommendedUser) {
      recUserFriendDTOArr.add(RecommendData(
          by: EnumRecommendBy.BY_RANDOM.name,
          data: element,
          color: ProductColor.BY_RANDOM));
    }
    log.i(
        "recUser data recUserFriendDTOArr length:${recUserFriendDTOArr.length} userFrienDTOList length: ${userFrienDTOList.length}");
  }

  retrieveBookList() async {
    bookList = await HttpRequestBook.getRecommendedBookListByFriendRead();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.BY_FRIEND,
        bookList: bookList,
        color: ProductColor.BY_FRIEND);

    bookList = await HttpRequestBook.getRecommendedBookListByTotalRead();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.BESTSELLER,
        bookList: bookList,
        color: ProductColor.MOST_READ);

    bookList = await HttpRequestBook.getRecommendedBookListByPoint();
    addBookListToRecBookList(
        recommendBy: EnumRecommendBy.HIGHEST_RATING,
        bookList: bookList,
        color: ProductColor.HIGHEST_RATING);
  }

  void addBookListToRecBookList(
      {required EnumRecommendBy recommendBy,
      required List<Book> bookList,
      required Color color}) {
    for (var element in bookList) {
      recBookArr.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
      log.i("Book ID : ${element.name}: ${element.imgUrl}");
    }
  }

  void addUserListToRecUserList(
      {required EnumRecommendBy recommendBy,
      required List<User> userList,
      required Color color}) {
    for (var element in userList) {
      recUserFriendDTOArr.add(
          RecommendData(by: recommendBy.name, data: element, color: color));
      log.i("User ID : ${element.name}: ${element.imgUrl}");
    }
  }

  @override
  void initState() {
    super.initState();
    retrieveRecommendData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: DefaultTabController(
      length: 2,
      child: Scaffold(
          backgroundColor: ProductColor.darkWhite,
          appBar: AppBar(
            toolbarHeight: 0,
            leading: null,
            automaticallyImplyLeading: false,
            bottom: TabBar(
              indicatorColor: ProductColor.pink,
              dividerColor: ProductColor.blue,
              labelColor: ProductColor.pink,
              overlayColor: MaterialStateColor.resolveWith(
                  (states) => ProductColor.lightPurple),
              unselectedLabelColor: ProductColor.black,
              dividerHeight: 2,
              tabs: [
                Tab(
                    child: Text("User",
                        style: TextStyle(fontSize: _fontSize + 5))),
                Tab(
                    child: Text("Book",
                        style: TextStyle(fontSize: _fontSize + 5))),
              ],
            ),
          ),
          body: TabBarView(
            children: [
              SingleChildScrollView(child: _UserCardColumn()),
              SingleChildScrollView(child: _BookCardColumn()),
            ],
          )),
    ));
  }

  Padding _BookCardColumn() {
    Column column = Column(children: []);
    for (int i = 0; i < recBookArr.length; i++) {
      BookCard recBookCard = BookCard(
        recData: recBookArr[i],
        index: recBookArr.length - i,
      );
      column.children.add(recBookCard);
    }
    return Padding(
      padding: const EdgeInsets.only(top: 15, bottom: 15, left: 25),
      child: column,
    );
  }

  Padding _UserCardColumn() {
    Column column = Column(children: []);
    for (int i = 0; i < recUserFriendDTOArr.length; i++) {
      RecommendUserCard recUserCard = RecommendUserCard(
        recData: recUserFriendDTOArr[i],
        index: i + 1,
      );
      column.children.add(recUserCard);
    }
    return Padding(
      padding: const EdgeInsets.only(top: 15, bottom: 15, left: 15),
      child: column,
    );
  }
}
