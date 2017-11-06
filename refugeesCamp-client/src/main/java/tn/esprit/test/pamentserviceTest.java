package tn.esprit.test;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.Donation;
import tn.esprit.interfaces.DonationRemoteInterface;
import tn.esprit.services.PaypalService;

public class pamentserviceTest {
	public static void main(String []args){
		try {
			Context ctx=new InitialContext();
			DonationRemoteInterface proxy=(DonationRemoteInterface)ctx.lookup("refugeesCamp-ear/refugeesCamp-ejb/DonationService!tn.esprit.interfaces.DonationRemoteInterface");
//			proxy.add(new Donation("adadaz",45,new Date(),"USD"));
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PaypalService ps=new PaypalService();
		System.out.println(ps.generatePayment(3, "USD"));
//		ps.confirmPayment("DHEKXMRTRHDEE", "PAY-0DK41110S9215500KLHVURMI");
	}
}

