package tn.esprit.test;

import javax.ejb.EJBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.Camp;
import tn.esprit.interfaces.CampRemoteInterface;

public class CampServiceTest {
	public static void main(String []args){
		try {
			Context ctx = new InitialContext();
			CampRemoteInterface proxy=(CampRemoteInterface)ctx.lookup("/refugeesCamp-ear/refugeesCamp-ejb/CampService!tn.esprit.interfaces.CampRemoteInterface");
//			proxy.addCamp(new Camp("campNow",true,"TN",500));
//			proxy.addCamp(new Camp("campY",false,"FR",100));
//			proxy.addCamp(new Camp("campX",true,"AN",200));
//			System.out.println(proxy.findAll());
//			Camp found =proxy.findCamp(1);
//			proxy.deleteCamp(found);
//			System.out.println("Success");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			System.out.println("Naming JNDI Problem");
		}catch (EJBException e) {
			// TODO Auto-generated catch block
			System.out.println("EJB Problem");
		}
	}
}
