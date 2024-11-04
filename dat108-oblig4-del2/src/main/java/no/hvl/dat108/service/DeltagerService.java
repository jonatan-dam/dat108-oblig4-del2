package no.hvl.dat108.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;

@Service
public class DeltagerService {

	@Autowired private DeltagerRepo deltagerRepo;
	
	/**
	 * Finner alle deltagere i databasen
	 * @return List<Deltager>  En liste over alle deltagere
	 */
	public List<Deltager> finnAlleDeltagere(){
		return deltagerRepo.findAll();
	} //end finnAlleDeltagere
	
	/**
	 * Sjekker om mobilnummeret allerede er registrert i databasen
	 * @param mobil Mobilnummeret som skal sjekkes
	 * @return boolean True dersom mobilnummeret eksisterer, false dersom mobilnummeret ikke eksisterer
	 */
	public boolean erMobilRegistrert(String mobil) {
		return deltagerRepo.existsByMobil(mobil);
	} //end erMobilRegistrert
	
	/**
	 * Finner en deltager ved søk på mobilnummer
	 * @param mobil Mobilnummeret det skal søkes på
	 * @return Deltager Deltageren som er registrert med det gitte telefonnummeret
	 */
	public Deltager finnVedMobil(String mobil) {
		return deltagerRepo.findByMobil(mobil);
	} //end finnVedMobil
	
	/**
	 * Finner alle deltagere i databasen
	 * @return List<Deltager> En liste over alle deltagere
	 */
	public List<Deltager> finnAlle(){
		return deltagerRepo.findAll();
	} //end finnAlle
	
	/**
	 * 
	 * @return
	 */
	public List<Deltager> sorterDeltagere(){
		Comparator<Deltager> comp = Comparator
				.comparing(Deltager::getFornavn)
				.thenComparing(Deltager::getEtternavn);
					
		List<Deltager> sortertDeltagere = finnAlle().stream()
				.sorted(comp)
				.toList();
		
		return sortertDeltagere;
	} //end sorterDeltagere
	
	
}
