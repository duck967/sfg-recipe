package guru.springframework.sfgrecipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgrecipe.controllers.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    
}
