package no.hvl.dat108.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;
import no.hvl.dat108.service.PassordService;
import no.hvl.dat108.util.InputValidator;
import no.hvl.dat108.util.LoginUtil;

@Controller
public class PaameldingController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("passord");
	}

	@Autowired
	private DeltagerRepo deltagerRepo;
	
	@Autowired
	private PassordService passordService;
	
	@GetMapping("/paamelding")
	public String paamelding() {
		return "paamelding_med_melding";
	}
	
	
	
	
	
	@GetMapping("/kvittering")
	public String kvittering() {
		return "paameldt";
	}
	
	@PostMapping(value ="/sjekkDeltager")
	public String sjekkDeltager(@Valid @ModelAttribute("deltager") Deltager deltager, BindingResult bindingResult, @RequestParam String passord,
			@RequestParam String passordRepetert, HttpSession session, HttpServletRequest request) {
		
		
		// Validerer deltager-objektet
		if(bindingResult.hasErrors()) { 
			List<String> feilmeldinger = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();

			session.setAttribute("SAfeilmeldinger", feilmeldinger);
			return "redirect:paamelding";
		}
		
		
		// Validerer passord
		if(!InputValidator.isValidPassword(passord)) {
			session.setAttribute("SAfeilmeldinger", "Passord er obligatorisk, og må ha en minimumslengde på 7.");
			return "redirect:paamelding";
		}
		
		if(!InputValidator.isValidPasswordRepeated(passord, passordRepetert)){
			session.setAttribute("SAfeilmeldinger", "Repetert passord må være identisk med det første passordet.");
			return "redirect:paamelding";
		}
		
		// Validerer at det ikke allerede er registrert en deltager med det gitte telefonnummeret
		if(deltagerRepo.existsByMobil(deltager.getMobil())) {
			session.setAttribute("SAfeilmeldinger", "Det er allerede registrert en deltager med det gitte telefonnummeret.");
			return "redirect:paamelding";
		}
		
		
		deltager.setSalt(passordService.genererTilfeldigSalt());
		deltager.setPassordhash(passordService.hashMedSalt(passord, deltager.getSalt()));
		
		session.setAttribute("SAdeltager", deltager);
		deltagerRepo.save(deltager);
		LoginUtil.loggInnBruker(request, deltager.getMobil(), deltager.getPassordhash());
		
		return "redirect:kvittering";
	}
	
	
}
