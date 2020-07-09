/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import eu.ensup.gestionscolairespringboot.dao.EtudiantRepository;
import eu.ensup.gestionscolairespringboot.dao.NoteRepository;
import eu.ensup.gestionscolairespringboot.domaine.Etudiant;
import eu.ensup.gestionscolairespringboot.domaine.Note;
import eu.ensup.gestionscolairespringboot.domaine.projection.EtudiantMoyenneVO;

/**
 *
 * @author lorris
 */
public class DirectionService implements IDirectionService {

	@Autowired
	private NoteRepository noteDAO;

	@Autowired
	private EtudiantRepository daoetu;

	@Override
	public List<EtudiantMoyenneVO> listeMoyenneEtudiants() {
		
		List<Etudiant> listeEtudiant = daoetu.findAll();
		List<Note> listeNoteEtudiant;
		List<EtudiantMoyenneVO> listeMoyenneEtudiant = new ArrayList<>();
		
		for (int y = 0; y < listeEtudiant.size(); y++) {
			System.out.println("id "+ listeEtudiant.get(y).getId());
			listeNoteEtudiant = noteDAO.findAllByIdEtu(listeEtudiant.get(y).getId());
			EtudiantMoyenneVO etudiantMoyenneVO = this.calculerMoyenneEtudiants(listeNoteEtudiant,
					listeEtudiant.get(y).getId());
			listeMoyenneEtudiant.add(etudiantMoyenneVO);
		}
		return listeMoyenneEtudiant;
	}

	/**
	 *
	 * Calcule la moyenne de toute les notes d'un étudiant
	 */
	@Override
	public EtudiantMoyenneVO calculerMoyenneEtudiants(List<Note> liste, int idEtu) {

		double countNotes = 0.0;
		int index = 0;
		double moyenne = 0.0;
		for (int i = 0; i < liste.size(); i++) {
			index++;
			countNotes = countNotes + liste.get(i).getNote();
		}
		if(index!=0) {
			moyenne = countNotes / index;
		}
		
		EtudiantMoyenneVO etuVO = new EtudiantMoyenneVO();
		Etudiant etu = daoetu.getOne(idEtu);
		etuVO.setAdresse(etu.getAdresse());
		etuVO.setCours(etu.getCours());
		etuVO.setDateNaissance(etu.getDateNaissance());
		etuVO.setMail(etu.getMail());
		etuVO.setId(etu.getId());
		etuVO.setNom(etu.getNom());
		etuVO.setPrenom(etu.getPrenom());
		etuVO.setTelephone(etu.getTelephone());
		etuVO.setMoyenne(moyenne);

		return etuVO;
	}
	
	@Override
	public String construcGraph(List<EtudiantMoyenneVO> liste)
	{
		String construct = "";
		construct = "labels: [";
			  for (int i = 0; i < liste.size(); i ++)
			  {
				  construct = construct + "'"+liste.get(i).getNom()+"',";
			  }
			  construct =   construct.substring(0, construct.length()-1);
			  construct = construct + "],\r\n" + 
			  		"	    datasets: [{\r\n" + 
			  		"	        fillColor: \"rgba(220,220,220,0)\",\r\n" + 
			  		"	        strokeColor: \"rgba(220,180,0,1)\",\r\n" + 
			  		"	        pointColor: \"rgba(220,180,0,1)\",\r\n" + 
			  		"	        data: [";
			  for (int i = 0; i < liste.size(); i ++)
			  {
				  construct = construct + "'"+liste.get(i).getMoyenne()+"',";
				 
			  }
		 construct =   construct.substring(0, construct.length()-1);
		 construct = construct +"  ]}]";
		return construct;
		
		
	}

}
