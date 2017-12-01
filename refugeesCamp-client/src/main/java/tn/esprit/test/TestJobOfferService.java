package tn.esprit.test;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.entities.CampChef;
import tn.esprit.entities.DistrictChef;
import tn.esprit.entities.Refugee;
import tn.esprit.interfaces.RefugeeInterfaceRemote;

public class TestJobOfferService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Context ctx = new InitialContext();
			// taken from console JNDI
//			JobOfferRemoteInterface proxy = (JobOfferRemoteInterface) ctx.lookup(
//					"/refugeesCamp-ear/refugeesCamp-ejb/JobOfferImpl!tn.esprit.interfaces.JobOfferRemoteInterface");
			RefugeeInterfaceRemote proxy= (RefugeeInterfaceRemote) ctx.lookup
					("/refugeesCamp-ear/refugeesCamp-ejb/RefugeeService!tn.esprit.interfaces.RefugeeInterfaceRemote");
			

			 
			
			// System.out.println(proxy.findAll());
			// -------------
			// JobOffer joboffer= proxy.findById(1);
			// proxy.delete(joboffer);
			// ---------------
			// JobOffer joboffer = proxy.findById(2);
			// joboffer.setDescription("test update22222");
			// proxy.update(joboffer);
			// -----------------
			
			 DistrictChef dc = new DistrictChef();
			 CampChef cc = new CampChef();
			 dc.setFirstName("dchief1");
			 cc.setFirstName("cchief1");
			
			 
//			 Camp rcamp = new Camp();
//			 Refugee r = new Refugee( "Midani", 
//					 "Rachdi", 
//					 "M", 
//					 new Date(), 
//					 "Tunisian", 
//					 rcamp,   
//					 12,
//					 "IT");		
//			 r.setEnglishlanguageLevel(LanguageLevelEnum.Excellent);
//			 r.setFrenchlanguageLevel(LanguageLevelEnum.Low);
//			 r.setHighestDegree(HighestDegreeEnum.Licence);
//			 
//			 proxy.add(r);
//			 JobOffer jo = new JobOffer("1st job offer", new Date() ,new
//			 Date(),12345,dc,cc);
//			 JobOffer jo1 = new JobOffer("2nd job offer", new Date() ,new
//					 Date(),12345,dc,cc);
//			 JobOffer jo2 = new JobOffer("3rd job offer", new Date() ,new
//					 Date(),12345,null,cc);
//			 System.out.println(jo);
//			 proxy.add(jo);
//			 proxy.add(jo1);
//			 proxy.add(jo2);
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
