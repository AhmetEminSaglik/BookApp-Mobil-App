import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/login/LoginCubit.dart';
import 'package:flutter_book_app/pages/LoginScreen.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    ResponsiveDesign(mediaQueryData: MediaQuery.of(context));
    return MultiBlocProvider(
        providers: [BlocProvider(create: (context) => LoginCubit())],
        child: MaterialApp(
          debugShowCheckedModeBanner: false,
          home: LoginScreen(),
        ));
  }
}
