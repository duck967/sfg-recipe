package guru.springframework.sfgrecipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgrecipe.controllers.domain.Category;
import guru.springframework.sfgrecipe.controllers.domain.UnitOfMeasure;
import guru.springframework.sfgrecipe.repositories.CategoryRepository;
import guru.springframework.sfgrecipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
    
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        
        System.out.println(String.format("Cat Id is: %d", categoryOptional.get().getId()));
        System.out.println(String.format("Unit of measure Id is: %d", unitOfMeasureOptional.get().getId()));

        return "index";
    }
}
