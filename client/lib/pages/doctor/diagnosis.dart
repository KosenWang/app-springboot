import 'dart:convert';

import 'package:client/pages/doctor/doctor.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class CreateDiagnosis extends StatefulWidget {
  @override
  _CreateDiagnosisState createState() => _CreateDiagnosisState();
}

class _CreateDiagnosisState extends State<CreateDiagnosis> {
  TextEditingController _pId = TextEditingController();
  TextEditingController _dId = TextEditingController();
  TextEditingController _diagnosis = TextEditingController();

  Future send() async {
    var url = Uri.parse("http://localhost:8080/diagnosis/createDiagnosis");
    var req_body = json
        .encode({'patient_id': _pId.text, 'doctor_id': _dId.text, 'diagnosis': _diagnosis.text});
    var res = await http.post(url,
        headers: {'Content-Type': 'application/json'}, body: req_body);

    if (res.statusCode == 201) {
      showDialog(
        context: context,
        builder: (context) {
          return AlertDialog(
            content: Text("Diagnosis created!"),
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
        title: Text("Diagnosis"),
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
              decoration: InputDecoration(hintText: "doctor_id"),
              controller: _dId,
            ),
            SizedBox(
              height: 20,
            ),
            TextField(
              decoration: InputDecoration(hintText: "diagnosis"),
              controller: _diagnosis,
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
