package tn.esprit.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Task;

@Remote
public interface TaskRemoteInterface {
	
	boolean add(Task joboffer);



}
