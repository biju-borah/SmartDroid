import 'package:flutter/cupertino.dart';

import 'package:flutter/material.dart';
import 'package:flip_card/flip_card.dart';

class Events extends StatelessWidget {
  var event = [
    {
      "title": "ESPERANZA",
      "content":
          "The event that excites every first-year guy and girl, which not only build their confidence but also add creativity to their levels. The main aim of this party is to give a warm welcome to the new comers.The main aim of this party is to give a warm welcome to the new comers. It is accompanied with so many colourful events and programs like ramp walk, traditional, fusion, and western dances, exhilarating singing performances, and splendid decoration, thus making it a soulful evening.",
      "picture": "assets/1.jpeg"
    },
    {
      "title": "ENIGMA",
      "content":
          "Computer Science Society organizes coding contests for first year students. This contest being open to all enhances a good competitive environment for the students. The questions are made such that even students will little or basic Knowledge of programming can surely attempt the questions. Learning programming is now an essential skill for various placement interviews and our society encourages more and more participation in such activities",
      "picture": "assets/2.jpeg"
    },
    {
      "title": "ABACUS",
      "content":
          "The annual Computer Science and Engineering week - ABACUS! The excitement begins each year, for the Annual week of the CSE department in which different games and competitions takes place where everyone gets a cool opportunity to prove their skills down and claim the prize money.But the most unique point about it is that it gives you, the freshers, the opportunity to become organizers, to present your ideas, instead of just participating and enables you to learn numerous things.",
      "picture": "assets/3.jpeg"
    },
    {
      "title": "BITSCRIBE",
      "content":
          "Every year, the Computer Science society excels in doing something new and innovative. It doesn’t remain dormant. The newsletter ‘bitscribe’ is a recent gift for the students of NIT Silchar from the society.It consists of articles by alumni as well as the current students of CSE which are full of motivation and knowledge. The newsletter requires a lot of effort and unity which shows the potential and efforts of the society.Whatever the society does, it never compromises with quality!",
      "picture": "assets/4.jpeg"
    },
    {
      "title": "LIT WEEK",
      "content":
          "The annual Computer Science and Engineering week - Abacus, is right around the corner.” Yeah, this is how the excitement begins each year, for the Annual week of the CSE dept. in which different games and competitions takes place where everyone gets a cool opportunity to prove their skills down and claim the prize money.But the most unique point about it is that it gives you, the freshers, the opportunity to become organizers, to present your ideas, instead of just participating and enables you to learn numerous things.",
      "picture": "assets/5.jpeg"
    },
  ];
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
          children: event.map((entry) {
        return FlipCard(
          fill: Fill.fillBack,
          direction: FlipDirection.HORIZONTAL,
          front: Container(
            margin: EdgeInsets.all(10),
            padding: EdgeInsets.all(10),
            width: double.infinity,
            height: 200,
            child: Card(
              color: Color.fromRGBO(182, 60, 162, 0.6),
              child: Stack(
                children: [
                  Center(
                    child: Container(
                      width: double.infinity,
                      child: Image.asset(
                        "assets/horizontal_logo.jpg",
                        fit: BoxFit.fitWidth,
                      ),
                    ),
                  ),
                  Container(
                    width: double.infinity,
                    child: Padding(
                      padding: const EdgeInsets.symmetric(vertical: 60),
                      child: Text(
                        '${entry["title"]}',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
          back: Container(
            margin: EdgeInsets.all(10),
            padding: EdgeInsets.all(10),
            width: double.infinity,
            height: 200,
            child: Card(
              color: Colors.black,
              child: Stack(
                children: [
                  Image.asset(
                    "${entry["picture"]}",
                    fit: BoxFit.fill,
                  ),
                  Padding(
                    padding: const EdgeInsets.all(15.0),
                    child: Text(
                      '${entry["content"]}',
                      textAlign: TextAlign.justify,
                      style: TextStyle(
                        fontSize: 15,
                        color: Colors.white,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      }).toList()),
    );
  }
}
