package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Camp;

@Remote
public interface CampRemoteInterface {
	public void addCamp(Camp i);
	
	public void deleteCamp(Camp i);
	
	public Camp findCamp(int id);
	
	public void updateCamp(Camp i);
	
	public List<Camp> findAll();
}
