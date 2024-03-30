import 'dart:math';

import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';

class UserBookActionCubit extends Cubit<void> {
  UserBookActionCubit(super.initialState);

  final random = Random();
  bool result = false;

  Future<bool> createUserReadBookConnection(int bookId) async {
    final rate = random.nextInt(9) + 1;
    await HttpRequestUser.setUserReadBookConnection(
        SharedPrefUtils.getUserId(), bookId, rate);
    result = true;
    emit(result);
    return result;
  }

  Future<bool> destroyUserReadBookConnection(int bookId) async {
    await HttpRequestUser.destroyUserReadBookConnection(
        SharedPrefUtils.getUserId(), bookId);
    result = true;
    emit(result);
    return result;
  }
}
