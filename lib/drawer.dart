import 'package:flutter/material.dart';

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
            onTap: () {},
          ),
          ListTile(
            title: const Text('EVENTS'),
            onTap: () {},
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