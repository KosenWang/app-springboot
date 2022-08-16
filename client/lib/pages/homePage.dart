import 'package:client/pages/doctor/homepage.dart';
import 'package:client/pages/patient/homepage.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("HomePage"), centerTitle: true),
        body: Center(
            child: Column(mainAxisSize: MainAxisSize.min, children: <Widget>[
          button("Patient", PatientHomePage()),
          SizedBox(
            height: 20,
          ),
          button("Doctor", DoctorHomePage()),
        ])));
  }

  Widget button(String text, Widget widget) {
    return ElevatedButton(
      onPressed: () {
        Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => widget),
        );
      },
      child: Text(text),
    );
  }
}
