import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/Recommend.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/product/RecommendUserDesignDecoration.dart';
import 'package:logger/logger.dart';
import '../cubit/UserFollowCubit.dart';
import '../model/Book.dart';
import '../model/UserFollowProcessCubitData.dart';
import '../util/CustomSnackBar.dart';
import '../util/ProductColor.dart';
import '../util/ResponsiveDesign.dart';
import 'BookDesignDecoration.dart';

class RecommendUserCard extends StatefulWidget {
  late RecommendData<UserFriendDTO> recData;
  late int index;

  RecommendUserCard({super.key, required this.index, required this.recData});

  @override
  State<RecommendUserCard> createState() => _RecommendUserCardState();
}

class _RecommendUserCardState extends State<RecommendUserCard> {
  var log = Logger(printer: PrettyPrinter(colors: false));

  final double imgWidth = ResponsiveDesign.width() / 5.5;
  final double imgHeight = ResponsiveDesign.height() / 6;
  final double padding = ResponsiveDesign.height() / 65;
  final double fontsize = 15;
  bool isLoading = true;
  bool isUserFollowed = false;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: imgHeight * 1.8,
      child: Column(
        children: [
          Row(
            children: [
              Stack(
                children: [
                  InkWell(
                    onTap: () {},
                    child: getRecommendCardContent(),
                  ),
                  Padding(
                    padding:
                        EdgeInsets.only(top: ResponsiveDesign.height() / 20),
                    child: Row(
                      children: [
                        getImage(context),
                        getChevron(),
                      ],
                    ),
                  ),
                ],
              ),
            ],
          )
        ],
      ),
    );
  }

  bool isBookReadByUser(Book book) {
    bool isBookRead = false;
    return isBookRead;
  }

  Padding getRecommendCardContent() {
    final double contentWidth = imgWidth / 2 + ResponsiveDesign.width() / 25;
    return Padding(
      padding: EdgeInsets.only(left: contentWidth),
      child: ContainerWithBoxDecoration(
        child: Container(
          // width: 295,
          width: ResponsiveDesign.width() - contentWidth - 5.5 * padding,
          height: imgHeight + 6 * padding,
          color: ProductColor.white,
          child: Padding(
            padding: EdgeInsets.only(left: imgWidth, top: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  getShortTitle(
                      "${widget.recData.data.name} ${widget.recData.data.lastname}"),
                  maxLines: 2,
                  style: TextStyle(
                      fontSize: fontsize + 1, fontWeight: FontWeight.bold),
                ),
                getSpace(),
                Text(
                  widget.recData.by,
                  style: TextStyle(
                      fontSize: fontsize,
                      fontWeight: FontWeight.bold,
                      color: widget.recData.color),
                ),
                getSpace(),
                Text(
                  "Total Read Book : ${widget.recData.data.totalReadBook}",
                  style: TextStyle(
                      fontSize: fontsize,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                Text(
                  "Following : ${widget.recData.data.totalFollowing}",
                  style: TextStyle(
                      fontSize: fontsize,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                Text(
                  "Followers : ${widget.recData.data.totalFollowers}",
                  style: TextStyle(
                      fontSize: fontsize,
                      fontWeight: FontWeight.bold,
                      color: ProductColor.grey),
                ),
                getSpace(3),
                BlocBuilder<UserFollowProcessCubit,
                    UserFollowProcessCubitData?>(builder: (builder, state) {
                  print("widget.recData.data : ${widget.recData.data}");
                  if (state != null &&
                      state.userFriendDTO == widget.recData.data) {
                    isUserFollowed = state.userIsFollowed;
                  }
                  if (isUserFollowed) {
                    return _FollowButton(
                      userFriendDTO: widget.recData.data,
                      btnColor: ProductColor.red,
                      onClickUserBtn: unfollowUserBtnAction,
                    );
                  } else {
                    return _FollowButton(
                      userFriendDTO: widget.recData.data,
                      btnColor: ProductColor.green,
                      onClickUserBtn: followUserBtnAction,
                    );
                  }
                }),
              ],
            ),
          ),
        ),
      ),
    );
  }

  void followUserBtnAction(UserFriendDTO userFriendDTO) async {
    UserFollowProcessCubitData data =
        await context.read<UserFollowProcessCubit>().followUser(userFriendDTO);
  }

  void unfollowUserBtnAction(UserFriendDTO userFriendDTO) async {
    UserFollowProcessCubitData data = await context
        .read<UserFollowProcessCubit>()
        .unfollowUser(userFriendDTO);
    String msg =
        "${userFriendDTO.name} ${userFriendDTO.lastname} is not following anymore.";
    showToastMsg(msg);
  }

  void showToastMsg(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(CustomSnackBar.getSnackBar(msg));
  }

  SizedBox getSpace([int value = 1]) {
    return SizedBox(
      height: (5 * value).toDouble(),
    );
  }

  Padding getChevron() {
    return Padding(
      padding: EdgeInsets.only(
          left: ResponsiveDesign.width() - imgWidth - 8 * padding),
      child: Container(
        decoration: BoxDecoration(
            border: Border.all(width: 2, color: ProductColor.white),
            borderRadius: const BorderRadius.all(Radius.circular(50)),
            color: ProductColor.white,
            boxShadow: [
              BoxShadow(
                color: Colors.deepOrange.withOpacity(0.7),
                spreadRadius: 3,
                blurRadius: 1,
                offset: const Offset(0, 1), // Gölge ofseti (x, y)
              ),
            ]),
        height: 25,
        width: 25,
        child: const Icon(Icons.chevron_right, color: ProductColor.red),
      ),
    );
  }

  Padding getImage(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(top: padding),
        child: Padding(
          padding: const EdgeInsets.all(10),
          child: RecommendUserDesignDecoration(
            child: ClipRRect(
              borderRadius: BorderRadius.circular(100),
              child: Image.network(
                widget.recData.data.imgUrl,
                fit: BoxFit.fitWidth,
                width: imgWidth,
              ),
            ),
          ),
        ));
  }

  String getShortDesc(String desc) {
    if (desc.trim().isEmpty) {
      return "- - -";
    }
    int index = 60;
    String shortDesc = desc;
    if (desc.trim().length > index) {
      shortDesc = shortDesc.replaceAll("\n", " ");
      shortDesc = "${shortDesc.substring(0, index).trim()}...";
    }
    return shortDesc;
  }

  String getShortTitle(String title) {
    int index = 40;
    if (title.trim().length > index) {
      return "${title.substring(0, index).trim()}...";
    }
    return title;
  }
}

class _FollowButton extends StatefulWidget {
  const _FollowButton(
      {required this.userFriendDTO,
      required this.btnColor,
      required this.onClickUserBtn});

  final btnColor;
  final void Function(UserFriendDTO userFriendDTO) onClickUserBtn;
  final UserFriendDTO userFriendDTO;

  @override
  State<_FollowButton> createState() => _FollowButtonState();
}

class _FollowButtonState extends State<_FollowButton> {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 30,
      width: ResponsiveDesign.width() / 3,
      child: ElevatedButton(
        onPressed: () {
          widget.onClickUserBtn(widget.userFriendDTO);
        },
        style: ButtonStyle(
          shape: MaterialStateProperty.all<RoundedRectangleBorder>(
              RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(10.0),
          )),
          backgroundColor:
              MaterialStateColor.resolveWith((states) => widget.btnColor),
          foregroundColor:
              MaterialStateColor.resolveWith((states) => ProductColor.white),
        ),
        child: const Text(
          "Follow",
          style: TextStyle(fontSize: 17),
        ),
      ),
    );
  }
}
