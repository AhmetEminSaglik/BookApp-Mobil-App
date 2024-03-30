import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:logger/logger.dart';

class ConnectionsValueCubit extends Cubit<bool> {
  ConnectionsValueCubit() : super(false);
  var log = Logger(printer: PrettyPrinter(colors: false));
  bool willBeUpdated = false;

  void update() {
    willBeUpdated = true;
    emit(willBeUpdated);
    reset();
  }
  void reset() {
    willBeUpdated = false;
    emit(willBeUpdated);
  }
}
