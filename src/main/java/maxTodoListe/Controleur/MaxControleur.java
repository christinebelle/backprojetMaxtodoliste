package maxTodoListe.Controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import maxTodoListe.Liste.MaxListe;
import maxTodoListe.Repository.MaxListeRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class MaxControleur {
	
	@Autowired
	private MaxListeRepository maxRepo;
	
	/**
	 * obtenir toute la ToDo liste
	 * @return maxListes
	 */
	@RequestMapping(value ="/liste", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> laListe() {
		
		List<MaxListe> maxListes = null;
		maxListes = (List<MaxListe>) maxRepo.findAll();		
		return ResponseEntity.status(HttpStatus.OK).body(maxListes);		
	}
	
	
	/**
	 * effacer un ToDo de la liste par son ID
	 * @param idListe
	 * @return null
	 */
	@RequestMapping(value ="/supprime/{idListe}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteListe(@PathVariable Integer idListe) {
		
		maxRepo.deleteById(idListe);
		return ResponseEntity.status(HttpStatus.OK).body(null);		
	}
	
	
	/**
	 * ajouter un ToDo à la liste 
	 * @param liste
	 * @return newListe
	 */
	@RequestMapping(value ="/ajouter", method = RequestMethod.POST )
	public ResponseEntity<?> ajoutListe(@RequestBody MaxListe liste) {
		
		MaxListe newListe = null;
		newListe = maxRepo.save(liste);
		return ResponseEntity.status(HttpStatus.CREATED).body(newListe);		
	}
	
	/**
	 * modifie un ToDo à la liste
	 * @param liste
	 * @param idListe
	 * @return newListe
	 */
	@RequestMapping(value="/ajouter/{idListe}", method = RequestMethod.PUT)
	public ResponseEntity<?> modifierListe(@RequestBody MaxListe liste, @PathVariable Integer idListe) {
		
		MaxListe newListe = null;
		newListe = maxRepo.save(liste);
		return ResponseEntity.status(HttpStatus.OK).body(newListe);		
	}
}
