package no.hvl.dat108.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;

@Service
public class DeltagerService {

	@Autowired private DeltagerRepo deltagerRepo;
	
	public List<Deltager> finnAlleDeltagere(){
		return deltagerRepo.findAll();
	}
	
	public boolean erMobilRegistrert(String mobil) {
		return deltagerRepo.existsByMobil(mobil);
	}
	
	public Deltager finnVedMobil(String mobil) {
		return deltagerRepo.findByMobil(mobil);
	}
	
	public List<Deltager> finnAlle(){
		return deltagerRepo.findAll();
	}
}
