package eu.ensup.gestionscolairespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.ensup.gestionscolairespringboot.domaine.Cours;
import eu.ensup.gestionscolairespringboot.domaine.Etudiant;
import eu.ensup.gestionscolairespringboot.domaine.Note;
import eu.ensup.gestionscolairespringboot.domaine.projection.EtudiantMoyenneVO;
import eu.ensup.gestionscolairespringboot.service.DirectionService;
import eu.ensup.gestionscolairespringboot.service.EnseignantService;
import eu.ensup.gestionscolairespringboot.service.EtudiantService;
import eu.ensup.gestionscolairespringboot.service.IDirectionService;
import eu.ensup.gestionscolairespringboot.service.IEnseignantService;
import eu.ensup.gestionscolairespringboot.service.IEtudiantService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Khady, Benjamin and David Controller contenant les différentes
 *         méthodes permettant les interactions service <=> vues
 */
@Controller
public class StaticController {

	@Autowired
	IEtudiantService ietudiantservice;
	
	@Autowired
	IDirectionService idirectionservice;
	
	@Autowired
	IEnseignantService ienseignantservice;

	private static final String LISTE_ETU = "listeEtudiants";
	private static final String LISTE_COURS = "listeCours";
	/**
	 * @return
	 */
	@Bean
	public EtudiantService ietudiantservice() {
		return new EtudiantService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public EnseignantService ienseignantservice() {
		return new EnseignantService();
	}
	@Bean
	public DirectionService idirectionservice() {
		return new DirectionService();
	}

	/**
	 * 
	 */
	public StaticController() {
		super();
	}

	/**
	 * @return
	 */
	public IEtudiantService getIetudiantservice() {
		return ietudiantservice;
	}

	/**
	 * @param ietudiantservice
	 */
	public void setIetudiantservice(IEtudiantService ietudiantservice) {
		this.ietudiantservice = ietudiantservice;
	}

	/**
	 * @param iformationService
	 */
	public StaticController(IEtudiantService iformationService) {
		super();
		this.ietudiantservice = iformationService;
	}

	
	@GetMapping("/accessDenied")
    public String accessDenied(Model model) 
    {
        return "accessDenied";
    }
	
	/**
	 * Méthode listant les étudiants
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/"+LISTE_ETU)
	public String listeEtudiants(Model model) {
		model.addAttribute(LISTE_ETU, ietudiantservice.getAll());
		return LISTE_ETU;
	}

	/**
	 * Méthode listant les cours
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/"+LISTE_COURS)
	public String listeCours(Model model) {
		model.addAttribute(LISTE_COURS, ietudiantservice.getAllCours());
		return LISTE_COURS;
	}

	/**
	 * redirection vers la page d'accueil
	 * 
	 * @return
	 */
	@GetMapping({ "/", "/accueil" })
	public String accueil() {

		return "accueil";
	}
	
	@GetMapping("/getFormAjoutEtudiantCours")
	public String getFormAjoutEtudiantCours(Model model) {
		model.addAttribute(LISTE_ETU, ietudiantservice.getAll());
		model.addAttribute(LISTE_COURS, ietudiantservice.getAllCours());
		return "ajouterEtudiantCours";
	}
	
	@GetMapping("/getMoyenneEtudiants")
	public String getMoyenneEtudiants(Model model) {
		String moyenne = idirectionservice.construcGraph(idirectionservice.listeMoyenneEtudiants());
		model.addAttribute("listeMoyenneEtudiants", moyenne);
		return "moyenneEtudiant";
	}
	
	/**
	 * permet de lier un étudiant à un cours redirige vers la vue
	 * messageAjoutEtudiantCours.jsp
	 * 
	 * @param etudiant
	 * @param cours
	 * @return
	 */
	@PostMapping("/lierEtudiantCours")
	public String lierEtudiantCours(Etudiant etudiant, Cours cours) {
		ietudiantservice.lierCoursEtudiant(cours, etudiant);
		return "messageAjoutEtudiantCours";
	}

	/**
	 * Redirection à la vue ajouterEtudiant.jsp
	 * 
	 * @return
	 */
	@GetMapping("getFormAjoutEtudiant")
	public String getFormAjoutEtudiant() {
		return "ajouterEtudiant";
	}

	/**
	 * Méthode permettant de sauvegarder un étudiant dans la base en remplissant les
	 * différents attributs suivant :
	 * 
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param dateNaissance
	 * @param etudiant
	 * @param modelMap
	 * @return
	 * 
	 *         Redirige sur la vue listeEtudiants.jsp
	 */

	@ApiOperation(value = "Sauvegarde d'un étudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/saveEtudiant")
	public String saveEtudiant(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom,
			@RequestParam("telephone") int telephone, @RequestParam("adresse") String adresse,
			@RequestParam("mail") String mail, @RequestParam("dateNaissance") String dateNaissance,
			ModelMap modelMap) {
		Etudiant etudiant = new Etudiant();
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setAdresse(adresse);
		etudiant.setTelephone(telephone);
		etudiant.setDateNaissance(dateNaissance);
		ietudiantservice.saveStudent(etudiant);

		return "redirect:/"+LISTE_ETU;
	}

	/**
	 * redirige vers la vue searchEtudiant.jsp pour la recherche d'un étudiant
	 * 
	 * @return
	 */
	@GetMapping("getFormLireEtudiant")
	public String getFormLireEtudiant() {
		return "searchEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() récupère l'étudiant en
	 * question. Si l'étudiant existe, il est affiché dans la vue detailEtudiant.jsp
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "Lire un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/readEtudiant")
	public String readEtudiant(@RequestParam("idEtudiant") int id, Model model) {
		model.addAttribute("etudiant", ietudiantservice.getById(id));
		return "detailEtudiant";
	}

	/**
	 * redirige vers la vue rechercheModificationEtudiant.jsp pour la modification
	 * d'un étudiant
	 * 
	 * @return
	 */
	@ApiOperation(value = "Recherche un �tudiant pour modifier celui-ci")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getFormModifierEtudiant")
	public String getFormModifierEtudiant() {
		return "rechercheModificationEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() récupère l'étudiant en
	 * question. Si l'étudiant existe, il est affiché dans la vue
	 * modificationEtudiant.jsp
	 * 
	 * @param id
	 * @param model
	 * @return
	 */

	@ApiOperation(value = "Lire un �tudiant modifi�")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/readUpdateEtudiant")
	public String readUpdateEtudiant(@RequestParam("idEtudiant") int id, Model model) {
		model.addAttribute("etudiant", ietudiantservice.getById(id));
		
		return "modificationEtudiant";
	}

	/**
	 * l'utilisateur va pouvoir changer les informations affiché de l'étudiant une
	 * fois envoyées et valides, l'utilisateur sera envoyer vers la vue
	 * listeEtudiants.jsp
	 * 
	 * @param idEtudiant
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param dateNaissance
	 * @param etudiant
	 * @param modelMap
	 * @return
	 */

	@ApiOperation(value = "Modifier un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/udpateEtudiant") // it only support port method
	public String udpateEtudiant(@RequestParam("idEtudiant") int idEtudiant, @RequestParam("nom") String nom,
			@RequestParam("prenom") String prenom, @RequestParam("telephone") int telephone,
			@RequestParam("adresse") String adresse, @RequestParam("mail") String mail,
			@RequestParam("dateNaissance") String dateNaissance,  ModelMap modelMap) {
		Etudiant etudiant = new Etudiant();
		etudiant.setId(idEtudiant);
		etudiant.setNom(nom);
		etudiant.setPrenom(prenom);
		etudiant.setAdresse(adresse);
		etudiant.setTelephone(telephone);
		etudiant.setDateNaissance(dateNaissance);
		ietudiantservice.saveStudent(etudiant);
		return "redirect:/"+LISTE_ETU;
	}

	/**
	 * redirige vers la vue rechercheModificationEtudiant.jsp pour la suppression
	 * d'un étudiant
	 * 
	 * @return
	 */

	@ApiOperation(value = "Rechercher un �tudiant pour supprimer celui-ci")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getFormSupprimerEtudiant")
	public String getFormSupprimerEtudiant() {
		return "suppressionEtudiant";
	}

	/**
	 * En saisissant l'id d'un étudiant, la méthode getById() supprime l'étudiant en
	 * question. Si l'étudiant existe, la vue messageSuppression.jsp est affichée.
	 * 
	 * @param idEtudiant
	 * @param modelMap
	 * @return
	 */
	@ApiOperation(value = "Supprimer un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/deleteEtudiant") // it only support port method
	public String deleteEtudiant(@RequestParam("idEtudiant") int idEtudiant, ModelMap modelMap) {
		
		ietudiantservice.deleteStudent(ietudiantservice.getById(idEtudiant));
		return "messageSuppression"; // welcome is view name. It will call welcome.jsp
	}
	
	/**
	 * redirige vers la vue rechercheNoterEtudiant.jsp pour noter
	 * un étudiant
	 * 
	 * @return
	 */
	@ApiOperation(value = "Recherche un �tudiant pour noter celui-ci")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("getFormNoterEtudiant")
	public String getFormNoterEtudiant() {
		return "noterEtudiant";
	}
	
	/**
	 * l'utilisateur va pouvoir noter un étudiant
	 * 
	 * @param idEtudiant
	 * @param note
	 * @return
	 */

	@ApiOperation(value = "Noter un �tudiant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/noterEtudiant") // it only support port method
	public String noterEtudiant(@RequestParam("idEtudiant") int idEtudiant, @RequestParam("idEnseignant") int idEnseignant, 
			@RequestParam("note") int note,
			ModelMap modelMap) {
		Note noteEtudiant = new Note();
		noteEtudiant.setNote((double) note);
		noteEtudiant.setIdEtu(idEtudiant);
		noteEtudiant.setIdEns(idEnseignant);
		ienseignantservice.noterEtudiant(noteEtudiant);
		return "redirect:/accueil";
	}
}
