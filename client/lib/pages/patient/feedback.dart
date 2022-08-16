import 'dart:convert';

import 'package:client/pages/patient/patient.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class PatientFeedback extends StatefulWidget {
  @override
  _PatientFeedbackState createState() => _PatientFeedbackState();
}

class _PatientFeedbackState extends State<PatientFeedback> {
  TextEditingController _pId = TextEditingController();
  TextEditingController _diaId = TextEditingController();
  TextEditingController _feedback = TextEditingController();
  Patient patient = Patient("", "");

  Future send() async {
    var url = Uri.parse("http://localhost:8080/patient/createFeedback");
    var req_body = json
        .encode({
      'patient_id': _pId.text,
      'diagnosis_id': _diaId.text,
      'feedback': _feedback.text
        });
    var res = await http.post(url,
        headers: {'Content-Type': 'application/json'}, body: req_body);

    if (res.statusCode == 201) {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("Thank you for your feedback!"),
          );
        },
      );
    } else {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("Patient exists, please log in"),
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
              decoration: InputDecoration(hintText: "patient_id"),
              controller: _pId,
            ),
            SizedBox(
              height: 20,
            ),
            TextField(
              decoration: InputDecoration(hintText: "diagnosis_id"),
              controller: _diaId,
            ),
            SizedBox(
              height: 20,
            ),
            TextField(
              decoration: InputDecoration(hintText: "feedback"),
              controller: _feedback,
            ),
            SizedBox(
              height: 40,
            ),
            ElevatedButton(
              child: Text("Submit"),
              onPressed: () {
                send();
              },
            ),
          ],
        ),
      ),
    );
  }
}
