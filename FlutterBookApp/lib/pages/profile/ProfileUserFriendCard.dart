import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/FollowerRemoveCubit.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:logger/logger.dart';

import '../../httprequest/HttpRequestUser.dart';
import '../../util/CustomSnackBar.dart';

class ProfileUserFriendCard extends StatefulWidget {
  ProfileUserFriendCard(
      {super.key, required this.userDTO, required this.index});

  int index;

  UserFriendDTO userDTO;

  @override
  State<ProfileUserFriendCard> createState() => _ProfileUserFriendCardState();
}

class _ProfileUserFriendCardState extends State<ProfileUserFriendCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  double cardHeight = ResponsiveDesign.height() / 12;
  double cardWidth = ResponsiveDesign.width();
  late UserFriendDTO userDTO;
  final double _fontSize = ResponsiveDesign.height() / 50;

  void _fillValues() {
    userDTO = widget.userDTO;
  }

  @override
  Widget build(BuildContext context) {
    _fillValues();
    return Container(
      color:
          widget.index % 2 == 0 ? ProductColor.darkWhite1 : ProductColor.white,
      child: Padding(
        padding:
            const EdgeInsets.only(left: 20, right: 20, top: 10, bottom: 10),
        child: Row(
          children: [
            SizedBox(
              width: ResponsiveDesign.width() / 12,
              child: Text("${widget.index + 1}-) ",
                  style: TextStyle(fontSize: _fontSize)),
            ),
            getImage(),
            const SizedBox(width: 10),
            Expanded(child: getUserFullName()),
            Padding(
              padding: EdgeInsets.only(right: ResponsiveDesign.width() / 8),
              child: getUsersReadBookCount(),
            ),
            InkWell(
              onTap: () => removeFollower(userDTO),
              child: const Icon(
                Icons.delete,
                color: ProductColor.red,
              ),
            )
            /*child: const Icon(Icons.chevron_right, color: ProductColor.red),*/
          ],
        ),
      ),
    );
  }

  void removeFollower(UserFriendDTO userFriendDTO) async {
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

  Text getUsersReadBookCount() {
    return Text(
      "${userDTO.totalReadBook}",
      style: TextStyle(fontSize: _fontSize),
    );
  }

  Text getUserFullName() {
    return Text(
      "${userDTO.name} ${userDTO.lastname}",
      style: TextStyle(fontSize: _fontSize),
    );
  }

  ClipRRect getImage() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(100),
      child: Image.network(
        userDTO.imgUrl,
        width: 50,
        height: 50,
        fit: BoxFit.fill,
      ),
    );
  }
}
