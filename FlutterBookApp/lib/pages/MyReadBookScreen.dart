import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:logger/logger.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../product/BookCard.dart';
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
                  padding: const EdgeInsets.only(
                      left: 20, right: 10, top: 10, bottom: 10),
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
      BookCard _bookCard = BookCard(
        book: bookList[i],
        index: (bookList.length - i),
        isBookRead: isBookReadByUser(bookList[i]),
      );
      column.children.add(_bookCard);
    }
    return column;
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
