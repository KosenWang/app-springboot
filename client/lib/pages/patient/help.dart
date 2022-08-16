import 'package:flutter/material.dart';

class PatientHelp extends StatefulWidget {
  @override
  _PatientHelpState createState() => _PatientHelpState();
}

class _PatientHelpState extends State<PatientHelp> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Help"),
        centerTitle: true,
      ),
      body: Center(
        child: Text("Help for patient"),
      ),
    );
  }
}
