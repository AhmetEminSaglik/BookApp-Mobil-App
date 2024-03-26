import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

class FollowerRemoveCubit extends Cubit<UserFriendDTO?> {
  FollowerRemoveCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));

  // bool followerIsRemoved = false;
  late UserFriendDTO? userFriendDTO;

  void removeFromList(UserFriendDTO userFriendDTO) {
    emit(userFriendDTO);
  }

  void updateIsCompleted() {
    userFriendDTO = null;
    emit(userFriendDTO);
  }
}
