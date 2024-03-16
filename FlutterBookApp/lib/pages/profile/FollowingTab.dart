import 'package:flutter/cupertino.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/pages/profile/UserCard.dart';

import '../../model/User.dart';
import '../../util/ProductColor.dart';
import '../../util/ResponsiveDesign.dart';

class FollowingTab extends StatefulWidget {
  FollowingTab({super.key, required this.list});

  List<UserFriendDTO> list;

  @override
  State<FollowingTab> createState() => _FollowingTabState();
}

class _FollowingTabState extends State<FollowingTab> {
  final double _fontSize = ResponsiveDesign.height() / 50;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          color: ProductColor.lightBlue,
          child: Padding(
            padding: const EdgeInsets.only(left: 30, top: 10, bottom: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text(
                  "User Profile",
                  style: TextStyle(fontSize: _fontSize),
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

  Column getUserList() {
    Column column = Column(children: []);
    for (int i = 0; i < widget.list.length; i++) {
      UserCard userCard = UserCard(
        userDTO: widget.list[i],
        index: i,
      );
      column.children.add(userCard);
    }
    return column;
  }
}
