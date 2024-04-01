import 'package:flutter/material.dart';
import 'package:flutter_book_app/pages/MyReadBookScreen.dart';
import 'package:flutter_book_app/pages/profile/ProfilScreen.dart';
import 'package:flutter_book_app/pages/RecommendScreen.dart';
import 'package:flutter_book_app/util/ProductColor.dart';
import 'package:flutter_book_app/util/SafeLogoutDrawerItem.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int currentIndex = 0;
  final titles = ["My Books", "Recommends", "Profile"];
  final colors = [ProductColor.green, ProductColor.blue, ProductColor.pink];

  final screens = [MyReadBookScreen(), RecommendScreen(), ProfilScreen()];
  final backgroundColor = Colors.blue;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: colors[currentIndex],
        foregroundColor: Colors.white,
        //Theme.of(context).colorScheme.inversePrimary,
        title: Text(titles[currentIndex]),
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
        items: [
          BottomNavigationBarItem(
              icon: const Icon(
                Icons.my_library_books,
              ),
              label: titles[0],
              backgroundColor: colors[0]),
          BottomNavigationBarItem(
              icon: const Icon(Icons.recommend),
              label: titles[1],
              backgroundColor: colors[1]),
          BottomNavigationBarItem(
              icon: const Icon(Icons.person),
              label: titles[2],
              backgroundColor: colors[2]),
          const BottomNavigationBarItem(
              icon: Icon(Icons.exit_to_app), label: "Exit"),
        ],
      ),
    );
  }
}

void _logout(BuildContext context) {
  SafeLogOut.clearSharedPref();
  Navigator.of(context).popUntil((route) => route.isFirst);
}
