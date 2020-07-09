/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.domaine.projection;

import eu.ensup.gestionscolairespringboot.domaine.Etudiant;

/**
 *
 * @author lorris
 */
public class EtudiantMoyenneVO extends Etudiant{
    
    private double moyenne;

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }
   
    
    
    
}
