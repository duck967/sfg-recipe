package guru.springframework.sfgrecipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgrecipe.controllers.domain.Category;
import guru.springframework.sfgrecipe.controllers.domain.UnitOfMeasure;
import guru.springframework.sfgrecipe.repositories.CategoryRepository;
import guru.springframework.sfgrecipe.repositories.RecipeRepository;
import guru.springframework.sfgrecipe.repositories.UnitOfMeasureRepository;
import guru.springframework.sfgrecipe.services.RecipeServiceImpl;

@Controller
public class IndexController {
    
    // private CategoryRepository categoryRepository;
    // private UnitOfMeasureRepository unitOfMeasureRepository;
    // private RecipeRepository recipeRepository;

    private final RecipeServiceImpl recipeServiceImpl;


    public IndexController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }


    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {

        // Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        // Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        
        // System.out.println(String.format("Cat Id is: %d", categoryOptional.get().getId()));
        // System.out.println(String.format("Unit of measure Id is: %d", unitOfMeasureOptional.get().getId()));

        //model.addAttribute("recipes", recipeRepository.findAll());
        model.addAttribute("recipes", recipeServiceImpl.getRecipes());

        return "index";
    }
}
