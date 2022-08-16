package swp.charite.diagnosis.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import swp.charite.diagnosis.model.Guidance;
import swp.charite.diagnosis.model.Patient;



@Component
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendEmail(Patient patient, Guidance guidance) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Emergency Department" + '<' + from + '>');
        message.setTo(patient.getEmail());
        message.setSubject("Guidance to " + patient.getFirstname() + " " + patient.getSurname());
        message.setText("Patient: " + patient.getFirstname() + " " + patient.getSurname() + "\n" +
                        "Diagnosis: " + guidance.getDiagnosisId() + "\n" + 
                        "Guidance: " + guidance.getGuidance() + "\n" + 
                        "Priority: " + guidance.getPriority() + "\n" + 
                        "Done: " + guidance.getDone());
        try {
            mailSender.send(message);
            System.out.println("Send email successfully!");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
}
