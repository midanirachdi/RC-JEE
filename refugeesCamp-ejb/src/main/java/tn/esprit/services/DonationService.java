package tn.esprit.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import tn.esprit.entities.Camp;
import tn.esprit.entities.Donation;
import tn.esprit.interfaces.DonationRemoteInterface;

@Stateless
@LocalBean
public class DonationService implements DonationRemoteInterface{
	
	@PersistenceContext(unitName = "refugeesCamp-ejb")
	EntityManager em;
	@Override
	public boolean add(Donation d) {
		em.persist(d);
		return true;
	}

	@Override
	public boolean delete(Donation d) {
		em.remove(em.merge(d));
		return true;
	}

	@Override
	public List<Donation> findAll() {
		String requete = "SELECT d FROM Donation d";
		return em.createQuery(requete,Donation.class).getResultList();
	}

	@Override
	public List<Donation> findByCamp(Camp c) {
		String requete = "SELECT d FROM Donation d WHERE d.to_camp ="+c.getId();
		return em.createQuery(requete,Donation.class).getResultList();
	}

	@Override
	public Donation findById(String id) {
		return em.find(Donation.class, id);
	}

	@Override
	public boolean update(Donation d) {
		em.merge(d);
		return true;
	}
	
	public Double convert(String from, String to, Double amount) throws IOException{

		Document document = Jsoup.connect("http://www.xe.com/currencyconverter/convert/?Amount="+amount+"&From="+from+"&To="+ to).get();
		
		Elements element= document.select(".uccResultAmount");
		
		return Double.parseDouble(element.text());         /// lahi tconverti string chor double
	}
	
	public Double getCampAvgTotalDonation(Camp camp){
		List<Donation> list = findByCamp(camp);
		
		Map<String, Double> currencyMap = new HashMap<>();// listz jdid 
		
		for(Donation donation : list){
			Double amount = currencyMap.get(donation.getCurrency().toUpperCase());
			if(amount == null){
				currencyMap.put(donation.getCurrency().toUpperCase(), donation.getAmount());
			}else{
				currencyMap.put(donation.getCurrency().toUpperCase(), donation.getAmount() + amount);
			}
		}
		
		Double totalTND = 0d;
		
		for (Map.Entry<String, Double> entry : currencyMap.entrySet()) {
			try {
				totalTND += convert(entry.getKey(), "TND", entry.getValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return totalTND;
	}
	public Double getCampAvgTotalDonation(){
		List<Donation> list = findAll();
		
		Map<String, Double> currencyMap = new HashMap<>();// listz jdid 
		
		for(Donation donation : list){
			Double amount = currencyMap.get(donation.getCurrency().toUpperCase());
			if(amount == null){
				currencyMap.put(donation.getCurrency().toUpperCase(), donation.getAmount());
			}else{
				currencyMap.put(donation.getCurrency().toUpperCase(), donation.getAmount() + amount);
			}
		}
		
		Double totalTND = 0d;
		
		for (Map.Entry<String, Double> entry : currencyMap.entrySet()) {
			try {
				totalTND += convert(entry.getKey(), "TND", entry.getValue());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return totalTND;
	}
	
}
