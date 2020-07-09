package eu.ensup.gestionscolairespringboot.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

	@Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/").setViewName("getFormLogin");
	    registry.addViewController("/registerOK").setViewName("getFormLogin");
	    registry.addViewController("/getFormLogin").setViewName("accueil");
	    registry.addViewController("/getFormAjoutEtudiant").setViewName("ajouterEtudiant");
	  }
}
