package guru.springframework.sfgrecipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgrecipe.controllers.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    
}
