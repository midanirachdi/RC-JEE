package tn.esprit.authorization;

import java.util.HashMap;

import javax.ws.rs.HeaderParam;

import tn.esprit.entities.User;

public interface Iauth {
	
	
	public User authentify(String crd);

}
