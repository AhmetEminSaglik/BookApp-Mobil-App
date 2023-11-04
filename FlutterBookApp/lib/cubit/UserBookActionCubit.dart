import 'dart:math';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';

class UserBookActionCubit extends Cubit<void> {
  UserBookActionCubit(super.initialState);

  bool result = false;

  Future<bool> createUserReadBookConnection(int bookId) async {
    final random = new Random();
    final rate = random.nextInt(9) + 1;
    await HttpRequestUser.setUserReadBookConnection(
        SharedPrefUtils.getUserId(), bookId, rate);
    result = true;
    emit(result);
    return true;
  }
  Future<bool> destroyUserReadBookConnection(int bookId) async {
    // final random = new Random();
    // final rate = random.nextInt(9) + 1;
    // await HttpRequestUser.setUserReadBookConnection(
    //     SharedPrefUtils.getUserId(), bookId, rate);
    // result = true;
    // emit(result);
    return true;
  }


}
