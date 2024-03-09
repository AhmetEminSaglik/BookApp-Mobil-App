import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_book_app/pages/MyReadBookScreen.dart';
import 'package:flutter_book_app/pages/ProfilScreen.dart';
import 'package:flutter_book_app/pages/RecommendScreen.dart';
import 'package:flutter_book_app/util/SafeLogoutDrawerItem.dart';

class HomeScreen extends StatefulWidget {
  HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int currentIndex = 1;
  final screens = [
    const MyReadBookScreen(),
    const RecommendScreen(),
    const ProfilScreen()
  ];
  final backgroundColor = Colors.blue;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: backgroundColor,
        //Theme.of(context).colorScheme.inversePrimary,
        title: const Text("Home Screen"),
      ),
      body: screens[currentIndex],
      backgroundColor: Colors.red,
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: currentIndex,
        onTap: (index) => setState(() {
          if (index == screens.length) {
            _logout(context);
          } else {
            currentIndex = index;
          }
        }),
        backgroundColor: Colors.blue,
        items: const [
          BottomNavigationBarItem(
              icon: Icon(
                Icons.my_library_books,
              ),
              label: "My Books",
              backgroundColor: Colors.green),
          BottomNavigationBarItem(
              icon: Icon(Icons.recommend),
              label: "Recommends",
              backgroundColor: Colors.orange),
          BottomNavigationBarItem(
              icon: Icon(Icons.person),
              label: "Profil",
              backgroundColor: Colors.red),
          BottomNavigationBarItem(icon: Icon(Icons.exit_to_app), label: "Exit"),
        ],
      ),
    );
  }
}

void _logout(BuildContext context) {
  SafeLogOut.clearSharedPref();
  Navigator.of(context).popUntil((route) => route.isFirst);
}
