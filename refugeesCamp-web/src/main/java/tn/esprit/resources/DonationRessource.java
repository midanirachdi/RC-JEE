package tn.esprit.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import tn.esprit.entities.Camp;
import tn.esprit.entities.Donation;
import tn.esprit.services.CampService;
import tn.esprit.services.DonationService;

@Path("/donations")
public class DonationRessource {
	@EJB
	DonationService donationservice;
	@EJB
	CampService cs;
	String clientId = "AXWaTaAVKEbzNcdqqbYCSnAxI1tNB6uiIINJphmt3DKeNHErhZdFSDHxj_6owFCld7oS-CwkeGCbcE3a";
	String clientSecret = "EC--VCYTG-icuQgIz0WchzeK2xryFzfIO0lHU5VSDJeHIl_B4XLuSce2PAd-MrkIZ4ypflkVyyNTS5Cj";
	
	public DonationRessource() {
		super();
	}
	
	@GET
	@Path("/avg")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCampAllAvg(){
		return Response.ok(donationservice.getCampAvgTotalDonation()).build();
	}
	
	@GET
	@Path("/avg/{campId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCampAvg(@PathParam("campId") Integer id){
		Camp camp = new Camp();
		camp.setId(id);
		return Response.ok(donationservice.getCampAvgTotalDonation(camp)).build();
	}
	@GET
	@Path("/add")
	public Response generateDonation(@QueryParam("total")double total,@QueryParam("currency")String currency){
		
		APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		// Set payer details
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:18080/refugeesCamp-web/api/donation/cancel");
		redirectUrls.setReturnUrl("http://localhost:18080/refugeesCamp-web/api/donation/confirm");

		// Payment amount
		Amount amount = new Amount();
		amount.setCurrency(currency);
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(total+"");

		// Transaction information
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		.setDescription("Donation for RefugeesCamp.com");

		// Add transaction to a list
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		// Add payment details
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);
		try {
			Payment createdPayment = payment.create(context);
			Donation d=new Donation(createdPayment.getId(), Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal()), new Date(), createdPayment.getTransactions().get(0).getAmount().getCurrency());
			donationservice.add(d);
			return Response.temporaryRedirect(new URI(createdPayment.getLinks().get(1).getHref())).build();
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
			return Response.serverError().build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	@GET
	@Path("/addtocamp")
	public Response generateDonationForCamp(@QueryParam("total")double total,@QueryParam("currency")String currency,@QueryParam("camp_id")int id){
		if (cs.findById(id)==null)
			return Response.status(Status.NOT_FOUND).build();
		Camp c=cs.findById(id);
		APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:18080/refugeesCamp-web/api/donation/cancel");
		redirectUrls.setReturnUrl("http://localhost:18080/refugeesCamp-web/api/donation/confirm");
		Amount amount = new Amount();
		amount.setCurrency(currency);
		amount.setTotal(total+"");
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		.setDescription("Donation for Camp :"+c.getName());
		// Add transaction to a list
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);
		try {
			Payment createdPayment = payment.create(context);
			Donation d=new Donation(createdPayment.getId(), Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal()),
					new Date(), createdPayment.getTransactions().get(0).getAmount().getCurrency()
					,c);
			donationservice.add(d);
			return Response.temporaryRedirect(new URI(createdPayment.getLinks().get(1).getHref())).build();
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
			return Response.serverError().build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public  Response listall(){
		List<Donation> ls= donationservice.findAll();
		if (!ls.isEmpty())
			return Response.status(Status.FOUND).entity(ls).build();
		return Response.serverError().build();
	}
	@GET
	@Path("/confirm")
	@Produces(MediaType.TEXT_PLAIN)
	public Response confirmDonation(@QueryParam("paymentId")String paymentId,@QueryParam("token")String token,@QueryParam("PayerID")String PayerID){
		Donation d = donationservice.findById(paymentId);
		if ((d != null) && (d.getValidatedAt()==null)){
			APIContext context = new APIContext(clientId, clientSecret, "sandbox");
			Payment payapproved=new Payment();
			payapproved.setId(paymentId);
			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(PayerID);
			try {
			  payapproved.execute(context, paymentExecution);
			  d.setValidatedAt(new Date());
			  d.setState("valid");
			  donationservice.update(d);
			  return Response.ok().entity("Thanks for your donation").build();
			} catch (PayPalRESTException e) {
			  System.err.println(e.getDetails());
				return Response.serverError().build();
			}
		}else{
			return Response.serverError().build();
		}
	}
	@GET
	@Path("/cancel")
	@Produces(MediaType.TEXT_PLAIN)
	public Response cancelDonation(@QueryParam("token")String token){
		return Response.ok().entity("Homepage").build();
	}
}
