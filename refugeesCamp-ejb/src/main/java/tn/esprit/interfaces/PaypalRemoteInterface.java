package tn.esprit.interfaces;

import javax.ejb.Remote;

@Remote
public interface PaypalRemoteInterface {
	 String generatePayment(double total,String currency);
	 String confirmPayment(String payerId,String paymentID);
}
