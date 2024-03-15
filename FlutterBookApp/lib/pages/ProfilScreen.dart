import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';

import '../model/User.dart';

class ProfilScreen extends StatefulWidget {
  const ProfilScreen({super.key});

  @override
  State<ProfilScreen> createState() => _ProfilScreenState();
}

class _ProfilScreenState extends State<ProfilScreen> {
  User _user = SharedPrefUtils.getUser();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(20.0),
            child: Row(
              children: [
                Column(
                  children: [
                    // Image.network(user.imgurl,width:ResponsiveDesign.width()/2,fit: BoxFit.fill),
                    getProfilImage(),
                    getProfileFullName()
                  ],
                ),
                Padding(
                  padding: EdgeInsets.only(left: 15, right: 15),
                  child: Row(
                    children: [
                      getFollowersNumber(),
                      const SizedBox(width: 20),
                      getFollowedNumber()
                    ],
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }

  Column getFollowedNumber() {
    return Column(
      children: [
        Text("${_user.totalFollowed}", style: const TextStyle(fontSize: 20)),
        const Text(
          "Followed",
          style: TextStyle(fontSize: 20),
        ),
      ],
    );
  }

  Column getFollowersNumber() {
    return Column(
      children: [
        Text("${_user.totalFollowers}", style: const TextStyle(fontSize: 20)),
        const Text(
          "Followers",
          style: TextStyle(fontSize: 20),
        ),
      ],
    );
  }

  Text getProfileFullName() {
    return Text(
      "${_user.name} ${_user.lastname}",
      style: const TextStyle(fontSize: 20),
    );
  }

  ClipRRect getProfilImage() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(100),
      child: Image.network(_user.imgurl,
          height: ResponsiveDesign.height() / 6, fit: BoxFit.fill),
    );
  }
}
