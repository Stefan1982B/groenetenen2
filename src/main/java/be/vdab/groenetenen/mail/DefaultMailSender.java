package be.vdab.groenetenen.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.exceptions.KanMailNietZendenException;

@Component
class DefaultMailSender implements MailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMailSender.class);
	private final JavaMailSender sender;
	private final String emailAdresWebMaster;

	DefaultMailSender(JavaMailSender sender, String emailAdresWebMaster) {
		this.sender = sender;
		this.emailAdresWebMaster = emailAdresWebMaster;
	}

	@Override
	public void nieuweOfferte(Offerte offerte) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(offerte.getEmailAdres());
			message.setSubject("Nieuwe offerte");
			message.setText("Uw offerte heeft het nummer " + offerte.getId());
			sender.send(message);
		} catch (MailException ex) {
			LOGGER.error("Kan mail nieuwe offerte niet versturen", ex);
			throw new KanMailNietZendenException();
		}

	}

	@Override
	public void aantalOffertesMail(long aantal) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(emailAdresWebMaster);
			helper.setSubject("Aantal offertes");
			helper.setText("Aantal offertes:<strong>" + aantal + "</strong>", true);
			sender.send(message);
		} catch (MessagingException | MailException ex) {
			LOGGER.error("Kan mail aantal offertes niet versturen", ex);
			throw new KanMailNietZendenException();
		}
	}

}
