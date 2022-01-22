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