import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/httprequest/HttpRequestUser.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

import '../model/UserFollowProcessCubitData.dart';

class UserFollowProcessCubit extends Cubit<UserFollowProcessCubitData?> {
  UserFollowProcessCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));
  late UserFollowProcessCubitData data;

  Future<UserFollowProcessCubitData> followUser(
      UserFriendDTO userFriendDTO) async {
    data = UserFollowProcessCubitData(userFriendDTO: userFriendDTO);
    data.userIsFollowed = await HttpRequestUser.followUser(userFriendDTO.id);
    emit(data);
    return data;
  }

  Future<UserFollowProcessCubitData> unfollowUser(
      UserFriendDTO userFriendDTO) async {
    data = UserFollowProcessCubitData(userFriendDTO: userFriendDTO);
    bool success = await HttpRequestUser.unfollowUser(userFriendDTO.id);
    if (success) {
      data.userIsFollowed = false;
    } else {
      data.userIsFollowed = true;
    }
    emit(data);
    return data;
  }
}
