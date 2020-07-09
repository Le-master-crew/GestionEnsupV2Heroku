/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.service;

import eu.ensup.gestionscolairespringboot.dao.NoteRepository;
import eu.ensup.gestionscolairespringboot.domaine.Note;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lorris
 */
public class EnseignantService implements IEnseignantService {

	@Autowired
	private NoteRepository noteDAO;

	@Override
	public Note noterEtudiant(Note n) {
		return noteDAO.save(n);

	}

}
