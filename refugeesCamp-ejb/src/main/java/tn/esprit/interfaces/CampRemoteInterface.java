package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Camp;

@Remote
public interface CampRemoteInterface {
	
	void add(Camp c);

	void update(Camp c);

	List<Camp> findAll();

	Camp findById(int id);

	void delete(Camp c);

}
