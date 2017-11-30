package tn.esprit.test;

import java.util.Date;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.Evenement;
import tn.esprit.interfaces.EvenementRemoteInterface;

public class CampServiceTest {
	public static void main(String []args) throws NamingException{
			Context ctx = new InitialContext();
			EvenementRemoteInterface proxy=(EvenementRemoteInterface)ctx
					.lookup("refugeesCamp-ear/refugeesCamp-ejb/EvenementService!tn.esprit.services.EvenementService");
			Evenement e=new Evenement();
			e.setDateEvent(new Date());
			e.setName("EventX");
			e.setLocation("TN");
			e.setNbplace(4);
			proxy.add(e);//			System.out.println(proxy.findAll());
//			Camp found =proxy.findById(6);
//			System.out.println(found);
//			proxy.delete(found);
//			System.out.println("Success");
	}
}
