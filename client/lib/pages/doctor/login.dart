import 'dart:convert';

import 'package:client/pages/doctor/diagnosis.dart';
import 'package:client/pages/doctor/doctor.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class DoctorLogin extends StatefulWidget {
  @override
  _DoctorLoginState createState() => _DoctorLoginState();
}

class _DoctorLoginState extends State<DoctorLogin> {
  TextEditingController _firstname = TextEditingController();
  TextEditingController _lastname = TextEditingController();
  Doctor patient = Doctor("", "");

  Future login() async {
    var url = Uri.parse("http://localhost:8080/doctor/createDoctor");
    var req_body = json
        .encode({'firstname': patient.firstname, 'surname': patient.lastname});
    var res = await http.post(url,
        headers: {'Content-Type': 'application/json; charset=UTF-8'}, body: req_body);

    if (res.statusCode == 201) {
      Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => CreateDiagnosis(),
          ));
    } else {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("No doctor exists, please sign up first!"),
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
