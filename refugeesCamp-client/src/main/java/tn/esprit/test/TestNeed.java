package tn.esprit.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.Need;
import tn.esprit.entities.Stock;
import tn.esprit.interfaces.NeedService;
import tn.esprit.services.NeedImpl;

public class TestNeed {
	
	public static void main(String[] args) throws NamingException {
		
		InitialContext ctx = new InitialContext();
		NeedService proxy1=(NeedService)ctx.lookup("refugeesCamp-ear/refugeesCamp-ejb/NeedImpl!tn.esprit.interfaces.NeedService");
	//////// add/////////////
		Need projet=new Need();
		Date date=new Date();
		projet.setDate(date);
		projet.setType(Stock.stockNeedsEnum.Covers);
		projet.setQuantity(3);
		projet.setDescription("na9es");
		
	    proxy1.addNeed(projet);
	    System.out.println("l'ajout a été effectué lhmd");
	    ///////////// liste
//	    System.out.println("************************affichage**************");
//	    System.out.println(proxy1.listAll());
	  	/*List<Need> list1=new ArrayList<>();
	  	list1=proxy1.listAll();
	  	for(Need projet1:list1)
		{
			System.out.println(projet1.getType());
		}*/
	   /* System.out.println("************************suppresion**************");
	    Need n= (Need)proxy1.findNeedById(2);
		System.out.println(n.getType());
	    proxy1.deleteNeed(n);
		System.out.println("succes");*/
	}
}
