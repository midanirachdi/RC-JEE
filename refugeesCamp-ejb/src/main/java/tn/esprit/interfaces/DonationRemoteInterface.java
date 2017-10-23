package tn.esprit.interfaces;

import java.io.IOException;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Camp;
import tn.esprit.entities.Donation;
@Remote
public interface DonationRemoteInterface {
	boolean add(Donation d);
	boolean delete(Donation d);
	boolean update(Donation d);
	List<Donation> findAll();
	List<Donation> findByCamp(Camp c);
	Donation findById(String id);
	Double convert(String from, String to, Double amount) throws IOException;
}
