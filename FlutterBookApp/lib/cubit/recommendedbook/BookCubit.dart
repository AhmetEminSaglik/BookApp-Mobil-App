import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/Book.dart';
import 'package:flutter_book_app/pages/BookDetailPage.dart';
import 'package:logger/logger.dart';

class BookCubit extends Cubit<bool> {
  bool isBookExist = false;

  BookCubit() : super(false);
  Book? book;
  var log = Logger(printer: PrettyPrinter(colors: false));

  void setBook(Book book) {
    this.book = book;
    isBookExist = true;
    emit(isBookExist);
  }

  void goToDetailPageOfBook(BuildContext context) {
    if (isBookExist && book != null) {
      _navigatePage(context, book!);
    }
  }

  void _navigatePage(BuildContext context, Book book) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => BookDetailPage(
                  book: book,
                )));
  }
}
