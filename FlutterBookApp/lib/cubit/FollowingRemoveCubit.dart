import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

class FollowingRemoveCubit extends Cubit<UserFriendDTO?> {
  FollowingRemoveCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));

  void removeFromList(UserFriendDTO userFriendDTO) {
    print(">>>>>>>>>>>the follower will be removed");
    emit(userFriendDTO);
  }
}
