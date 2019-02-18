package maxTodoListe.Controleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import maxTodoListe.Liste.DragDrop;
import maxTodoListe.Repository.DragDropRepository;

	

@RestController
public class DragDropControleur {
	
	@Autowired
	private DragDropRepository dragDropRepo;
	
	@GetMapping("/getPosition")
	public ResponseEntity<?> getPosition() {
		
		List<DragDrop> positions = null;
		positions = (List<DragDrop>) dragDropRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(positions);
			
	}
	

	@PostMapping("/postPosition")
	private ResponseEntity<?> postPosition(@RequestBody DragDrop position) {
		
		DragDrop newPosition = null;
		newPosition = dragDropRepo.save(position);
		return ResponseEntity.status(HttpStatus.CREATED).body(newPosition);
		
	}
	
	@GetMapping("/lastPosition")
	private ResponseEntity<?> lastPosition() {
		
		List<DragDrop> positions = null;
		positions = (List<DragDrop>) dragDropRepo.findByLastPosition();
		return ResponseEntity.status(HttpStatus.OK).body(positions);
	}

}
