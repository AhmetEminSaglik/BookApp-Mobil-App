import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyReadBookScreen extends StatefulWidget {
  const MyReadBookScreen({super.key});

  @override
  State<MyReadBookScreen> createState() => _MyReadBookScreenState();
}

class _MyReadBookScreenState extends State<MyReadBookScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Text("MyReadBook Screen"),
      ),
    );
  }
}
