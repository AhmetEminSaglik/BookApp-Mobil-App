import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
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
    // book = bookList[0];
    print(" recbookArr size : ${recBookArr.length}");
    recBook = recBookArr[recBookArr.length - 1];
    setState(() {
      isLoading = false;
    });
    // print("-------------- USER LIST :  ");
    // userList.forEach((element) {
    //   print(element);
    // });
    // print("-------------- Book LIST :  ");
    // bookList.forEach((element) {
    //   print(element);
    // });
    //
    // print("-------------- Recommend Object LIST :  ");
    // list.forEach((element) {
    //   print(element);
    // });
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
                  padding: const EdgeInsets.all(20),
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
      _BookCard _bookCard = _BookCard(recBook: recBookArr[i]);
      column.children.add(_bookCard);
    }
    return column;
  }
}

class _BookCard extends StatefulWidget {
  late RecommendData<Book> recBook;

  _BookCard({required this.recBook});

  @override
  State<_BookCard> createState() => _BookCardState();
}

class _BookCardState extends State<_BookCard> {
  final double imgWidth = 90;
  final double imgHeight = 140;
  final double paddingTop = 20;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  Padding(
                    padding: EdgeInsets.only(left: imgWidth * 3 / 5, right: 10),
                    child: _ContainerWithBoxDecoration(
                      widget: Container(
                        width: 270,
                        height: imgHeight + 3 * paddingTop,
                        color: ProductColor.white,
                        child: Container(
                          child: Padding(
                            padding: EdgeInsets.only(left: imgWidth, top: 15),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  getShortTitle(widget.recBook.data.name),
                                  maxLines: 2,
                                  style: const TextStyle(
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(top: 10),
                                  child: getBookRatingShape(
                                      widget.recBook.data.point),
                                ),
                                const SizedBox(
                                  width: 10,
                                ),
                                Text(
                                  "${widget.recBook.data.totalRead} Reviews",
                                  style: const TextStyle(
                                      fontSize: 15,
                                      fontWeight: FontWeight.bold,
                                      color: ProductColor.grey),
                                ),
                                Padding(
                                  padding: const EdgeInsets.only(top: 15),
                                  child: Text(
                                    getShortDesc(widget.recBook.data.desc),
                                    maxLines: 3,
                                    style: const TextStyle(
                                        fontSize: 15, color: ProductColor.grey),
                                  ),
                                ),
                              ],
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                  Row(
                    children: [
                      Padding(
                          padding: EdgeInsets.only(top: paddingTop),
                          child: _ContainerWithBoxDecoration(
                            widget: Padding(
                              padding: const EdgeInsets.all(10),
                              child: Image.network(
                                widget.recBook.data.imgUrl,
                                fit: BoxFit.cover,
                                height: imgHeight,
                                width: imgWidth,
                              ),
                            ),
                          )),
                      Padding(
                        padding: const EdgeInsets.only(left: 205),
                        child: Container(
                          decoration: BoxDecoration(
                              border: Border.all(
                                  width: 2, color: ProductColor.white),
                              borderRadius:
                                  const BorderRadius.all(Radius.circular(50)),
                              color: ProductColor.white,
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.deepOrange.withOpacity(0.7),
                                  spreadRadius: 2,
                                  blurRadius: 3,
                                  offset:
                                      const Offset(0, 1), // Gölge ofseti (x, y)
                                ),
                              ]),
                          height: 25,
                          width: 25,
                          // color: ProductColor.red,
                          child: Icon(Icons.chevron_right,
                              color: ProductColor.red),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  Widget getBookRatingShape(double rating) {
    // rating /= 2;
    double currentRating = rating;
    return RatingBar.builder(
        initialRating: rating,
        tapOnlyMode: false,
        allowHalfRating: true,
        itemBuilder: (context, _) => const Icon(
              // Icons.star,
              Icons.star,
              color: ProductColor.ratingColor,
            ),
        unratedColor: ProductColor.unRatingColor,
        itemSize: 25,
        onRatingUpdate: (rating) {
          setState(() {
            rating=currentRating ;
          });

        });
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 70;
    String shortDesc = desc;
    if (desc.trim().length > index) {
      shortDesc = shortDesc.replaceAll("\n", " ");
      shortDesc = "${shortDesc.substring(0, index).trim()}...";
    }
    return shortDesc;
  }

  String getShortTitle(String title) {
    int index = 35;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }
}

class _ContainerWithBoxDecoration extends StatelessWidget {
  late Widget widget;

  _ContainerWithBoxDecoration({required this.widget});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
          border: Border.all(width: 5, color: ProductColor.white),
          borderRadius: const BorderRadius.all(Radius.circular(12)),
          color: ProductColor.white,
          boxShadow: [
            BoxShadow(
              color: Colors.grey.withOpacity(0.7),
              spreadRadius: 6,
              blurRadius: 5,
              offset: const Offset(0, 1), // Gölge ofseti (x, y)
            ),
          ]),
      child: widget,
    );
  }
}
