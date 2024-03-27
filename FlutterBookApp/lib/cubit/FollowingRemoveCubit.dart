import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/UserFriendDTOCubitData.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

class FollowingRemoveCubit extends Cubit<UserFriendDTOCubitData?> {
  FollowingRemoveCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));

  // bool followerIsRemoved = false;
  late UserFriendDTOCubitData userFriendDTOCubitData;

  void removeFromList(UserFriendDTO userFriendDTO) {
    userFriendDTOCubitData = UserFriendDTOCubitData(userFriendDTO);
    emit(userFriendDTOCubitData);
  }

  void updateFollowingNumber() {
    userFriendDTOCubitData.updateProfilFollowerValue = true;
    print(">>>>>>>>>>>followers will be updated");
    emit(userFriendDTOCubitData);
  }
}
