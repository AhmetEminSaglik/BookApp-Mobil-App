import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:logger/logger.dart';
import '../cubit/recommendedbook/BookCubit.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../util/ProductColor.dart';
import 'BookDesignDecoration.dart';

class BookCard extends StatefulWidget {
  late Book book;
  late int index;
  late bool isBookRead;

  BookCard({required this.book, required this.index});

  @override
  State<BookCard> createState() => _BookCardState();
}

class _BookCardState extends State<BookCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));

  final double imgWidth = ResponsiveDesign.width() / 6.5;
  final double imgHeight = ResponsiveDesign.height() / 6.5;
  final double padding = ResponsiveDesign.height() / 65;
  bool isLoading = true;

/*
  _retrieveUserReadThisBook() async {
    await _retrieveReadBookList();
    setState(() {
      isLoading = false;
    });
  }

  _retrieveReadBookList() async {
    Book book = await HttpRequestBook.getIfUserReadBook(widget.book.id);
    if (book.id == widget.book.id) {
      widget.isBookRead = true;
    }
  }*/

  @override
  Widget build(BuildContext context) {
    // _retrieveUserReadThisBook();
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
                      goToDetailPageOfBook(context, widget.book);
                    },
                    child: getBookCardContent(),
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

  Padding getBookCardContent() {
    final double contentWidth = imgWidth / 2 + ResponsiveDesign.width() / 25;
    return Padding(
      padding: EdgeInsets.only(
        left: contentWidth,
      ),
      child: ContainerWithBoxDecoration(
        child: Container(
          // width: 295,
          width: ResponsiveDesign.width() - contentWidth - 5.5 * padding,
          height: imgHeight + 5.5 * padding,
          color: ProductColor.white,
          child: Padding(
            padding: EdgeInsets.only(left: imgWidth, top: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                getShortTitle("${widget.index}-) ${widget.book.name}")
                /*Text(
                  getShortTitle("${widget.index}-) ${widget.book.name}"),
                  maxLines: 2,
                  style: const TextStyle(
                      fontSize: 18, fontWeight: FontWeight.bold),
                )*/
                ,
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Row(
                        children: [
                          getBookRatingShape(widget.book.point),
                        ],
                      ),
                    ),
                    const SizedBox(
                      height: 10,
                    ),
                    Text(
                      "${widget.book.totalRead} Reviews",
                      style: const TextStyle(
                          fontSize:16,
                          fontWeight: FontWeight.bold,
                          color: ProductColor.grey),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 10),
                      child: Text(
                        getShortDesc(widget.book.desc),
                        maxLines: 2,
                        style:
                        const TextStyle(fontSize: 16, color: ProductColor.grey),
                      ),
                    )
                  ],),
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
          left: ResponsiveDesign.width() - imgWidth - 8 * padding),
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
            goToDetailPageOfBook(context, widget.book);
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
        ));
  }

  void goToDetailPageOfBook(BuildContext context, Book book) {
    context.read<BookCubit>().setBook(book);
    context.read<BookCubit>().goToDetailPage(context);
  }

  String getShortDesc(String desc) {
    if (desc.trim().length == 0) {
      return "- - -";
    }
    int index = 50;
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
    double fontSize = 20;
    if (title.trim().length > maxChar) {
      title = "${title.substring(0, maxChar).trim()}...";
    }
    if (title.length > firstLineMaxChar) {
      fontSize = 18.5;
    }

    Text text = Text(
      title,
      maxLines: 2,
      style:  TextStyle(fontSize: fontSize, fontWeight: FontWeight.bold),
    );

    return text;
  }
}
