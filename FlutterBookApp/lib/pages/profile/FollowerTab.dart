import 'package:flutter/cupertino.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/util/ProductColor.dart';

import '../../model/User.dart';
import '../../util/ResponsiveDesign.dart';
import 'UserCard.dart';

class FollowersTab extends StatefulWidget {
  FollowersTab({super.key, required this.list});

  List<User> list;

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
      UserCard userCard = UserCard(user: widget.list[i],index: i,);
      column.children.add(userCard);
    }
    return column;
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Column(
      children: [
        Padding(
          padding: const EdgeInsets.only(left: 30,top: 10,bottom: 10),
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
        getUserList(),
      ],
    ));
  }
}
