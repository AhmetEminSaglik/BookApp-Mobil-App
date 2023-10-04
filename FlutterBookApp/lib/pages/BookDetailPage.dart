import 'package:flutter/material.dart';
import 'package:flutter_book_app/model/Book.dart';
import 'package:flutter_book_app/util/ProductColor.dart';

class BookDetailPage extends StatefulWidget {
  late Book book;

  BookDetailPage({required this.book});

  @override
  State<BookDetailPage> createState() => _BookDetailPageState();
}

class _BookDetailPageState extends State<BookDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(widget.book.name)),
      backgroundColor: ProductColor.darkBlue,
      body: Container(
        height: 100,
      ),
    );
  }
}
