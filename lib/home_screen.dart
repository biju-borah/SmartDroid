import 'package:cssapp/drawer.dart';
import 'package:cssapp/events_page.dart';
import 'package:cssapp/members_screen.dart';
import 'package:flutter/material.dart';
import 'package:cssapp/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import './logo.dart';
import './events.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  PageController _pageController = PageController();
  List<Widget> _screens = [FirstPage(), EventsPage(), MembersPage(), MyDrawer()];

  int _selectedIndex = 0;
  void _onPageChanged(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  void _onItemTapped(int selectedIndex) {
    _pageController.jumpToPage(selectedIndex);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: PageView(
          controller: _pageController,
          children: _screens,
          onPageChanged: _onPageChanged,
          physics: NeverScrollableScrollPhysics(),
        ),
        bottomNavigationBar: BottomNavigationBar(
          onTap: _onItemTapped,
          type: BottomNavigationBarType.fixed,
          showSelectedLabels: false,
          showUnselectedLabels: false,
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(icon: Icon(Icons.home), label: ''),
            BottomNavigationBarItem(icon: Icon(Icons.event), label: ''),
            BottomNavigationBarItem(icon: Icon(Icons.groups), label: ''),
            BottomNavigationBarItem(icon: Icon(Icons.menu), label: ''),
          ],
        ));
  }
}


class FirstPage extends StatefulWidget {
  const FirstPage({Key? key}) : super(key: key);

  @override
  State<FirstPage> createState() => _FirstPageState();
}

class _FirstPageState extends State<FirstPage> {
  @override
  Widget build(BuildContext context) {
    final appbarsize = AppBar(
      title: Text("COMPUTER SCIENCE SOCIETY"),
    );
    return Scaffold(
      backgroundColor: Colors.black,
      appBar: appbarsize,
      drawer: MyDrawer(),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Logo(appbarsize),
            Events(),
          ],
        ),
      ),
    );
  }
}
