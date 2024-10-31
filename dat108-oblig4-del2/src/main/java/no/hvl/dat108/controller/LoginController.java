package no.hvl.dat108.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;
import no.hvl.dat108.service.PassordService;
import no.hvl.dat108.util.InputValidator;
import no.hvl.dat108.util.LoginUtil;

@Controller
@RequestMapping("/innlogging")
public class LoginController {
	
	@Autowired
	private PassordService passordService;
	
	@Autowired
	private DeltagerRepo deltagerRepo;
	
	@GetMapping
	public String hentLoginSkjema() {
		return "innlogging";
	}
	
	@PostMapping
	public String provInnlogging(@RequestParam String mobil, @RequestParam String passord,
			HttpServletRequest request, RedirectAttributes ra) {
		
		Deltager bruker = deltagerRepo.findByMobil(mobil);
		
		
		
		// Hvis ugyldig brukernavn (mobilnummer) eller passord, gå til innlogging med feilmelding
		if(!InputValidator.isValidUsername(mobil) || !passordService.erKorrektPassord(passord, bruker.getSalt(), bruker.getPassordhash())) {
			ra.addFlashAttribute("redirectMessage", "Brukernavn og/eller passord er ikke gyldig.");
			return "redirect:innlogging";
		}
		
		// Logger inn
		LoginUtil.loggInnBruker(request, mobil, passord);
		
		return "redirect:deltagerliste";
	}
}
