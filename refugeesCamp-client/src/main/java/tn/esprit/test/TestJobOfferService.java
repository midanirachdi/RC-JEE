package tn.esprit.test;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.JobOffer;
import tn.esprit.interfaces.JobOfferRemoteInterface;
import tn.esprit.services.JobOfferImpl;

public class TestJobOfferService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Context ctx = new InitialContext();
			// taken from console JNDI
			JobOfferRemoteInterface proxy = (JobOfferRemoteInterface) ctx.lookup(
					"/refugeesCamp-ear/refugeesCamp-ejb/JobOfferImpl!tn.esprit.interfaces.JobOfferRemoteInterface");
			// System.out.println(proxy.findAll());
			// -------------
			// JobOffer joboffer= proxy.findById(2);
			// proxy.delete(joboffer);
			// ---------------
			// JobOffer joboffer = proxy.findById(1);
			// joboffer.setDescription("test update");
			// proxy.update(joboffer);
			//-----------------
			JobOffer jo = new JobOffer("test description", new Date() ,new Date(),12345);
			proxy.add(jo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
