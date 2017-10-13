package tn.esprit.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Need;

@Remote
public interface NeedService {
	
	public void addNeed(Need n);
	public void deleteNeed(Need n);
	public void updateNeed(Need n);
	public Need  findNeedById(int id);
	public List<Need> listAll();
	public List<Need> getNeedByType(String n);

}
