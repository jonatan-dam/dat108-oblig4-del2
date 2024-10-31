package no.hvl.dat108.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;
import no.hvl.dat108.service.PassordService;
import no.hvl.dat108.util.LoginUtil;

@Controller
public class DeltagerListeController {
	
	@Autowired
	private DeltagerRepo deltagerRepo;
	
	@Autowired
	private PassordService passordService;
	
	@GetMapping("/deltagerliste")
	public String deltagerliste(Model model, HttpSession session, RedirectAttributes ra) {
		
		if(!LoginUtil.erBrukerInnlogget(session)) {
			ra.addFlashAttribute("redirectMessage", "Du må logge inn!");
			return "redirect:innlogging";
		}
		
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

}
