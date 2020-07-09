/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.ensup.gestionscolairespringboot.domaine;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author lorris
 */
@Entity
public class Note {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	private int idEtu;
	private int idEns;
	private double valeur;

    public Note() {
        super();
    }

    public Note(int id, int idEtu, int idEns, double note) {
        super();
        this.id = id;
        this.idEtu = idEtu;
        this.idEns = idEns;
        this.valeur = note;
    }
        
        

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtu() {
        return idEtu;
    }

    public void setIdEtu(int idEtu) {
        this.idEtu = idEtu;
    }

    public int getIdEns() {
        return idEns;
    }

    public void setIdEns(int idEns) {
        this.idEns = idEns;
    }




    public double getNote() {
        return valeur;
    }

    public void setNote(double note) {
        this.valeur = note;
    }
        
        
	
    
}
