/**
 * En controller-klasse for utlogging av brukere
 */

package no.hvl.dat108.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.util.LoginUtil;

@Controller
@RequestMapping("/loggut")
public class LogoutController {

	/**
	 * En PostMapping som håndterer utlogging
	 * @param session
	 * @param ra
	 * @return inlogging.jsp et view for å logge inn igjen
	 */
	@PostMapping
	public String loggUtBruker(HttpSession session, RedirectAttributes ra) {
		
		LoginUtil.loggUtBruker(session);
		ra.addFlashAttribute("redirectMessage", "Du er nå logget ut");
		
		
		return "redirect:innlogging";
	} //end loggUtBruker
	
}
