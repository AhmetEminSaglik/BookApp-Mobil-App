import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';

import '../model/User.dart';

class ProfilScreen extends StatefulWidget {
  const ProfilScreen({super.key});

  @override
  State<ProfilScreen> createState() => _ProfilScreenState();
}

class _ProfilScreenState extends State<ProfilScreen> {
  final User _user = SharedPrefUtils.getUser();
  final double _fontSize = ResponsiveDesign.height() / 55;
  final double _numberFontSize=ResponsiveDesign.height() / 35;
  int bookCount = 0;
  bool isLoading = true;
  final double _profileItemSpace = 10;

  @override
  void initState() {
    super.initState();
    _retrieveUserBookCount();
  }

  _retrieveUserBookCount() async {
    bookCount = await HttpRequestUser.getUserBookCount();
    log(" Book Count is retireved : $bookCount ");
    setState(() {
      isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: isLoading
          ? const Center(child: CircularProgressIndicator())
          : Center(
              child: Column(
                children: [
                  Padding(
                    padding:
                        const EdgeInsets.only(top: 20),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Column(
                          children: [
                            // Image.network(user.imgurl,width:ResponsiveDesign.width()/2,fit: BoxFit.fill),
                            getProfilImage(),
                            getProfileFullName()
                          ],
                        ),
                        SizedBox(width: _profileItemSpace),
                        Padding(
                          padding: EdgeInsets.only(left: _profileItemSpace),
                          child: Row(
                            children: [
                              getTotalRead(),
                              SizedBox(width: _profileItemSpace),
                              getFollowersNumber(),
                              SizedBox(width: _profileItemSpace),
                              getFollowedNumber(),
                            ],
                          ),
                        ),
                      ],
                    ),
                  )
                ],
              ),
            ),
    );
  }

  Column getFollowedNumber() {
    return Column(
      children: [
        Text("${_user.totalFollowed}",
            style: TextStyle(fontSize: _numberFontSize)),
        Text(
          "Following",
          style: TextStyle(fontSize: _fontSize),
        ),
      ],
    );
  }

  Column getFollowersNumber() {
    return Column(
      children: [
        Text("${_user.totalFollowers}",
            style: TextStyle(fontSize: _numberFontSize)),
        Text(
          "Followers",
          style: TextStyle(fontSize: _fontSize),
        ),
      ],
    );
  }

  Column getTotalRead() {
    return Column(
      children: [
        Text("$bookCount", style: TextStyle(fontSize: _numberFontSize)),
        Text(
          "Read Books",
          textAlign: TextAlign.center,
          style: TextStyle(fontSize: _fontSize),
        ),
      ],
    );
  }

  Text getProfileFullName() {
    return Text(
      "${_user.name} ${_user.lastname}",
      style: TextStyle(fontSize: _fontSize),
    );
  }

  ClipRRect getProfilImage() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(100),
      child: Image.network(_user.imgurl,
          height: ResponsiveDesign.height() / 10, fit: BoxFit.fill),
    );
  }
}
