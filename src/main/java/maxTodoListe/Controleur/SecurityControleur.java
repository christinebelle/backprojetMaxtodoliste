package maxTodoListe.Controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityControleur {
	
	@GetMapping("/max")
	public String userRessource() {
		
		// le return doit renvoyer la page voulu
		return "max.html";		
	}
	
	@GetMapping("/kanban")
	public String getKanban() {
		return "kanban.html";
	}
}
