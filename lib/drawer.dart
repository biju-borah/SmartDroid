import 'package:cssapp/main.dart';
import 'package:flutter/material.dart';
import './events_page.dart';

class MyDrawer extends StatelessWidget {
  const MyDrawer({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: [
          SizedBox(
            height: 100,
          ),
          ListTile(
            title: const Text('HOME'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const MyHomePage()),
              );
            },
          ),
          ListTile(
            title: const Text('EVENTS'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const EventsPage()),
              );
            },
          ),
          ListTile(
            title: const Text('GALLERY'),
            onTap: () {},
          ),
          ListTile(
            title: const Text('MEMBERS'),
            onTap: () {},
          ),
          ListTile(
            title: const Text('DEVELOPERS'),
            onTap: () {},
          ),
        ],
      ),
    );
  }
}
//niki