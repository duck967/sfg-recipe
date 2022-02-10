package guru.springframework.sfgrecipe.services;

import java.util.Set;

import guru.springframework.sfgrecipe.controllers.domain.Recipe;

public interface RecipeService {
    public Set<Recipe> getRecipes();
}
