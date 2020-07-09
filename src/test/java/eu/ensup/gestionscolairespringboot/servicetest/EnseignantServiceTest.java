package eu.ensup.gestionscolairespringboot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import eu.ensup.gestionscolairespringboot.dao.NoteRepository;
import eu.ensup.gestionscolairespringboot.domaine.Note;
import eu.ensup.gestionscolairespringboot.service.EnseignantService;

class EnseignantServiceTest {

	@Mock
	private NoteRepository dao;
	
	@Autowired
	@InjectMocks
	private EnseignantService service;
	
	@BeforeEach
	public void setupMock() {
		MockitoAnnotations.initMocks(this); // Cette méthode initialise également les objets simulés lors de
											// l'initialisation des tests junit.
	} 
	
	@Test
	void noterEtudiantTest() {
		//l'enseignant dont l'id est 1 donne une Note de 20.00 à 
		//l'étudiant dont l'id est 1 retourne une nouvelle Note de 20.00
		Note note = new Note();
		note.setIdEns(1);
		note.setIdEtu(1);
		note.setNote(20.00);
		Note note1 = new Note();
		note.setNote(20.00);
		when(dao.save(note)).thenReturn(note1);
		assertEquals(note1,  service.noterEtudiant(note));
		
	}

}
