import 'dart:convert';

import 'package:client/pages/patient/information.dart';
import 'package:flutter/material.dart';
import 'package:client/pages/patient/patient.dart';
import 'package:http/http.dart' as http;

class PatientLogin extends StatefulWidget {
  @override
  _PatientLoginState createState() => _PatientLoginState();
}

class _PatientLoginState extends State<PatientLogin> {
  TextEditingController _firstname = TextEditingController();
  TextEditingController _lastname = TextEditingController();
  Patient patient = Patient("", "");

  Future login() async {
    var url = Uri.parse("http://localhost:8080/patient/createPatient");
    var req_body = json
        .encode({'firstname': patient.firstname, 'surname': patient.lastname});
    var res = await http.post(url,
        headers: {'Content-Type': 'application/json'}, body: req_body);

    if (res.statusCode == 201) {
      Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => PatientInformation(),
          ));
    } else {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("No patient exists, please sign up first!"),
          );
        },
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Login"),
          centerTitle: true,
        ),
        body: Center(
          child: Column(children: <Widget>[
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
            ElevatedButton(
              child: Text("submit"),
              onPressed: () {
                patient.firstname = _firstname.text;
                patient.lastname = _lastname.text;
                login();
              },
            ),
          ]),
        ));
  }
}
