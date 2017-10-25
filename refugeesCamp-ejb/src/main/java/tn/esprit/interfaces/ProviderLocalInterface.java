package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Provider;

/*
* author: Salim Ben Hassine
*/
@Local
public interface ProviderLocalInterface {
	boolean add(Provider provider);

	boolean update(Provider provider);

	List<Provider> findAll();

	Provider findById(int id);

	boolean delete(Provider provider);
}

