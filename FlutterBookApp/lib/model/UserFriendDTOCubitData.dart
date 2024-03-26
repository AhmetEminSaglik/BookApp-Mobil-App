import 'dto/UserFriendDTO.dart';

class UserFriendDTOCubitData {
  late UserFriendDTO _userFriendDTO;
  bool _updateProfilFollowerValue = false;

  UserFriendDTOCubitData(this._userFriendDTO);


  UserFriendDTO get userFriendDTO => _userFriendDTO;

  set userFriendDTO(UserFriendDTO value) {
    _userFriendDTO = value;
  }

  bool get updateProfilFollowerValue => _updateProfilFollowerValue;

  set updateProfilFollowerValue(bool value) {
    _updateProfilFollowerValue = value;
  }
}
