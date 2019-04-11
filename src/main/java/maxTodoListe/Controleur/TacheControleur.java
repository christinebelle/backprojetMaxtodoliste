package maxTodoListe.Controleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import maxTodoListe.Entity.TacheListe;
import maxTodoListe.Repository.TacheRepository;

	

@RestController
@CrossOrigin("*")
public class TacheControleur {
	
	@Autowired
	private TacheRepository tacheRepo;
	
	@GetMapping("/tache")
	public ResponseEntity<?> getTache() {
		
		Iterable<TacheListe> positions = null;
		positions = tacheRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(positions);					
	}
	
	@GetMapping("/AFaire")
	public ResponseEntity<?> getAFaire() {
		
		Iterable<TacheListe> positions = null;
		positions = tacheRepo.findByPositionAFaire();
		return ResponseEntity.status(HttpStatus.OK).body(positions);					
	}
	
	@GetMapping("/EnCour")
	public ResponseEntity<?> getEnCour() {
		
		Iterable<TacheListe> positions = null;
		positions = tacheRepo.findByPositionEnCour();
		return ResponseEntity.status(HttpStatus.OK).body(positions);					
	}
	
	@GetMapping("/Terminer")
	public ResponseEntity<?> getTerminer() {
		
		Iterable<TacheListe> positions = null;
		positions = tacheRepo.findByPositionTerminer();
		return ResponseEntity.status(HttpStatus.OK).body(positions);					
	}
	
	@PostMapping("/ajouterTache")
	private ResponseEntity<?> postPosition(@RequestBody TacheListe tache) {
		
		TacheListe newtache = null;
		newtache = tacheRepo.save(tache);
		return ResponseEntity.status(HttpStatus.CREATED).body(newtache);		
	}
	
	@GetMapping("/modifierTache")
	private ResponseEntity<?> modifierTache(@RequestBody TacheListe tacheListe) {
		
		TacheListe newTache = null;
		newTache = tacheRepo.save(tacheListe);
		return ResponseEntity.status(HttpStatus.CREATED).body(newTache);
	}
	
	@DeleteMapping("/supprime/{idTache}")
	
	public void deleteLivre(@PathVariable Integer idListe) {
		tacheRepo.existsById(idListe);
	}
}
