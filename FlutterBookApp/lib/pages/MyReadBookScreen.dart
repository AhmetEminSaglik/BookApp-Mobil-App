
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/MyBookReadScreenCubit.dart';
import 'package:logger/logger.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
import '../product/BookCard.dart';
import '../util/ProductColor.dart';

class MyReadBookScreen extends StatefulWidget {
  MyReadBookScreen({super.key});


  @override
  State<MyReadBookScreen> createState() => _MyReadBookScreenState();
}

class _MyReadBookScreenState extends State<MyReadBookScreen> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  List<Book> bookList = [];
  late List<Book> readBookList;
  bool isLoading = true;

  _retrieveReadBookData() async {
    log.i("Read book data is retrieved");
    context.read<MyReadBookScreenCubit>().resetUpdateValue();
    await _retrieveReadBookList();

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

  _retrieveReadBookList() async {
    bookList = await HttpRequestBook.getReadBookList();
  }

  @override
  void initState() {
    super.initState();
      print("MyReadBookScreen > initState ");
      _retrieveReadBookData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: ProductColor.darkWhite,
      body: BlocBuilder<MyReadBookScreenCubit, bool>(
        builder: (context, state) {
          if (state) {
            _retrieveReadBookData();
            // context.read<MyReadBookScreenCubit>().resetUpdateValue();
          }
          return state
              ? const Center(child: CircularProgressIndicator())
              : SingleChildScrollView(
                  child: Padding(
                    padding: const EdgeInsets.only(
                        left: 25, right: 0, top: 25, bottom:25),
                    child: Column(
                      children: [
                        SingleChildScrollView(child: _BookCardColumn()),
                      ],
                    ),
                  ),
                );
        },
      ),
    );
  }

  Column _BookCardColumn() {
    Column column =  Column(children: []);
    for (int i = 0; i < bookList.length; i++) {
      BookCard bookCard = BookCard(
        book: bookList[i],
        index: (bookList.length - i),
        // isBookRead: isBookReadByUser(bookList[i]),
      );
      column.children.add(bookCard);
    }
    return column;
  }

  bool isBookReadByUser(Book book) {
    bool isBookRead = false;
    for (var element in bookList) {
      if (element.id == book.id) {
        isBookRead = true;
        continue;
      }
    }
    log.i("Book is Read : $isBookRead");
    return isBookRead;
  }
}

/*
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

String getShortTitle(String title) {
  int index = 35;
  if (title.trim().length > index) {
    return "${title.substring(0, index).trim()}...";
  }
  return title;
}
*/
