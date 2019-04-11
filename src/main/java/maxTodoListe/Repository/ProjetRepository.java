package maxTodoListe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maxTodoListe.Entity.ProjetListe;

@Repository
public interface ProjetRepository extends JpaRepository<ProjetListe, Integer> {

}
