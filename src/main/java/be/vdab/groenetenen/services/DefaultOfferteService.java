package be.vdab.groenetenen.services;

import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.mail.MailSender;
import be.vdab.groenetenen.repositories.OfferteRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultOfferteService implements OfferteService {
	
	private final MailSender mailSender;
	private final OfferteRepository repository;
	DefaultOfferteService(OfferteRepository repository, MailSender mailSender){
		this.repository = repository;
		this.mailSender = mailSender;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Offerte offerte) {
		repository.save(offerte);
		mailSender.nieuweOfferte(offerte);
		
	}

	@Override
	public Optional<Offerte> read(long id) {
		return repository.findById(id);
	}

	@Override 
	@Scheduled(fixedRate=60000)  
	public void aantalOffertesMail() {   
		mailSender.aantalOffertesMail(repository.count()); } 

}
