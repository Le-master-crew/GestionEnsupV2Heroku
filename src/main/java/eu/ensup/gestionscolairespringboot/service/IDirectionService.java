/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.service;

import java.util.List;

import eu.ensup.gestionscolairespringboot.domaine.Note;
import eu.ensup.gestionscolairespringboot.domaine.projection.EtudiantMoyenneVO;

/**
 *
 * @author lorris
 */
public interface IDirectionService {
    
    
     public List<EtudiantMoyenneVO> listeMoyenneEtudiants();
     
     public EtudiantMoyenneVO calculerMoyenneEtudiants(List<Note> liste, int idEtu);
     
     public String construcGraph(List<EtudiantMoyenneVO> liste);
}
