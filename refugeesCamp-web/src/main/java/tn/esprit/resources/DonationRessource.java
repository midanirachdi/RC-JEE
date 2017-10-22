package tn.esprit.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

@Path("/donation")
public class DonationRessource {
	@EJB
	DonationService donationservice;
	@EJB
	CampService cs;
	
	
	public DonationRessource() {
		super();
	}
	
	@GET
	@Path("/{total}/{currency}")
	public Response generateDonation(@PathParam("total")double total,@PathParam("currency")String currency){
		String clientId = "AcG08W3mBVODYQMJFzB_ojA3ylNxL4ot9ptCY92akURy9WOFxi426XEvqYlZZG38APEqsJLzxwb0NmH-";
		String clientSecret = "EHVEu1m90nsiv-x8S6vzaqrSbC25vg0oZ3RdtYm4Z5A2uA09WFTPpeQ67RrHc54RtJVvxXIKitxSR7VO";
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
			return Response.status(Status.CREATED).entity(createdPayment.getLinks().get(1).getHref()).build();
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return Response.serverError().build();
	}
	@POST
	@Path("/{total}/{currency}")
	public Response generateDonationForCamp(@PathParam("total")double total,@PathParam("currency")String currency,Camp c){
		if (cs.findById(c.getId())==null)
			return Response.status(Status.NOT_FOUND).build();
		String clientId = "AcG08W3mBVODYQMJFzB_ojA3ylNxL4ot9ptCY92akURy9WOFxi426XEvqYlZZG38APEqsJLzxwb0NmH-";
		String clientSecret = "EHVEu1m90nsiv-x8S6vzaqrSbC25vg0oZ3RdtYm4Z5A2uA09WFTPpeQ67RrHc54RtJVvxXIKitxSR7VO";
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
		.setDescription("Donation for Camp"+c.getName());

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
			Donation d=new Donation(createdPayment.getId(), Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal()), new Date(), createdPayment.getTransactions().get(0).getAmount().getCurrency(),c);
			donationservice.add(d);
			return Response.status(Status.CREATED).entity(createdPayment.getLinks().get(1).getHref()).build();
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return Response.serverError().build();
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
			String clientId = "AcG08W3mBVODYQMJFzB_ojA3ylNxL4ot9ptCY92akURy9WOFxi426XEvqYlZZG38APEqsJLzxwb0NmH-";
			String clientSecret = "EHVEu1m90nsiv-x8S6vzaqrSbC25vg0oZ3RdtYm4Z5A2uA09WFTPpeQ67RrHc54RtJVvxXIKitxSR7VO";
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
			  Response.ok().entity("Thanks for your donation").build();
			} catch (PayPalRESTException e) {
			  System.err.println(e.getDetails());
			}
		}
		return Response.serverError().build();
	}
	@GET
	@Path("/cancel")
	@Produces(MediaType.TEXT_PLAIN)
	public Response cancelDonation(@QueryParam("token")String token){
		return Response.ok().entity("Homepage").build();
	}
}
