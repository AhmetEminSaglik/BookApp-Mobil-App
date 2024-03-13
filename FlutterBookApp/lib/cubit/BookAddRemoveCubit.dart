import 'dart:ffi';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';

class BookAddRemoveCubit extends Cubit<bool> {
  BookAddRemoveCubit() : super(false);
  bool isBookRead = false;
  var log = Logger(printer: PrettyPrinter(colors: false));

  void updateBookReadValue(bool isBookRead) {
    this.isBookRead = isBookRead;
    emit(this.isBookRead);
  }

  void addBook() {
    isBookRead = true;
    emit(isBookRead);
  }

  void removeBook() {
    isBookRead = false;
    emit(isBookRead);
  }
}
