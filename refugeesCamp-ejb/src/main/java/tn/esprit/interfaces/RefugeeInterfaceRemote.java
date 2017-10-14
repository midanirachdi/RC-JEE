package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Refugee;

@Remote
public interface RefugeeInterfaceRemote {
	void add(Refugee refugee);

	void update(Refugee refugee);
	
	void delete(Refugee refugee);

	List<Refugee> findAll();

	Refugee findById(int id);

	List<Refugee> findByName(String name);
}
