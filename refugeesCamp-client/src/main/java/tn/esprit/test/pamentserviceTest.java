package tn.esprit.test;

import java.util.List;
import java.util.ArrayList;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class pamentserviceTest {
	public static void main(String []args){
		//My ClientId and Secret
		String clientId = "Aey8unrFl71F5O3NTjBCNe08Nh9ktrzKf6kOM_XAvsZUhdDk2uxWbJkFimU5gUHR7GE-rdLCA9Rwhs9p";
		String clientSecret = "EJfDu1NxotLb02YHc-Q3bqRYTnxoN01ZtydBmFdwurFCzpldFJD2qhqhMJO-FyiaDjBCJX2Uhu1LmlAn";
		APIContext context = new APIContext(clientId, clientSecret, "sandbox");
		// Set payer details
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:18080/cancel");
		redirectUrls.setReturnUrl("http://localhost:18080/process");

		// Payment amount
		Amount amount = new Amount();
		amount.setCurrency("USD");
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal("30");

		// Transaction information
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		  .setDescription("thanks for your donation.");

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
		  List<Links> liens=createdPayment.getLinks();
		  
		  for (Links l : liens) {
			  if (l.getRel().equalsIgnoreCase("approval_url")) {
			      System.out.println(l.getHref());
			    }
		}
		} catch (PayPalRESTException e) {
		    System.err.println(e.getDetails());
		}
		Payment payapproved= new Payment();
		payapproved.setId("PAY-99051175396992223LHT73WQ");

		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId("DHEKXMRTRHDEE");
		try {
		  Payment createdPayment = payapproved.execute(context, paymentExecution);
		  System.out.println(createdPayment);
		} catch (PayPalRESTException e) {
		  System.err.println(e.getDetails());
		}
	}
}

