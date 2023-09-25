import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

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
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
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
            SizedBox(
              width: 150,
              height: 40,
              child: ElevatedButton(
                  onPressed: () {},
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
    /*
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          TextFormField(
            controller: _usernameController,
            decoration: InputDecoration(
              labelText: 'Kullanıcı Adı',
              prefixIcon: Icon(Icons.person), // Kullanıcı adı ikonu
            ),
          ),
          TextFormField(
            controller: _passwordController,
            obscureText: true,
            decoration: InputDecoration(
              labelText: 'Şifre',
              prefixIcon: Icon(Icons.lock), // Şifre ikonu
            ),
          ),
          SizedBox(height: 20),
          ElevatedButton(
            onPressed: () {
              // Burada kullanıcı girişi doğrulama ve yönlendirme kodlarını ekleyebilirsiniz.
              // Örnek: Navigator.pushReplacementNamed(context, '/ana_ekran');
            },
            child: Text('Giriş Yap'),
          ),
        ],
      ),
    );
  */
  }
}
