import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/BookAddRemoveCubit.dart';
import 'package:flutter_book_app/cubit/MyBookReadScreenCubit.dart';
import 'package:flutter_book_app/cubit/UserBookActionCubit.dart';
import 'package:flutter_book_app/cubit/login/LoginCubit.dart';
import 'package:flutter_book_app/cubit/recommendedbook/BookCubit.dart';
import 'package:flutter_book_app/pages/LoginScreen.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    ResponsiveDesign(mediaQueryData: MediaQuery.of(context));
    print(
        "ResponsiveDesign : height : ${ResponsiveDesign.height()} , width : ${ResponsiveDesign.width()}");

    return MultiBlocProvider(
        providers: [
          BlocProvider(create: (context) => LoginCubit()),
          BlocProvider(create: (context) => BookCubit()),
          BlocProvider(create: (context) => UserBookActionCubit(0)),
          BlocProvider(create: (context) => BookAddRemoveCubit()),
          BlocProvider(create: (context) => MyReadBookScreenCubit()),
        ],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          home: LoginScreen(),
        ));
  }
}
