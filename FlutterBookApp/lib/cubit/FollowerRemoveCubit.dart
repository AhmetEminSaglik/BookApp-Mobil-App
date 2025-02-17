import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/UserFriendDTOCubitData.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

class FollowerRemoveCubit extends Cubit<UserFriendDTOCubitData?> {
  FollowerRemoveCubit() : super(null);
  var log = Logger(printer: PrettyPrinter(colors: false));

  late UserFriendDTOCubitData userFriendDTOCubitData;

  void removeFromList(UserFriendDTO userFriendDTO) {
    userFriendDTOCubitData = UserFriendDTOCubitData(userFriendDTO);
    emit(userFriendDTOCubitData);
  }

  void updateFollowersNumber() {
    userFriendDTOCubitData.updateProfilFollowerValue = true;
    emit(userFriendDTOCubitData);
  }
}
