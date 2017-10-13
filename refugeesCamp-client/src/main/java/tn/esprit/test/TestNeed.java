package tn.esprit.test;

//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.Need;
import tn.esprit.interfaces.NeedService;
import tn.esprit.services.NeedImpl;

public class TestNeed {
	
	public static void main(String[] args) throws NamingException {
		
		InitialContext ctx = new InitialContext();
		NeedService proxy1=(NeedService)ctx.lookup("refugeesCamp-ear/refugeesCamp-ejb/NeedImpl!tn.esprit.interfaces.NeedService");
	
		Need projet=new Need();
		
		projet.setType("habit");
		projet.setDescription("manquant");
		

	    proxy1.addNeed(projet);
	    System.out.println("l'ajout a été effectué lhmd");

	
	}

}
