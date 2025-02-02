// import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/FollowingRemoveCubit.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/model/UserFriendDTOCubitData.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/pages/profile/FollowingTab.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/ResponsiveDesign.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:logger/logger.dart';

import '../../cubit/FollowerRemoveCubit.dart';
import '../../cubit/ConnectionsValueCubit.dart';
import '../../model/User.dart';
import 'FollowerTab.dart';

class ProfilScreen extends StatefulWidget {
  ProfilScreen({super.key});

  @override
  State<ProfilScreen> createState() => _ProfilScreenState();
}

class _ProfilScreenState extends State<ProfilScreen> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  final User _user = SharedPrefUtils.getUser();
  final double _fontSize = ResponsiveDesign.height() / 50;
  final double _numberFontSize = ResponsiveDesign.height() / 35;
  static int bookCount = 0;
  bool isLoading = true;
  final double _profileItemSpace = 10;
  static List<UserFriendDTO> followingList = [];
  static List<UserFriendDTO> followerList = [];

  @override
  void initState() {
    super.initState();
    print("MyReadBookScreen > initState ");
    _retrieveAllData();
  }

  _retrieveAllData() async {
    bookCount = await HttpRequestUser.getUserBookCount();
    followingList = await HttpRequestUser.getFollowingUserList();
    followerList = await HttpRequestUser.getFollowerUserList();

    if (mounted) {
      setState(() {
        isLoading = false;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: ProductColor.darkWhite,
      body: isLoading
          ? const Center(child: CircularProgressIndicator())
          : Column(
              children: [
                getProfilDataContainer(),
                Expanded(
                  child: Padding(
                    padding: const EdgeInsets.only(
                        left: 20, right: 20, top: 25, bottom: 10),
                    child: SizedBox(
                      width: ResponsiveDesign.width(),
                      // height: 300,
                      child: DefaultTabController(
                          length: 2,
                          child: Scaffold(
                            appBar: AppBar(
                              toolbarHeight: 0,
                              leading: null,
                              automaticallyImplyLeading: false,
                              bottom: TabBar(
                                indicatorColor: ProductColor.pink,
                                // dividerColor: ProductColor.lightBlue,
                                labelColor: ProductColor.pink,
                                // labelColor: ProductColor.pink ,
                                overlayColor: MaterialStateColor.resolveWith(
                                    (states) => ProductColor.lightPurple),
                                unselectedLabelColor: ProductColor.black,
                                tabs: [
                                  Tab(
                                      child: Text("Followers",
                                          style: TextStyle(
                                              fontSize: _fontSize + 1))),
                                  Tab(
                                      child: Text("Following",
                                          style: TextStyle(
                                              fontSize: _fontSize + 1))),
                                ],
                              ),
                            ),
                            body: TabBarView(
                              children: [
                                BlocBuilder<FollowerRemoveCubit,
                                        UserFriendDTOCubitData?>(
                                    builder: (builder, state) {
                                  log.i(
                                      "${state?.userFriendDTO.name} ${state?.userFriendDTO.lastname} : >>BlocBuilder<FollowerRemoveCubit,>>> girdi");
                                  if (state != null &&
                                      followerList
                                          .contains(state.userFriendDTO)) {
                                    removeFromList(
                                        followerList, state.userFriendDTO);
                                  }
                                  return FollowersTab(list: followerList);
                                }),
                                BlocBuilder<FollowingRemoveCubit,
                                    UserFriendDTO?>(builder: (builder, state) {
                                  if (state != null &&
                                      followingList.contains(state)) {
                                    removeFromList(followingList, state);
                                  }
                                  return FollowingTab(list: followingList);
                                }),
                              ],
                            ),
                          )),
                    ),
                  ),
                )
              ],
            ),
    );
  }

  void removeFromList(List<Object> list, UserFriendDTO object) {
    list.remove(object);
    context.read<ConnectionsValueCubit>().update();
  }

  Center getProfilDataContainer() {
    return Center(
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.only(top: 20),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Column(
                  children: [
                    // Image.network(user.imgUrl,width:ResponsiveDesign.width()/2,fit: BoxFit.fill),
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
                      getFollowingNumber(),
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

  Column getFollowingNumber() {
    return Column(
      children: [
        BlocBuilder<ConnectionsValueCubit, bool>(builder: (builder, state) {
          return Text("${followingList.length}",
              style: TextStyle(fontSize: _numberFontSize));
        }),
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
        BlocBuilder<ConnectionsValueCubit, bool>(builder: (builder, state) {
          return Text("${followerList.length}",
              style: TextStyle(fontSize: _numberFontSize));
        }),
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
      child: Image.network(_user.imgUrl,
          height: ResponsiveDesign.height() / 10, fit: BoxFit.fill),
    );
  }
}
