package swp.charite.diagnosis.util;

import swp.charite.diagnosis.model.Guidance;
import swp.charite.diagnosis.model.Patient;

public interface MailService {
    public void sendEmail(Patient patient, Guidance guidance);
}
