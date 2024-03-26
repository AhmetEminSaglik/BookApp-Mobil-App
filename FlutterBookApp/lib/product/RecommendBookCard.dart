import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/Recommend.dart';
import 'package:logger/logger.dart';
import '../cubit/recommendedbook/BookCubit.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../util/ProductColor.dart';
import '../util/ResponsiveDesign.dart';
import 'BookDesignDecoration.dart';

class RecommendBookCard extends StatefulWidget {
  late RecommendData recData;
  late int index;

  RecommendBookCard({required this.index, required this.recData});

  @override
  State<RecommendBookCard> createState() => _RecommendBookCardState();
}

class _RecommendBookCardState extends State<RecommendBookCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));

  final double imgWidth = ResponsiveDesign.width() / 6.5;
  final double imgHeight = ResponsiveDesign.height() / 6.5;
  final double padding = ResponsiveDesign.height() / 65;

  bool isLoading = true;
  List<Book> bookList = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _retrieveReadBookData();
  }

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
  Widget build(BuildContext context) {
    return SizedBox(
      height: imgHeight * 1.7,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {
                      goToDetailPage(context, widget.recData.data,
                          isBookReadByUser(widget.recData.data));
                    },
                    child: getRecommendCardContent(),
                  ),
                  Row(
                    children: [
                      Padding(
                          padding: EdgeInsets.only(
                              top: ResponsiveDesign.height() / 100),
                          child: getBookImage(context)),
                      getChevron(),
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

  bool isBookReadByUser(Book book) {
    bool isBookRead = false;
    bookList.forEach((element) {
      if (element.id == book.id) {
        isBookRead = true;
        return;
      }
    });
    log.i("Book is Read : $isBookRead");
    return isBookRead;
  }

  Padding getRecommendCardContent() {
    final double contentWidth = imgWidth / 2 + ResponsiveDesign.width() / 25;
    return Padding(
      padding: EdgeInsets.only(left: contentWidth),
      child: ContainerWithBoxDecoration(
        child: Container(
          // width: 295,
          width: ResponsiveDesign.width() - contentWidth - 4.5 * padding,
          height: imgHeight + 5.5 * padding,
          color: ProductColor.white,
          child: Padding(
            padding: EdgeInsets.only(left: imgWidth, top: 5),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                getShortTitle("${widget.index}-) ${widget.recData.data.name}"),
                /*Text(
                  getShortTitle(
                      "${widget.index}-) ${widget.recData.data.name}"),
                  maxLines: 2,
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.bold),
                ),*/
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(top: 5),
                        child: Row(
                          children: [
                            getBookRatingShape(widget.recData.data.point),
                          ],
                        ),
                      ),
                      const SizedBox(
                        height: 12,
                      ),
                      Text(
                        "${widget.recData.data.totalRead} Reviews",
                        style: const TextStyle(
                            fontSize: 15,
                            fontWeight: FontWeight.bold,
                            color: ProductColor.grey),
                      ),
                      Text(
                        widget.recData.by,
                        style: TextStyle(
                            fontSize: 15,
                            fontWeight: FontWeight.bold,
                            color: widget.recData.color),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(top: 5),
                        child: Text(
                          getShortDesc(widget.recData.data.desc),
                          maxLines: 3,
                          style: const TextStyle(
                              fontSize: 15, color: ProductColor.grey),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Padding getChevron() {
    return Padding(
      padding: EdgeInsets.only(
          left: ResponsiveDesign.width() - imgWidth - 7.2 * padding),
      child: Container(
        decoration: BoxDecoration(
            border: Border.all(width: 2, color: ProductColor.white),
            borderRadius: const BorderRadius.all(Radius.circular(50)),
            color: ProductColor.white,
            boxShadow: [
              BoxShadow(
                color: Colors.deepOrange.withOpacity(0.7),
                spreadRadius: 2,
                blurRadius: 3,
                offset: const Offset(0, 1), // Gölge ofseti (x, y)
              ),
            ]),
        height: 25,
        width: 25,
        // color: ProductColor.red,
        child: const Icon(Icons.chevron_right, color: ProductColor.red),
      ),
    );
  }

  Padding getBookImage(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(top: padding),
        child: InkWell(
          onTap: () {
            goToDetailPage(context, widget.recData.data,
                isBookReadByUser(widget.recData.data));
          },
          child: ContainerWithBoxDecoration(
            child: Padding(
              padding: const EdgeInsets.all(10),
              child: Image.network(
                widget.recData.data.imgUrl,
                fit: BoxFit.cover,
                height: imgHeight,
                width: imgWidth,
              ),
            ),
          ),
        ));
  }

  void goToDetailPage(BuildContext context, Book book, bool isBookRead) {
    context.read<BookCubit>().setBook(book);
    context.read<BookCubit>().goToDetailPage(context);
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 60;
    String shortDesc = desc;
    if (desc.trim().length > index) {
      shortDesc = shortDesc.replaceAll("\n", " ");
      shortDesc = "${shortDesc.substring(0, index).trim()}...";
    }
    return shortDesc;
  }

  Text getShortTitle(String title) {
    final int maxChar = 40;
    final int firstLineMaxChar = 20;
    double fontSize = 18;
    if (title.trim().length > maxChar) {
      title = "${title.substring(0, maxChar).trim()}...";
    }
    if (title.length > firstLineMaxChar) {
      fontSize = 17;
    }

    Text text = Text(
      title,
      maxLines: 2,
      style: TextStyle(fontSize: fontSize, fontWeight: FontWeight.bold),
    );

    return text;
  }
/*
  String getShortTitle(String title) {
    int index = 40;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }*/
}
