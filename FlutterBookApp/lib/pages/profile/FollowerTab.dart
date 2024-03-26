import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/FollowerRemoveCubit.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import '../../util/ResponsiveDesign.dart';
import 'ProfileUserFriendCard.dart';

class FollowersTab extends StatefulWidget {
  FollowersTab({super.key, required this.list});

  List<UserFriendDTO> list;

  @override
  State<FollowersTab> createState() => _FollowersTabState();
}

class _FollowersTabState extends State<FollowersTab> {
  @override
  void initState() {
    super.initState();
  }

  final double _fontSize = ResponsiveDesign.height() / 50;

  Column getUserList() {
    Column column = Column(children: []);
    for (int i = 0; i < widget.list.length; i++) {
      ProfileUserFriendCard userCard = ProfileUserFriendCard(
        userDTO: widget.list[i],
        index: i,
      );
      column.children.add(userCard);
    }
    return column;
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          color: ProductColor.lightPink,
          child: Padding(
            padding: const EdgeInsets.only(left: 30, top: 10, bottom: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  "User Profile",
                  style: TextStyle(fontSize: _fontSize),
                ),
                SizedBox(
                  width: ResponsiveDesign.width() / 12,
                ),
                Text(
                  "Total Book Read",
                  style: TextStyle(fontSize: _fontSize),
                ),
              ],
            ),
          ),
        ),
        Expanded(
          child: SingleChildScrollView(
            child: Column(
              children: [
                getUserList(),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
