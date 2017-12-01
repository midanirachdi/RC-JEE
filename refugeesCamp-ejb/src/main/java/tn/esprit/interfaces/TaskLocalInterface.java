package tn.esprit.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Task;

@Local
public interface TaskLocalInterface {

	boolean add(Task joboffer);

	

}
