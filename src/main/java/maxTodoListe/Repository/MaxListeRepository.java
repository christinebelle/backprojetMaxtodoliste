package maxTodoListe.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import maxTodoListe.Liste.MaxListe;

@Repository
public interface MaxListeRepository extends CrudRepository<MaxListe, Integer> {

}
