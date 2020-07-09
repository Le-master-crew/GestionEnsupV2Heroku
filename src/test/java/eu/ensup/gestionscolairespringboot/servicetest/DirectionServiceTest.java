package eu.ensup.gestionscolairespringboot.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import eu.ensup.gestionscolairespringboot.dao.EtudiantRepository;
import eu.ensup.gestionscolairespringboot.dao.NoteRepository;
import eu.ensup.gestionscolairespringboot.domaine.Etudiant;
import eu.ensup.gestionscolairespringboot.domaine.Note;
import eu.ensup.gestionscolairespringboot.domaine.projection.EtudiantMoyenneVO;
import eu.ensup.gestionscolairespringboot.service.DirectionService;
import eu.ensup.gestionscolairespringboot.service.EtudiantService;

class DirectionServiceTest {

	@Mock
	private NoteRepository daoNote;
	@Mock
	private EtudiantRepository daoEtu;

	@Autowired
	@InjectMocks
	private EtudiantService etudiantService;

	@Autowired
	@InjectMocks
	private DirectionService directionService;

	@BeforeEach
	public void setupMock() {
		MockitoAnnotations.initMocks(this); // Cette méthode initialise également les objets simulés lors de
											// l'initialisation des tests junit.
	}

	@Test
	void listerMoyenneEtudiantReturn10() {
		//
		Etudiant etuExpected1 = new Etudiant();
		etuExpected1.setId(1);
		etuExpected1.setAdresse("");
		etuExpected1.setDateNaissance("");
		etuExpected1.setMail("");
		etuExpected1.setNom("");
		etuExpected1.setPrenom("");
		etuExpected1.setTelephone(1);
		List<Etudiant> etudiants = new ArrayList<>();
		etudiants.add(etuExpected1);

		Note note1 = new Note();
		note1.setNote(20.00);
		Note note2 = new Note();
		note2.setNote(0.0);
		List<Note> listeNoteEtudiant = new ArrayList<>();

		note1.setIdEtu(etuExpected1.getId());
		note2.setIdEtu(etuExpected1.getId());

		listeNoteEtudiant.add(note1);
		listeNoteEtudiant.add(note2);

		List<EtudiantMoyenneVO> listeMoyenneEtudiant = new ArrayList<>();
		EtudiantMoyenneVO etuMoyenne = new EtudiantMoyenneVO();
		etuExpected1.setId(1);
		etuMoyenne.setAdresse("");
		etuMoyenne.setDateNaissance("");
		etuMoyenne.setMail("");
		etuMoyenne.setNom("");
		etuMoyenne.setPrenom("");
		etuMoyenne.setTelephone(1);
		etuMoyenne.setMoyenne(10.00);

		listeMoyenneEtudiant.add(etuMoyenne);
		
		when(daoEtu.getOne(1)).thenReturn(etuExpected1);
		when(daoEtu.findAll()).thenReturn(etudiants);
		when(daoNote.findAll()).thenReturn(listeNoteEtudiant);
		List<EtudiantMoyenneVO> listeMoyenneEtudiantactual =directionService.listeMoyenneEtudiants();
		assertEquals(listeMoyenneEtudiant.size(),listeMoyenneEtudiantactual.size());
		
	}

	@Test
	void calculerMoyenneEtudiantReturn10() {
		// la moyenne d'un étudiant dont l'id est 1 et dont les notes sont 0 et 20
		// est égale à la moyenne d'un nouveletudiant dont la moyenne est 10
		Etudiant etu = new Etudiant(1, "1", "1", "1", "1", 1, "1");

		Note note = new Note();
		note.setNote(0.0);
		Note note2 = new Note();
		note.setNote(20.00);
		List<Note> notes = new ArrayList<>();
		notes.add(note);
		notes.add(note2);

		when(daoEtu.getOne(1)).thenReturn(etu);

		EtudiantMoyenneVO etuVOExtpected = new EtudiantMoyenneVO();
		etuVOExtpected.setMoyenne(10.00);
		EtudiantMoyenneVO etuVOActual = directionService.calculerMoyenneEtudiants(notes, 1);

		assertEquals(etuVOExtpected.getMoyenne(), etuVOActual.getMoyenne());

	}
	
	@Test
	void testGraphiquenotnull() {
		List<EtudiantMoyenneVO> liste = new ArrayList<>();
		EtudiantMoyenneVO etumoy1 = new EtudiantMoyenneVO();
		etumoy1.setNom("eric");
		etumoy1.setMoyenne(10.00);
		liste.add(etumoy1);
		assertTrue(directionService.construcGraph(liste).contains("eric"));
	}

}
