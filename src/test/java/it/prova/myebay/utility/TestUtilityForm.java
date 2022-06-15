package it.prova.myebay.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import it.prova.myebay.dto.UtenteEdit;
import it.prova.myebay.dto.UtenteSearch;
import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Ruolo;
import it.prova.myebay.model.StatoUtente;

class TestUtilityForm {
	
	@Test
	void TestCreateAnnuncioFromParams() throws Exception {
		String titolo = "titolo";
		String testoAnnuncio = "testo";
		String prezzo = "48";
		String[] categorie = {"1", "2", "3"};
		
		int prezzoParsed = Integer.parseInt(prezzo);
		
		Set<Categoria> setCategorie = new HashSet<>();
		setCategorie.add(new Categoria(Long.parseLong(categorie[0]))); 
		setCategorie.add(new Categoria(Long.parseLong(categorie[1]))); 
		setCategorie.add(new Categoria(Long.parseLong(categorie[2]))); 
		
		List<Long> listaIdExpected = setCategorie.stream().map(
				categoria -> categoria.getId()
			).collect(Collectors.toList());
		
		Annuncio annuncioActual = UtilityForm.createAnnuncioFromParams(titolo, testoAnnuncio, prezzo, categorie);	
		
		List<Long> listaIdActual = annuncioActual.getCategorie().stream().map(
				categoria -> categoria.getId()
			).collect(Collectors.toList());
		
		assertEquals(titolo, annuncioActual.getTitolo());
		assertEquals(testoAnnuncio, annuncioActual.getTestoAnnuncio());
		assertEquals(prezzoParsed, annuncioActual.getPrezzo());
		
		assertTrue(listaIdActual.containsAll(listaIdExpected));
	}
	
	@Test
	void TestCreateUtenteSearchFromParams() throws Exception {
		String nome = "nome";
		String cognome = "cognome";
		String username = "username";
		String dateCreated = "01/01/2000";
		String[] ruoli = {"ROLE_CLASSIC_USER", "ROLE_ADMIN"};
		
		Date dateParsed = UtilityForm.parseDateFromString(dateCreated);
		
		List<Ruolo> listaRuoli = new ArrayList<>();
		listaRuoli.add(Ruolo.fromString("ROLE_CLASSIC_USER"));
		listaRuoli.add(Ruolo.fromString("ROLE_ADMIN"));
		
		UtenteSearch utenteActual = UtilityForm
				.createUtenteSearchFromParams(nome, cognome, username, dateCreated, ruoli);
		
		assertEquals(nome, utenteActual.getNome());
		assertEquals(cognome, utenteActual.getCognome());
		assertEquals(username, utenteActual.getUsername());
		assertEquals(dateParsed, utenteActual.getDateCreated());
		
		assertEquals(listaRuoli, utenteActual.getRuoli());
	}
	
	@Test
	void TestValidateUtenteBeanForEdit() {
		UtenteEdit utenteParam = new UtenteEdit("nome", "cognome", "username", StatoUtente.ATTIVO);
		
		assertTrue(UtilityForm.validateUtenteBeanForEdit(utenteParam));
	}
	
	@Test
	void TestValidateUtenteBeanForEdit_failure() {
		UtenteEdit utenteParam = new UtenteEdit("", "cognome", "username", null);
		
		assertFalse(UtilityForm.validateUtenteBeanForEdit(utenteParam));
	}
}
