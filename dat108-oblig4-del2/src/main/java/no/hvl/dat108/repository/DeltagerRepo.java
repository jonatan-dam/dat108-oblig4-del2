package no.hvl.dat108.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import no.hvl.dat108.model.Deltager;

public interface DeltagerRepo extends JpaRepository<Deltager, String> {
	/**
	 * Leter etter deltager med gitt telefonnummer
	 * @param mobil mobilnummeret som skal brukes til søking
	 * @return Deltager Deltageren som er registrert med det gitte telefonnummeret
	 */
	Deltager findByMobil(String mobil);
	
	/**
	 * Sjekker om det finnes en deltager registrert med det gitte telefonnummeret
	 * @param mobil mobilnummeret som skal sjekkes
	 * @return boolean true dersom deltager eksisterer, false dersom deltager ikke eksisterer
	 */
	boolean existsByMobil(String mobil);
}
