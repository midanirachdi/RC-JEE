package tn.esprit.test;

import java.util.Date;

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
//			proxy.add(new Camp("campNow",true,"TN",500,new Date()));
//			proxy.add(new Camp("campY",false,"FR",100,new Date()));
//			proxy.add(new Camp("campX",true,"AN",200,new Date()));
//			System.out.println(proxy.findAll());
//			Camp found =proxy.findById(6);
//			System.out.println(found);
//			proxy.delete(found);
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
