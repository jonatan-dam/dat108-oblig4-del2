/**
 * En controller-klasse for å håndtere påmelding av deltagere
 */
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import no.hvl.dat108.model.Deltager;
import no.hvl.dat108.repository.DeltagerRepo;
import no.hvl.dat108.service.PassordService;
import no.hvl.dat108.util.InputValidator;
import no.hvl.dat108.util.LoginUtil;

@Controller
public class PaameldingController {
	
	/**
	 * En custom binder som setter passord til å være untatt fra databinding
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("passord");
	} //end initBinder

	@Autowired
	private DeltagerRepo deltagerRepo;
	
	@Autowired
	private PassordService passordService;
	
	
	/**
	 * En GetMapping som viser påmeldingsskjemaet
	 * @return paamelding_med_melding.jsp view med skjema
	 */
	@GetMapping("/paamelding")
	public String paamelding() {
		return "paamelding_med_melding";
	} //end paamelding
	
	
	/**
	 * En GetMapping som viser kvittering etter vellykket påmelding. 
	 * @return paameldt.jsp view med kvittering
	 */
	@GetMapping("/kvittering")
	public String kvittering() {
		return "paameldt";
	} //end kvittering
	
	
	/**
	 * En PostMapping som håndterer noe validering av data, feilmeldinger, og lagring av deltagere
	 * @param deltager Deltager som skal meldes på
	 * @param bindingResult Resultat av databinding og validering
	 * @param passord uhashet passort
	 * @param passordRepetert 
	 * @param session
	 * @param request
	 * @return paamelding.jsp dersom noen feil, kvittering.jsp dersom gyldig påmelding
	 */
	@PostMapping(value ="/sjekkDeltager")
	public String sjekkDeltager(@Valid @ModelAttribute("deltager") Deltager deltager, BindingResult bindingResult, @RequestParam String passord,
			@RequestParam String passordRepetert, RedirectAttributes ra, HttpServletRequest request) {
		
		
		// Validerer deltager-objektet
		if(bindingResult.hasErrors()) { 
			List<String> feilmeldinger = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();

			ra.addFlashAttribute("RAfeilmeldinger", feilmeldinger);
			return "redirect:paamelding";
		}
		
		
		// Validerer passord
		if(!InputValidator.isValidPassword(passord)) {
			ra.addFlashAttribute("RAfeilmeldinger", "Passord er obligatorisk, og må ha en minimumslengde på 7.");
			return "redirect:paamelding";
		}
		
		if(!InputValidator.isValidPasswordRepeated(passord, passordRepetert)){
			ra.addFlashAttribute("RAfeilmeldinger", "Repetert passord må være identisk med det første passordet.");
			return "redirect:paamelding";
		}
		
		// Validerer at det ikke allerede er registrert en deltager med det gitte telefonnummeret
		if(deltagerRepo.existsByMobil(deltager.getMobil())) {
			ra.addFlashAttribute("RAfeilmeldinger", "Det er allerede registrert en deltager med det gitte telefonnummeret.");
			return "redirect:paamelding";
		}
		
		
		ra.addFlashAttribute("RAdeltager", deltager); // Lagrer atributter i session til kvitteringsview
		
		deltager.setSalt(passordService.genererTilfeldigSalt()); // Genererer og lagrer salt til hashing
		deltager.setPassordhash(passordService.hashMedSalt(passord, deltager.getSalt())); // Hasher brukerens passord med salt
		
		deltagerRepo.save(deltager); // Lagrer deltageren i databasen
		LoginUtil.loggInnBruker(request, deltager.getMobil(), deltager.getPassordhash());// Logger inn brukeren
		
		
		
		return "redirect:kvittering";
	} //end sjekkDeltager
	
	
}
