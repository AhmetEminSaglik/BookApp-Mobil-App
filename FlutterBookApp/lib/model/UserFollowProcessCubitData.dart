import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';

class UserFollowProcessCubitData {
  late UserFriendDTO userFriendDTO;
  bool userIsFollowed = false;

  UserFollowProcessCubitData({required this.userFriendDTO});

  @override
  String toString() {
    return 'UserFollowProcessCubitData{userFriendDTO: $userFriendDTO, userIsFollowed: $userIsFollowed}';
  }
}
