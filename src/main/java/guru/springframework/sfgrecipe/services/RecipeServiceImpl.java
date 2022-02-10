package guru.springframework.sfgrecipe.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgrecipe.controllers.domain.Recipe;
import guru.springframework.sfgrecipe.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        
        Set<Recipe> recipes = new HashSet<Recipe>();

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }
    
}
