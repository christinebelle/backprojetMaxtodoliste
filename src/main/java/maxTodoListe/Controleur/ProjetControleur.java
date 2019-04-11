package maxTodoListe.Controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import maxTodoListe.Entity.ProjetListe;
import maxTodoListe.Repository.ProjetRepository;

@RestController
@CrossOrigin("*")
public class ProjetControleur {
	
	@Autowired
	private ProjetRepository projetRepo;
	
	/**
	 * obtenir toute la ToDo liste
	 * @return maxListes
	 */
	@GetMapping("/liste")
	public ResponseEntity<?> projets() {		
		List<ProjetListe> projetListes = null;
		projetListes = projetRepo.findAll();		
		return ResponseEntity.status(HttpStatus.OK).body(projetListes);		
	}
	
	
	/**
	 * effacer un ToDo de la liste par son ID
	 * @param idListe
	 * @return null
	 */
	@DeleteMapping("/supprime/{idListe}")
	public void deleteprojet(@PathVariable Integer idListe) {
		projetRepo.deleteById(idListe);		
	}
	
	
	/**
	 * ajouter un ToDo à la liste 
	 * @param liste
	 * @return newListe
	 */
	@RequestMapping(value ="/ajouter", method = RequestMethod.POST )
	public ResponseEntity<?> ajoutprojet(@RequestBody ProjetListe liste) {
		
		ProjetListe newListe = null;
		newListe = projetRepo.save(liste);
		return ResponseEntity.status(HttpStatus.CREATED).body(newListe);		
	}
	
	/**
	 * modifie un ToDo à la liste
	 * @param liste
	 * @param idListe
	 * @return newListe
	 */
	@PutMapping("/modifier")
	public ResponseEntity<?> modifierprojet(@RequestBody ProjetListe liste) {
		ProjetListe newListe = null;
		newListe = projetRepo.save(liste);
		return ResponseEntity.status(HttpStatus.CREATED).body(newListe);		
	}
}
