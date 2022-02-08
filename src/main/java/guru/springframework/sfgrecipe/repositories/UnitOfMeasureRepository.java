package guru.springframework.sfgrecipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgrecipe.controllers.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    
}
