import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:flutter_book_app/cubit/login/LoginCubit.dart';
import 'package:flutter_book_app/enum/EnumLoginState.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:logger/logger.dart';

import '../util/ProductColor.dart';

class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: ProductColor.blue,
      body: Center(
        child: LoginForm(),
      ),
    );
  }
}

class LoginForm extends StatefulWidget {
  @override
  _LoginFormState createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  var log = Logger(printer: PrettyPrinter(colors: false));
  final TextEditingController _usernameController =
      TextEditingController(text: "user13");
  final TextEditingController _passwordController =
      TextEditingController(text: "pass");

  @override
  Widget build(BuildContext context) {
    // Login();
    autoLogin();
    return Padding(
      padding: const EdgeInsets.all(25.0),
      child: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const SizedBox(
              child: Icon(
                FontAwesomeIcons.book,
                size: 125.0, // İstediğiniz boyuta ayarlayabilirsiniz
                color: ProductColor.white, // İstediğiniz rengi seçebilirsiniz
              ),
            ),
            const SizedBox(
              height: 50,
            ),
            TextFormField(
              controller: _usernameController,
              style:
                  const TextStyle(fontSize: 20, color: ProductColor.darkBlue),
              decoration: const InputDecoration(
                labelText: "Username",
                labelStyle: TextStyle(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
                filled: true,
                fillColor: ProductColor.white,
                border: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(20))),
                // hintText: "Username",
                prefixIcon: Icon(Icons.person),
              ),
            ),
            const SizedBox(
              height: 50,
            ),
            TextFormField(
              controller: _passwordController,
              obscureText: true,
              style:
                  const TextStyle(fontSize: 20, color: ProductColor.darkBlue),
              decoration: const InputDecoration(
                labelStyle: TextStyle(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.bold),
                labelText: "Password",
                filled: true,
                fillColor: ProductColor.white,
                border: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(20))),
                // hintText: "Username",
                prefixIcon: Icon(Icons.lock),
              ),
            ),
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
                          (states) => ProductColor.darkBlue),
                      foregroundColor: MaterialStateColor.resolveWith(
                          (states) => ProductColor.white)),
                  child: const Text(
                    "Login",
                    style: TextStyle(fontSize: 23),
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
}
