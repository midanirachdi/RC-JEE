package tn.esprit.test;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.*;
import tn.esprit.interfaces.RefugeeInterfaceRemote;

public class RefugeeServiceTest {

	public static void main(String[] args) {
		try {
			System.out.println("yess !!!");
			Context ctx = new InitialContext();
			
			Object proxy2= ctx.lookup("refugeesCamp-ear/refugeesCamp-ejb/Test!tn.esprit.services.TestRefugeeRemote");
//			RefugeeInterfaceRemote proxy = (RefugeeInterfaceRemote) ctx.lookup(
//					"/refugeesCamp-ear/refugeesCamp-ejb/RefugeeService!tn.esprit.interfaces.RefugeeInterfaceRemote");
			// ((TestRefugeeRemote) proxy2).methode();
			System.out.println("yess !!!");
			Camp c = new Camp();
			Refugee r = new Refugee();
			r.setFirstname("zz");
			r.setNationality("US");
			r.setRcamp(null);
			r.setLastName("rr");
			r.setDateOfBirth(null);
			r.setSex("f");
			// ((TestRefugeeRemote) proxy2).add(r);
//			// List<Refugee> l = proxy.findAll();
//			// System.out.println(l);
	
//			// System.out.println(proxy.findById(1));
//			System.out.println("-----------------------");
//			// Refugee r3 = proxy.findById(1);
//			//r3.setSex("f");
			// proxy.update(r3);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			
		}

	}

}
