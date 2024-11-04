/**
 * En controller-klasse for fremvisning av deltagerlisten
 */

package no.hvl.dat108.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.service.DeltagerService;
import no.hvl.dat108.util.LoginUtil;

@Controller
public class DeltagerListeController {
	
	@Autowired
	private DeltagerService deltagerService;
	
	/**
	 * En GetMapping for å fremvise en liste av deltagere. Krever at brukeren er innlogget.
	 * Setter også attributtene fornavn og etternavn, slik at brukeren kan se hvem hen er logget inn som.
	 * @return deltagerliste.jsp view for fremvisning av deltagere
	 */
	@GetMapping("/deltagerliste")
	public String deltagerliste(Model model, HttpSession session, RedirectAttributes ra) {
		
		if(!LoginUtil.erBrukerInnlogget(session)) {
			ra.addFlashAttribute("redirectMessage", "Du må logge inn!");
			return "redirect:innlogging";
		}
		
		String mobil = (String) session.getAttribute("username");
		session.setAttribute("fornavn", deltagerService.finnVedMobil(mobil).getFornavn());
		session.setAttribute("etternavn", deltagerService.finnVedMobil(mobil).getEtternavn());
		
		
		// Henter en sortert liste over alle deltagere		
		model.addAttribute("deltagere", deltagerService.sorterDeltagere());
		
		return "deltagerliste";
	} //end deltagerliste

}
