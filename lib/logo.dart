import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:lottie/lottie.dart';
import './events.dart';

class Logo extends StatelessWidget {
  var appbar;
  Logo(this.appbar);

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: SizedBox(
        height: (MediaQuery.of(context).orientation == Orientation.landscape
            ? MediaQuery.of(context).size.width -
                MediaQuery.of(context).padding.top
            : MediaQuery.of(context).size.height -
                MediaQuery.of(context).padding.top -
                appbar.preferredSize.height),
        child: Stack(
          children: [
            Column(
              children: [
                Container(
                  child: Lottie.asset('assets/loader.json'),
                ),
                MediaQuery.of(context).orientation == Orientation.portrait
                    ? Container(
                        child: Lottie.asset('assets/loader.json'),
                      )
                    : Container(),
              ],
            ),
            SizedBox(
              width: double.infinity,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  SizedBox(
                    height: 70,
                    width: 200,
                    child: Card(
                      color: Colors.black,
                      child: Padding(
                        padding: const EdgeInsets.all(10.0),
                        child: Text(
                          "Computer",
                          textAlign: TextAlign.left,
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: 33.98,
                              fontWeight: FontWeight.w900),
                        ),
                      ),
                    ),
                  ),
                  SizedBox(
                      height: 70,
                      width: 180,
                      child: Card(
                          color: Colors.pink,
                          child: Padding(
                            padding: const EdgeInsets.all(10.0),
                            child: Text(
                              "Science",
                              textAlign: TextAlign.center,
                              style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 33.98,
                                  fontWeight: FontWeight.w900),
                            ),
                          ))),
                  SizedBox(
                      height: 70,
                      width: 180,
                      // margin: EdgeInsets.all(5),
                      child: Card(
                        color: Colors.black,
                        child: Padding(
                          padding: const EdgeInsets.all(10.0),
                          child: Text(
                            "Society",
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 33.98,
                              fontWeight: FontWeight.w900,
                            ),
                          ),
                        ),
                      )),
                  SizedBox(
                    height: 50,
                  ),
                  Text(
                    "Department of Computer Science and Engineering",
                    style: TextStyle(color: Colors.white),
                  ),
                  Text(
                    "National Institute of Technology, Silchar",
                    style: TextStyle(color: Colors.white),
                  ),
                  SizedBox(
                    height: 200,
                  ),
                  Text(
                    "ABOUT US",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 40,
                        fontWeight: FontWeight.bold),
                  ),
                  SizedBox(
                    height: 50,
                  ),
                  Container(
                    width: 300,
                    child: Text(
                      "Computer Science and Engineering is probably the coolest branch in NIT Silchar with the highest number of placements and talented minds.",
                      style: TextStyle(color: Colors.white),
                      textAlign: TextAlign.justify,
                    ),
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
