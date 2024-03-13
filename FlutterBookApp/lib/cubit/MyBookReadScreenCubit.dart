import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';

class MyReadBookScreenCubit extends Cubit<bool> {
  bool _willBeUpdated = false;
  var _log = Logger(printer: PrettyPrinter(colors: false));

  MyReadBookScreenCubit() : super(false);

  void updateBookList() {
    _willBeUpdated = true;
    emit(_willBeUpdated);
    _log.i("update Book List func calisti ");
  }

  void resetUpdateValue() {
    _willBeUpdated = false;
    emit(_willBeUpdated);
    _log.i("reset Update Value func calisti ");

  }
}
