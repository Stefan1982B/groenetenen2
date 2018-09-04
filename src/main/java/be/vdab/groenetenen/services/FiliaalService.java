package be.vdab.groenetenen.services;

import java.util.List;

import be.vdab.groenetenen.entities.Filiaal;

public interface FiliaalService {
	List<Filiaal>findByAdresPostcode(int van, int tot);
	List<Filiaal>findAll();
	void delete(long id); 
	void create(Filiaal filiaal);
	void update(Filiaal filiaal);
}
