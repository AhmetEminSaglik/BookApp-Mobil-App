import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';
import '../cubit/recommendedbook/RecommendedBookCubit.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../product/BookDesignDecoration.dart';
import '../util/ProductColor.dart';

class MyReadBookScreen extends StatefulWidget {
  const MyReadBookScreen({super.key});

  @override
  State<MyReadBookScreen> createState() => _MyReadBookScreenState();
}

class _MyReadBookScreenState extends State<MyReadBookScreen> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  List<Book> bookList = [];
  late List<Book> readBookList;
  bool isLoading = true;

  _retrieveReadBookData() async {
    await _retrieveReadBookList();
    setState(() {
      isLoading = false;
    });
  }

  _retrieveReadBookList() async {
    bookList = await HttpRequestBook.getReadBookList();
  }

  @override
  void initState() {
    super.initState();
    _retrieveReadBookData();
  }

  @override
  Widget build(BuildContext context) {
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
    for (int i = 0; i < bookList.length; i++) {
      _BookCard _bookCard = _BookCard(book: bookList[i]);
      column.children.add(_bookCard);
    }
    return column;
  }
}

class _BookCard extends StatefulWidget {
  late Book book;

  _BookCard({required this.book});

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
      height: 255,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {
                      goToDetailPageOfBook(context, widget.book);
                    },
                    child: Padding(
                      padding:
                      EdgeInsets.only(left: imgWidth * 3 / 5, right: 10),
                      child: ContainerWithBoxDecoration(
                        child: Container(
                          width: 270,
                          height: imgHeight + 4 * paddingTop,
                          color: ProductColor.white,
                          child: Container(
                            child: Padding(
                              padding: EdgeInsets.only(left: imgWidth, top: 15),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    getShortTitle(widget.book.name),
                                    maxLines: 2,
                                    style: const TextStyle(
                                        fontSize: 20,
                                        fontWeight: FontWeight.bold),
                                  ),
                                  Padding(
                                    padding: const EdgeInsets.only(top: 10),
                                    child: Row(
                                      children: [
                                        getBookRatingShape(
                                            widget.book.point),
                                      ],
                                    ),
                                  ),
                                  const SizedBox(
                                    width: 10,
                                  ),
                                  Text(
                                    "${widget.book.totalRead} Reviews",
                                    style: const TextStyle(
                                        fontSize: 15,
                                        fontWeight: FontWeight.bold,
                                        color: ProductColor.grey),
                                  ),
                                  Padding(
                                    padding: const EdgeInsets.only(top: 15),
                                    child: Text(
                                      getShortDesc(widget.book.desc),
                                      maxLines: 3,
                                      style: const TextStyle(
                                          fontSize: 15,
                                          color: ProductColor.grey),
                                    ),
                                  ),
                                ],
                              ),
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
                          child: InkWell(
                            onTap: () {
                              goToDetailPageOfBook(
                                  context, widget.book);
                            },
                            child: ContainerWithBoxDecoration(
                              child: Padding(
                                padding: const EdgeInsets.all(10),
                                child: Image.network(
                                  widget.book.imgUrl,
                                  fit: BoxFit.cover,
                                  height: imgHeight,
                                  width: imgWidth,
                                ),
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

  void goToDetailPageOfBook(BuildContext context, Book book) {
    context.read<RecommendedBookCubit>().setBook(book);
    context.read<RecommendedBookCubit>().goToDetailPageOfBook(context);
  }

  /*Widget getBookRatingShape(double rating) {
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
            rating = currentRating;
          });
        });
  }
*/
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
