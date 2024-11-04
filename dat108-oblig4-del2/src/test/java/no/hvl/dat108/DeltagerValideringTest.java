package no.hvl.dat108;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import no.hvl.dat108.model.Deltager;

class DeltagerValideringTest {
	
	private Validator validator;
	
	private Deltager testDeltager;

	@BeforeEach
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		testDeltager = new Deltager(
				"12345678",
				"Tester",
				"Testesen",
				"mann"
				);
		
	}

	@Test
	void testDeltagerHarGyldigeInitVerdier() {
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	void fornavnErObligatorisk() {
		testDeltager.setFornavn(null);
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void fornavnMatcherRegex() {
		testDeltager.setFornavn("Tester1");
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void etternavnErObligatorisk() {
		testDeltager.setEtternavn(null);
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void etternavnMatcherRegex() {
		testDeltager.setEtternavn("Testesen!");
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void mobilErObligatorisk() {
		testDeltager.setMobil(null);
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void mobilMatcherRegex() {
		testDeltager.setMobil("123456789");
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	
	
	@Test
	void kjonnErObligatorisk() {
		testDeltager.setKjonn(null);
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	@Test
	void kjonnMatcherRegex() {
		testDeltager.setKjonn("hannkjonn");
		Set<ConstraintViolation<Deltager>> violations = validator.validate(testDeltager);
		
		assertFalse(violations.isEmpty());
		assertThat(violations).hasSize(1);
	}
	
	
	
	

}
