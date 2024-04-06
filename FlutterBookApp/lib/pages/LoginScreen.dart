import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/login/LoginCubit.dart';
import 'package:flutter_book_app/enum/EnumLoginState.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:logger/logger.dart';

import '../util/ProductColor.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: DecoratedBox(
            decoration: const BoxDecoration(
                image: DecorationImage(
                    // image: AssetImage("images/8.jpg"),
                    image: AssetImage("images/9.png"),
                    // image: AssetImage("images/10.jpg"),
                    fit: BoxFit.none)),
            child: Center(
              child: Padding(
                padding: const EdgeInsets.all(20.0),
                child: ClipRRect(
                    borderRadius: BorderRadius.circular(50),
                    child: BackdropFilter(
                        filter: ImageFilter.blur(sigmaX: 5, sigmaY: 5),
                        child: Container(
                            decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(50),
                                border: Border.all(
                                    width: 5, color: ProductColor.black)),
                            child: const LoginForm()))),
              ),
            )));
  }
}

class LoginForm extends StatefulWidget {
  const LoginForm({super.key});

  @override
  _LoginFormState createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  final TextEditingController _usernameController =
      TextEditingController(text: "user2");
  final TextEditingController _passwordController =
      TextEditingController(text: "pass");

  Color hintColor = ProductColor.black;
  Color textColor = ProductColor.pink;
  Color iconColor = ProductColor.pink;
  Color itemBackgroundColor = ProductColor.pink;
  Color itemForegroundColor = ProductColor.black;

  @override
  Widget build(BuildContext context) {
    // Login();
    autoLogin();
    return Padding(
      padding: const EdgeInsets.all(15.0),
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // const Image(image: AssetImage('images/21.jpg')),
            SizedBox(
              child: Icon(
                FontAwesomeIcons.book,
                size: 125.0, // İstediğiniz boyuta ayarlayabilirsiniz
                color: itemBackgroundColor, // İstediğiniz rengi seçebilirsiniz
              ),
            ),
            const SizedBox(
              height: 50,
            ),
            _getUsernameField(),
            const SizedBox(
              height: 50,
            ),
            _getPasswordField(),
            const SizedBox(
              height: 50,
            ),
            /*
            *  return BlocBuilder<ProfilUpdatedCubit, bool>(
              builder: (builder, isUpdated) {}
              * */
            getWidgetFoLoginState() ?? Container(),
            SizedBox(
              width: 150,
              height: 40,
              child: ElevatedButton(
                  onPressed: () {
                    manuelLogin(
                        username: _usernameController.text,
                        password: _passwordController.text);
                  },
                  style: ButtonStyle(
                      backgroundColor: MaterialStateColor.resolveWith(
                          (states) => itemBackgroundColor),
                      foregroundColor: MaterialStateColor.resolveWith(
                          (states) => itemForegroundColor)),
                  child: const Text(
                    "Login",
                    style: TextStyle(fontSize: 23,fontWeight: FontWeight.bold),
                  )),
            ),
          ],
        ),
      ),
    );
  }

  Widget getWidgetFoLoginState() {
    return BlocBuilder<LoginCubit, EnumLoginState>(
      builder: (builder, state) {
        if (state == EnumLoginState.LoginLoading) {
          return const Center(child: CircularProgressIndicator());
        }
        return const SizedBox();
      },
    );
  }

  void autoLogin() async {
    await SharedPrefUtils.initiliazeSharedPref();
    String username = SharedPrefUtils.getUsername();
    String pass = SharedPrefUtils.getPassword();
    if (username.isNotEmpty && pass.isNotEmpty) {
      // print("Username is not Empty : ($username)");
      // print("password is not Empty : ($pass)");
      login(username: username, password: pass);
    }
  }

  void manuelLogin({required String username, required String password}) {
    login(username: username, password: password);
  }

  void login({required String username, required String password}) {
    context.read<LoginCubit>().login(context, username, password);
  }

/*void navigatePage() {
    Navigator.push(context,
        MaterialPageRoute(builder: (context) => const RecommendScreen()));
  }*/
  Widget _getUsernameField() {
    return _getFormField(
        controller: _usernameController,
        hintText: "Username",
        textColor: textColor,
        hintColor: hintColor,
        iconColor: iconColor,
        icon: Icons.person);
  }

  Widget _getPasswordField() {
    return _getFormField(
      controller: _passwordController,
      hintText: "Password",
      textColor: textColor,
      hintColor: hintColor,
      iconColor: iconColor,
      icon: Icons.lock,
      obscureText: true,
    );
  }
}

class _getFormField extends StatelessWidget {
  const _getFormField(
      {required TextEditingController controller,
      required String hintText,
      required this.textColor,
      required this.hintColor,
      required this.iconColor,
      required IconData icon,
      bool obscureText = false})
      : _controller = controller,
        _hintText = hintText,
        _icon = icon,
        _obscureText = obscureText;

  final TextEditingController _controller;
  final Color textColor;
  final Color hintColor;
  final String _hintText;
  final Color iconColor;
  final IconData _icon;
  final bool _obscureText;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: _controller,
      obscureText: _obscureText,
      style: TextStyle(fontSize: 20, color: textColor),
      decoration: InputDecoration(
        // labelText: _hintText,
        // alignLabelWithHint: true,
        hintText: _hintText,
        hintStyle: const TextStyle(fontWeight: FontWeight.bold),
        labelStyle: TextStyle(
            fontSize: 20, color: hintColor, fontWeight: FontWeight.bold),
        filled: true,
        fillColor: ProductColor.white,
        border: const OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(20))),
        // hintText: "Username",
        prefixIcon: Icon(
          _icon,
          color: iconColor,
        ),
      ),
    );
  }
}
