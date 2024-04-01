import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/FollowingRemoveCubit.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/pages/profile/ProfileUserFriendCard.dart';
import '../../httprequest/HttpRequestUser.dart';
import '../../util/CustomSnackBar.dart';
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
          color: ProductColor.pink,
          child: Padding(
            padding: const EdgeInsets.only(left: 30, top: 10, bottom: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text(
                  "User Profile",
                  style: TextStyle(
                      fontSize: _fontSize,
                      color: ProductColor.white,
                      fontWeight: FontWeight.bold),
                ),
                Text(
                  "Total Book Read",
                  style: TextStyle(
                      fontSize: _fontSize,
                      color: ProductColor.white,
                      fontWeight: FontWeight.bold),
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
      ProfileUserFriendCard userCard = ProfileUserFriendCard(
        userDTO: widget.list[i],
        index: i,
        removeConnection: removeFollowing,
      );
      column.children.add(userCard);
    }
    return column;
  }

  void removeFollowing(UserFriendDTO userFriendDTO) async {
    print("removeFollower function calisti");
    bool result = await HttpRequestUser.removeFollowing(userFriendDTO.id);
    String msg = "";
    if (result) {
      msg =
          "${userFriendDTO.name} ${userFriendDTO.lastname} is succesfully removed";
      context.read<FollowingRemoveCubit>().removeFromList(userFriendDTO);
    } else {
      msg = "Failed. Following is not removed.";
    }
    showToastMsg(msg);
  }

  void showToastMsg(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(CustomSnackBar.getSnackBar(msg));
  }
}
