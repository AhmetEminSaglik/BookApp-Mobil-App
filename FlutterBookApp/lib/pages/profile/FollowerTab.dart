import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/FollowerRemoveCubit.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import '../../httprequest/HttpRequestUser.dart';
import '../../util/CustomSnackBar.dart';
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
        removeConnection: removeFollower,
        /*removeConnection: (userFriendDTO){
          print("removeConnection function calisti");
          // print("gelen data : ${widget.list[i]}}");
          // removeFollower(widget.list[i]);
          print("gelen data : $userFriendDTO}");
          removeFollower(userFriendDTO);
        },*/
      );
      column.children.add(userCard);
    }
    return column;
  }

  void removeFollower(UserFriendDTO userFriendDTO) async {
    print("removeFollower function calisti");
    bool result = await HttpRequestUser.removeFollower(userFriendDTO.id);
    String msg = "";
    if (result) {
      msg =
          "${userFriendDTO.name} ${userFriendDTO.lastname} is succesfully removed";
      context.read<FollowerRemoveCubit>().removeFromList(userFriendDTO);
    } else {
      msg = "Failed. Follower is not removed.";
    }
    showToastMsg(msg);
  }

  void showToastMsg(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(CustomSnackBar.getSnackBar(msg));
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
