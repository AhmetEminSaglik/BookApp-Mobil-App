import 'package:flutter/cupertino.dart';
import 'package:flutter_book_app/pages/profile/UserCard.dart';

import '../../model/User.dart';

class FollowingTab extends StatefulWidget {
  FollowingTab({super.key, required this.list});

  List<User> list;

  @override
  State<FollowingTab> createState() => _FollowingTabState();
}

class _FollowingTabState extends State<FollowingTab> {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(child: Center(child: getUserList()));
  }

  Column getUserList() {
    Column column = Column(children: []);
    for (int i = 0; i < widget.list.length; i++) {
      UserCard userCard = UserCard(
        user: widget.list[i],
        index: i,
      );
      column.children.add(userCard);
    }
    return column;
  }
}
