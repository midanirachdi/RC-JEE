package tn.esprit.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import tn.esprit.entities.Donation;
import tn.esprit.interfaces.DonationRemoteInterface;
import tn.esprit.interfaces.PaypalRemoteInterface;
public class PaypalService{
	Context ctx;
	//My ClientId and Secret
	String clientId = "AXWaTaAVKEbzNcdqqbYCSnAxI1tNB6uiIINJphmt3DKeNHErhZdFSDHxj_6owFCld7oS-CwkeGCbcE3a";
	String clientSecret = "EC--VCYTG-icuQgIz0WchzeK2xryFzfIO0lHU5VSDJeHIl_B4XLuSce2PAd-MrkIZ4ypflkVyyNTS5Cj";
	APIContext context = new APIContext(clientId, clientSecret, "sandbox");
	public String generatePayment(double total,String currency){
		DonationRemoteInterface ds = null;
		try {
			ctx=new InitialContext();
			ds=(DonationRemoteInterface)ctx.lookup("/refugeesCamp-ear/refugeesCamp-ejb/DonationService!tn.esprit.interfaces.DonationRemoteInterface");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

		// Create payment
		try {
			Payment createdPayment = payment.create(context);
			Donation d=new Donation(createdPayment.getId(), Double.parseDouble(createdPayment.getTransactions().get(0).getAmount().getTotal()), new Date(), createdPayment.getTransactions().get(0).getAmount().getCurrency());
			ds.add(d);
			return createdPayment.getLinks().get(1).getHref();
		} catch (PayPalRESTException e) {
			System.err.println(e.getDetails());
		}
		return "non trouvé";
	}
	public String confirmPayment(String payerId,String paymentID){	
		DonationRemoteInterface ds = null;
		try {
			ctx=new InitialContext();
			ds=(DonationRemoteInterface)ctx.lookup("/refugeesCamp-ear/refugeesCamp-ejb/DonationService!tn.esprit.interfaces.DonationRemoteInterface");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Payment payapproved=new Payment();
		payapproved.setId(paymentID);
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		try {
		  Payment createdPayment = payapproved.execute(context, paymentExecution);
		  Donation d = ds.findById(createdPayment.getId());
		  d.setValidatedAt(new Date());
		  d.setState("valid");
		  ds.update(d);
		  return "Thanks for your Donation";
		} catch (PayPalRESTException e) {
		  System.err.println(e.getDetails());
		}
		return "error";
	}
}
