import 'package:flutter/material.dart';

class PatientInformation extends StatefulWidget {
  @override
  _PatientInformationState createState() => _PatientInformationState();
}

class _PatientInformationState extends State<PatientInformation> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Personal Information"),
        centerTitle: true,
      ),
      body: Center(
        child: Text("Patient's personal information here"),
      ),
    );
  }
}
