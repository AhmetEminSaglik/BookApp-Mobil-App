import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/MyBookReadScreenCubit.dart';
import 'package:flutter_book_app/model/Recommend.dart';
import 'package:flutter_book_app/product/BookCard.dart';
import 'package:logger/logger.dart';
import '../httprequest/HttpRequestBook.dart';
import '../model/Book.dart';
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

  Future<void> _retrieveReadBookData() async {
    log.i("Read book data is retrieved");
    context.read<MyReadBookScreenCubit>().resetUpdateValue();
    await _retrieveReadBookList();

    if (mounted) {
      setState(() {
        isLoading = false;
      });
    }
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
      body: RefreshIndicator(
        onRefresh: _retrieveReadBookData,
        child: BlocBuilder<MyReadBookScreenCubit, bool>(
          builder: (context, state) {
            if (state) {
              _retrieveReadBookData();
              // context.read<MyReadBookScreenCubit>().resetUpdateValue();
            }
            return state
                ? const Center(child: CircularProgressIndicator())
                : ListView(children: [
                    Padding(
                      padding: const EdgeInsets.only(
                          left: 25, right: 0, top: 25, bottom: 25),
                      child: Column(
                        children: [
                          _BookCardColumn(),
                        ],
                      ),
                    ),
                  ]);
          },
        ),
      ),
    );
  }

  Column _BookCardColumn() {
    Column column = Column(children: []);
    for (int i = 0; i < bookList.length; i++) {
      RecommendData<Book> recData = RecommendData(data: bookList[i]);
      BookCard bookCard =
          BookCard(index: bookList.length - i, recData: recData);
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
