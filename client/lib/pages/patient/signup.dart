import 'dart:convert';

import 'package:client/pages/patient/patient.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class PatientSignup extends StatefulWidget {
  @override
  _PatientSignupState createState() => _PatientSignupState();
}

class _PatientSignupState extends State<PatientSignup> {
  TextEditingController _firstname = TextEditingController();
  TextEditingController _lastname = TextEditingController();
  Patient patient = Patient("", "");

  Future signup() async {
    var url = Uri.parse("http://localhost:8080/patient/createPatient");
    var req_body = json
        .encode({'firstname': patient.firstname, 'surname': patient.lastname});
    var res = await http.post(url,
        headers: {'Content-Type': 'application/json'}, body: req_body);

    if (res.statusCode == 201) {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("Sign up successfully"),
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
        title: Text("Register"),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            TextField(
              decoration: InputDecoration(hintText: "First Name"),
              controller: _firstname,
            ),
            SizedBox(
              height: 20,
            ),
            TextField(
              decoration: InputDecoration(hintText: "Last Name"),
              controller: _lastname,
            ),
            SizedBox(
              height: 40,
            ),
            ElevatedButton(
              child: Text("Submit"),
              onPressed: () {
                patient.firstname = _firstname.text;
                patient.lastname = _lastname.text;
                signup();
              },
            ),
          ],
        ),
      ),
    );
  }
}
