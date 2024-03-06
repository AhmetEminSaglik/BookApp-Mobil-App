import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/httprequest/Model/ResponseEntity.dart';
import 'package:flutter_book_app/pages/HomeScreen.dart';
import 'package:flutter_book_app/pages/MyReadBookScreen.dart';
import 'package:flutter_book_app/repo/UserRepository.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:logger/logger.dart';

import '../../enum/EnumLoginState.dart';
import '../../model/User.dart';
import '../../pages/RecommendScreen.dart';

class LoginCubit extends Cubit<EnumLoginState> {
  LoginCubit() : super(EnumLoginState.LoginInitial);
  User? user;
  var log = Logger(printer: PrettyPrinter(colors: false));

  void login(BuildContext context, String username, String password) async {
    emit(EnumLoginState.LoginLoading);
    ResponseEntity respEntity = await HttpRequestUser.login(username, password);
    if (respEntity.success) {
      user = UserRepository.parseUser(respEntity.data);
      SharedPrefUtils.setLoginDataUser(user!);
      emit(EnumLoginState.LoginSuccess);
      _navigatePage(context);
    } else {
      emit(EnumLoginState.LoginError);
      log.e("ERROR OCCURED : ${respEntity.message}");
    }
    // print("Gelen User : $user");
    // emit(EnumLoginState.LoginSuccess);
  }

  void _navigatePage(BuildContext context) {
    Navigator.push(context,
        MaterialPageRoute(builder: (context) => HomeScreen()));
  }
}
