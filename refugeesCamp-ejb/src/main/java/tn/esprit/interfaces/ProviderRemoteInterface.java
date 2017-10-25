package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Provider;

/*
* author: Salim Ben Hassine
*/
@Remote
public interface ProviderRemoteInterface {
	boolean add(Provider provider);

	boolean update(Provider provider);

	List<Provider> findAll();

	Provider findById(int id);

	boolean delete(Provider provider);
}

