import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:logger/logger.dart';

class MyReadBookScreenCubit extends Cubit<bool> {
  bool _willBeUpdated = false;
  final _log = Logger(printer: PrettyPrinter(colors: false));

  MyReadBookScreenCubit() : super(false);

  void updateBookList() {
    _willBeUpdated = true;
    _log.i("update Book List func calisti ");
    emit(_willBeUpdated);
  }

  void resetUpdateValue() {
    if (_willBeUpdated) {
      _willBeUpdated = false;
      _log.i("reset Update Value func calisti ");
      emit(_willBeUpdated);
    }
  }
}
