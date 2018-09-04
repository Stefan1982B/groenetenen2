package be.vdab.groenetenen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.groenetenen.repositories.FiliaalRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultFiliaalService implements FiliaalService {

	private final FiliaalRepository filiaalRepository;

	DefaultFiliaalService(FiliaalRepository filiaalRepository) {
		this.filiaalRepository = filiaalRepository;
	}

	@Override
	public List<Filiaal> findByAdresPostcode(int van, int tot) {
		return filiaalRepository.findByAdresPostcodeBetweenOrderByAdresPostcode(van, tot);
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void delete(long id) {
		Optional<Filiaal>filiaal = filiaalRepository.findById(id);
		if(filiaal.isPresent()) {
			if(! filiaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
			filiaalRepository.deleteById(id);
		}
		
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
		
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);
		
	}
	
	

}
