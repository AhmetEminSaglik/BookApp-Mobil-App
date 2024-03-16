import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:logger/logger.dart';

import '../../model/User.dart';

class UserCard extends StatefulWidget {
  UserCard({super.key, required this.userDTO, required this.index});

  int index;

  UserFriendDTO userDTO;

  @override
  State<UserCard> createState() => _UserCardState();
}

class _UserCardState extends State<UserCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  double cardHeight = ResponsiveDesign.height() / 12;
  double cardWidth = ResponsiveDesign.width();
  late UserFriendDTO userDTO;
  final double _fontSize = ResponsiveDesign.height() / 50;

  void _fillValues() {
    userDTO = widget.userDTO;
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _fillValues();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      color:
          widget.index % 2 == 0 ? ProductColor.darkWhite1 : ProductColor.white,
      child: Padding(
        padding:
            const EdgeInsets.only(left: 20, right: 20, top: 10, bottom: 10),
        child: Row(
          children: [
            Container(
              width: ResponsiveDesign.width()/12,
              child: Text("${widget.index + 1}-) ",
                  style: TextStyle(fontSize: _fontSize)),
            ),
            getUserImage(),
            const SizedBox(width: 10),
            Expanded(child: getUserFullName()),
            Padding(
              padding: EdgeInsets.only(right: ResponsiveDesign.width() / 8),
              child: getUsersReadBookCount(),
            ),
          ],
        ),
      ),
    );
  }

  Text getUsersReadBookCount() {
    return Text("${userDTO.totalReadBook}",
      style: TextStyle(fontSize: _fontSize),
    );
  }

  Text getUserFullName() {
    return Text(
      "${userDTO.name} ${userDTO.lastname}",
      style: TextStyle(fontSize: _fontSize),
    );
  }

  ClipRRect getUserImage() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(100),
      child: Image.network(
        userDTO.imgurl,
        width: 50,
        height: 50,
        fit: BoxFit.fill,
      ),
    );
  }
}
