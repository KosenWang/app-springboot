import 'dart:convert';

import 'package:client/pages/patient/patient.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class GetFeedback extends StatefulWidget {
  @override
  _GetFeedbackState createState() => _GetFeedbackState();
}

class _GetFeedbackState extends State<GetFeedback> {
  TextEditingController _dId = TextEditingController();

  Future send() async {
    var url = Uri.parse(
        "http://localhost:8080/doctor/getFeedback?doctor_id=" + _dId.text);
    var res = await http.get(url);

    if (!res.body.isEmpty) {
      var jsonValue = json.decode(res.body);
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("Feedback of patient: " + jsonValue['feedback']),
          );
        },
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Feedback"),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
            children: <Widget>[
        TextField(
        decoration: InputDecoration(hintText: "doctor_id"),
        controller: _dId,
        ),
        SizedBox(
          height: 20,
        ),
        Text("Get feedback from patient"),
        ElevatedButton(
          child: Text("Submit"),
          onPressed: () {
            send();
          },)]
      ),
    ));
  }
}
