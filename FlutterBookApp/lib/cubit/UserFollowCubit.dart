import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

import '../model/UserFollowProcessCubitData.dart';

class UserFollowProcessCubit extends Cubit<UserFollowProcessCubitData?> {
  UserFollowProcessCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));
  late UserFollowProcessCubitData data;

  // late UserFriendDTO userFriendDTO;
  Future<UserFollowProcessCubitData> followUser(
      UserFriendDTO userFriendDTO) async {
    data = UserFollowProcessCubitData(userFriendDTO: userFriendDTO);
    data.userIsFollowed = await HttpRequestUser.followUser(userFriendDTO.id);
    print("userIsFollowed : ${data.userIsFollowed}");
    emit(data);
    return data;
  }

  void unfollowUser(UserFriendDTO userFriendDTO) async {
    // bool result = await HttpRequestUser.followUser(userFriendDTO.id);
    // return result;
  }
}
