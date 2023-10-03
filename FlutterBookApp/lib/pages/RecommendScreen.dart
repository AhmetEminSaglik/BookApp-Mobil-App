import 'package:flutter/material.dart';
import 'package:flutter_book_app/enum/EnumRecommendBy.dart';
import 'package:flutter_book_app/httprequest/HttpRequestBook.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
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
        backgroundColor: ProductColor.white,
        body: isLoading
            ? const Center(child: CircularProgressIndicator())
            : SingleChildScrollView(
                child: Column(
                  children: [
                    /* SizedBox(
                      height: 100,
                    ),*/
                    // getBookCardColumn(),
                    SingleChildScrollView(child: _BookCardColumn()),
                    // _BookCard(recBook: recBook),

                    /*        Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        _BookCard(recBook: recBook),
                        _BookCard(recBook: recBook),
                        _BookCard(recBook: recBook),
                        _BookCard(recBook: recBook),
                        _BookCard(recBook: recBook),
                      ],
                    )*/
                  ],
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
  // final double imgWidth = 70;
  // final double imgHeight = 120;

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      child: Column(
        children: [
          Row(
            children: [
              Container(
                decoration: BoxDecoration(
                    border: Border.all(width: 5, color: ProductColor.white),
                    borderRadius: const BorderRadius.all(Radius.circular(12)),
                    color: ProductColor.white,
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.5), // Gölge rengi
                        spreadRadius: 5, // Gölge yayılma yarıçapı
                        blurRadius: 7, // Gölge bulanıklık yarıçapı
                        offset: Offset(0, 3), // Gölge ofseti (x, y)
                      ),
                    ]),
                child: Padding(
                  padding: const EdgeInsets.all(10),
                  child: Image.network(
                    widget.recBook.data.imgUrl,
                    fit: BoxFit.cover,
                    height: 200,
                    width: 120,
                  ),
                ),
              ),
            ],
          )
          /*Padding(
            padding: const EdgeInsets.all(10.0),
            child: InkWell(
              onTap: () {
                Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) =>
                            BookDetailPage(book: widget.recBook.data)));
              },

              child: Card(
                // clipBehavior: Clip.,
                color: ProductColor.cardBackground,
                child: Column(
                  children: [

                 */ /*   Padding(
                      padding: const EdgeInsets.only(
                          left: 10, right: 25, bottom: 10, top: 10),
                      child: Row(
                        children: [
                          getPointOfBookStar(widget.recBook.data.point),
                          const Spacer(),
                          ElevatedButton(
                            onPressed: () {},
                            style: ButtonStyle(
                                backgroundColor: MaterialStateColor.resolveWith(
                                    (states) => ProductColor.red)),
                            child: const Text(
                              "Add As Read",
                              style: TextStyle(
                                fontSize: 15,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          )
                        ],
                      ),
                    )*/ /*
                  ],
                ),
              ),
            ),
          ),*/
        ],
      ),
    );
  }

  Widget getPointOfBookStar(double rating) {
    rating /= 2;
    return RatingBar.builder(
        initialRating: rating,
        allowHalfRating: true,
        itemBuilder: (context, _) => const Icon(
              // Icons.star,
              Icons.favorite,
              color: ProductColor.ratingColor,
            ),
        unratedColor: ProductColor.unRatingColor,
        itemSize: 27,
        onRatingUpdate: (rating) {
          print("rating Update worked : $rating");
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
    int index = 40;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }
}

mixin heart {}
