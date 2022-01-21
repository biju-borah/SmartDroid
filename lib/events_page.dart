import 'package:flutter/material.dart';
import './drawer.dart';
import 'package:lottie/lottie.dart';

class EventsPage extends StatefulWidget {
  const EventsPage({Key? key}) : super(key: key);

  @override
  _EventsPageState createState() => _EventsPageState();
}

class _EventsPageState extends State<EventsPage> {
  @override
  Widget build(BuildContext context) {
    double myWidth = (MediaQuery.of(context).size.width).toDouble();
    return Scaffold(
      drawer: MyDrawer(),
      backgroundColor: Colors.black,
      appBar: AppBar(
        title: Text("CSS EVENTS"),
      ),
      body: Stack(
        children: [
          Container(
            alignment: Alignment.topRight,
            child: Lottie.asset('assets/accounting.json'),
            height: 200,
            width: double.infinity,
          ),
          Column(
            children: [
              Container(
                margin: EdgeInsets.all(20),
                padding: EdgeInsets.all(5),
                alignment: Alignment.topCenter,
                child: Container(
                  decoration: BoxDecoration(
                      color: Colors.grey,
                      border: Border.all(width: 3, color: Colors.white)),
                  child: Center(
                    child: Text(
                      "CSS",
                      style: TextStyle(
                        fontSize: 30,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  width: 100,
                  height: 50,
                ),
              ),
              Container(
                alignment: Alignment.topCenter,
                child: Container(
                  decoration: BoxDecoration(
                      color: Colors.grey,
                      border: Border.all(width: 3, color: Colors.white)),
                  child: Center(
                    child: Text(
                      "EVENTS",
                      style: TextStyle(
                        fontSize: 30,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ),
                  width: 200,
                  height: 50,
                ),
              ),
            ],
          ),
          Container(
            child: Lottie.asset('assets/accounting.json', width: 200),
          ),
          // Lottie.asset('assets/techno.json'),
        ],
      ),
    );
  }
}
