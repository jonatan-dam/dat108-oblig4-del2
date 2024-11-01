package no.hvl.dat108.util;

public class InputValidator {

	public static boolean isValidUsername(String mobil) {
		return mobil != null && mobil.matches("^[0-9]{8}$");
	}
	
	public static boolean isValidPassword(String passordhash) {
		return passordhash != null && passordhash.matches("^.{7,}$");
	}
	
	public static boolean isValidPasswordRepeated(String passordhash, String passordRepetert) {
		return passordRepetert.equals(passordhash);
	}
}
