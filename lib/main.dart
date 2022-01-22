import './events.dart';

import './splash.dart';
import 'package:flutter_svg/flutter_svg.dart';
import './drawer.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import './logo.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CSS App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Splash(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
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
