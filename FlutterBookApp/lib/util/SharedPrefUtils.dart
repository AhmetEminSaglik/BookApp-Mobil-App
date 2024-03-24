import 'package:logger/logger.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../enum/EnumUserProp.dart';
import '../model/User.dart';

class SharedPrefUtils {
  static var _sp;
  static var log = Logger(printer: PrettyPrinter(colors: false));

  static Future<void> setLoginDataUser(User user) async {
    await initiliazeSharedPref();
    _addDataToSP(user);
  }

  static Future<void> initiliazeSharedPref() async {
    _sp ??= await SharedPreferences.getInstance(); // if null, create instance
  }

  static void _addDataToSP(User user) {
    _sp.setInt(EnumUserProp.ID.name, user.id);
    _sp.setString(EnumUserProp.USERNAME.name, user.username);
    _sp.setString(EnumUserProp.NAME.name, user.name);
    _sp.setString(EnumUserProp.LASTNAME.name, user.lastname);
    _sp.setString(EnumUserProp.PASSWORD.name, user.password);
    _sp.setString(EnumUserProp.GENDER.name, user.gender);
    _sp.setInt(EnumUserProp.TOTAL_Following.name, user.following);
    _sp.setInt(EnumUserProp.TOTAL_FOLLOWERS.name, user.followers);
    _sp.setString(EnumUserProp.IMG_URL.name, user.imgUrl);
  }

  static User getUser() {
    return User(
        id: _sp.getInt(EnumUserProp.ID.name),
        name: _sp.getString(EnumUserProp.NAME.name),
        lastname: _sp.getString(EnumUserProp.LASTNAME.name),
        username: _sp.getString(EnumUserProp.USERNAME.name),
        gender: _sp.getString(EnumUserProp.GENDER.name),
        password: _sp.getString(EnumUserProp.PASSWORD.name),
        followers: _sp.getInt(EnumUserProp.TOTAL_FOLLOWERS.name),
        following: _sp.getInt(EnumUserProp.TOTAL_Following.name),
        imgUrl: _sp.getString(EnumUserProp.IMG_URL.name));
  }

  static int getUserId() {
    var value = _sp.getInt(EnumUserProp.ID.name);
    return value ?? -1;
  }

  static String getUsername() {
    String? value = _sp.getString(EnumUserProp.USERNAME.name);
    return value ?? "";
  }

  static String getPassword() {
    String? value = _sp.getString(EnumUserProp.PASSWORD.name);
    return value ?? "";
  }

  static get sp => _sp;
}
