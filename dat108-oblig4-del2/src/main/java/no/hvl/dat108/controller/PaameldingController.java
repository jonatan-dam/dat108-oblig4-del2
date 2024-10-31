package no.hvl.dat108.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;

@Controller
public class PaameldingController {

	@Autowired
	private DeltagerRepo deltagerRepo;
	
	@GetMapping("/paamelding")
	public String paamelding() {
		return "paamelding_med_melding";
	}
	
	
	@GetMapping("/deltagerliste")
	public String deltagerliste(Model model) {
		List<Deltager> alleDeltagere = deltagerRepo.findAll();
		
		// Legger inn sortering på fornavn først, etterfølgt av sortering på etternavn
		Comparator<Deltager> comp = Comparator
			.comparing(Deltager::getFornavn)
			.thenComparing(Deltager::getEtternavn);
				
		List<Deltager> sortertDeltagere = alleDeltagere.stream()
			.sorted(comp)
			.toList();
				
		model.addAttribute("deltagere", sortertDeltagere);
		
		return "deltagerliste";
	}
	
	
	@GetMapping("/kvittering")
	public String kvittering() {
		return "paameldt";
	}
	
	@PostMapping(value ="/sjekkDeltager")
	public String sjekkDeltager(@Valid @ModelAttribute("deltager") Deltager deltager, BindingResult bindingResult, HttpSession session) {
		
		// Validerer deltager-objektet
		if(bindingResult.hasErrors()) { 
			List<String> feilmeldinger = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			
			session.setAttribute("SAfeilmeldinger", feilmeldinger);
			return "redirect:paamelding";
		}
		
		// Validerer at det ikke allerede er registrert en deltager med det gitte telefonnummeret
		if(deltagerRepo.existsByMobil(deltager.getMobil())) {
			session.setAttribute("SAfeilmeldinger", "Det er allerede registrert en deltager med det gitte telefonnummeret.");
			return "redirect:paamelding";
		}
		
		deltagerRepo.save(deltager);
		session.setAttribute("SAdeltager", deltager);
		
		return "redirect:kvittering";
	}
	
	
}
