import 'package:client/pages/doctor/diagnosis.dart';
import 'package:client/pages/doctor/feedback.dart';
import 'package:client/pages/doctor/login.dart';
import 'package:flutter/material.dart';

class DoctorHomePage extends StatefulWidget {
  @override
  _DoctorHomePageState createState() => _DoctorHomePageState();
}

class _DoctorHomePageState extends State<DoctorHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Doctor"), centerTitle: true),
      body: Center(
          child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          button("Login", DoctorLogin()),
          SizedBox(
            height: 20,
          ),
          button("Diagnosis", CreateDiagnosis()),
          SizedBox(
            height: 20,
          ),
          button("Feedback", GetFeedback())
        ],
      )),
    );
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
