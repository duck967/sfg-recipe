package guru.springframework.sfgrecipe.bootstrap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import guru.springframework.sfgrecipe.controllers.domain.Difficulty;
import guru.springframework.sfgrecipe.controllers.domain.Ingredient;
import guru.springframework.sfgrecipe.controllers.domain.Notes;
import guru.springframework.sfgrecipe.controllers.domain.Recipe;
import guru.springframework.sfgrecipe.repositories.CategoryRepository;
import guru.springframework.sfgrecipe.repositories.RecipeRepository;
import guru.springframework.sfgrecipe.repositories.UnitOfMeasureRepository;

@Component
public class DataLoader  implements CommandLineRunner {

    // private Difficulty difficulty;
    // private Ingredient ingredient;
    // private Notes notes;
    // private Recipe recipe;

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
            RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("dataloader starting ...");

        unitOfMeasureRepository.findByDescription("Teaspoon").orElseThrow(() -> new Exception("Ingredient not found - Teaspoon"));
        // ...

        
        List<Ingredient> ingredientsGuacamole = new ArrayList<Ingredient>();
        ingredientsGuacamole.add(ingredientFactory("Avocado", "2", "Quantity"));
        ingredientsGuacamole.add(ingredientFactory("Salt", ".25", "Teaspoon"));
        ingredientsGuacamole.add(ingredientFactory("Lime Juice", "1", "Tablespoon"));
        ingredientsGuacamole.add(ingredientFactory("Minced Red Onion", "3", "Tablespoon"));
        ingredientsGuacamole.add(ingredientFactory("serrano chili", "2", "Quantity"));
        ingredientsGuacamole.add(ingredientFactory("cilantro", "2", "Tablespoon"));
        ingredientsGuacamole.add(ingredientFactory("ground black pepper", "1", "Pinch"));
        ingredientsGuacamole.add(ingredientFactory("ripe tomato", ".5", "Quantity"));
        ingredientsGuacamole.add(ingredientFactory("Red Radish", "10", "Slices"));
        ingredientsGuacamole.add(ingredientFactory("Tortilla Chips", "12", "Quantity"));

        Notes recipeGuacamoleNotes = new Notes();
        recipeGuacamoleNotes.setRecipeNotes("recipeNotes");

        Recipe recipeGuacamole = new Recipe();
        recipeGuacamole.setDescription("Guacamole");
        recipeGuacamole.setDifficulty(Difficulty.EASY);
        recipeGuacamole.setNotes(recipeGuacamoleNotes);
        recipeGuacamole.setPrepTime(20);
        recipeGuacamole.setServings(4);
        recipeGuacamole.setSource("Simply Recipes");
        recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Byte[] guacamoleImageBytes = getImageFileBytes("static/images/guacamole.jpg");
        recipeGuacamole.setImage(guacamoleImageBytes);

        recipeGuacamoleNotes.setRecipe(recipeGuacamole);

        for (Ingredient ingredient : ingredientsGuacamole) {
            recipeGuacamole.getIngredients().add(ingredient);
            ingredient.setRecipe(recipeGuacamole);
        }
        Recipe savedRecipeGuacamole = recipeRepository.save(recipeGuacamole);

        //
        //
        //

        List<Ingredient> ingredientsSpicyTacos = new ArrayList<Ingredient>();
        ingredientsSpicyTacos.add(ingredientFactory("Avocado", "2", "Quantity"));

        Notes recipeSpicyTacosNotes = new Notes();
        recipeSpicyTacosNotes.setRecipeNotes("recipeNotes");

        Recipe recipeSpicyTacos = new Recipe();
        recipeSpicyTacos.setDescription("Spicy Grilled Chicken Tacos");
        recipeSpicyTacos.setDifficulty(Difficulty.EASY);
        recipeSpicyTacos.setNotes(recipeSpicyTacosNotes);
        recipeSpicyTacos.setPrepTime(20);
        recipeSpicyTacos.setServings(4);
        recipeSpicyTacos.setSource("Simply Recipes");
        recipeSpicyTacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        Byte[] spicyTacosImageBytes = getImageFileBytes("static/images/guacamole.jpg");
        recipeSpicyTacos.setImage(spicyTacosImageBytes);

        recipeSpicyTacosNotes.setRecipe(recipeSpicyTacos);

        for (Ingredient ingredient : ingredientsSpicyTacos) {
            recipeSpicyTacos.getIngredients().add(ingredient);
            ingredient.setRecipe(recipeSpicyTacos);
        }

        Recipe savedRecipeSpicyTacos = recipeRepository.save(recipeSpicyTacos);

        System.out.println("dataloader ran ...");
        

        
    }

    private Ingredient ingredientFactory(String desc, String amount, String uomStr) {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription(desc);
        ingredient1.setAmount(new BigDecimal(amount));
        ingredient1.setUom(unitOfMeasureRepository.findByDescription(uomStr).get());

        return ingredient1;
    }

    private Byte[] getImageFileBytes(String pathStr) {
        // static/images/guacamole.png
        try {

            File fnew = ResourceUtils.getFile("classpath:" + pathStr);
            byte[] bytes = getRawBytesFromFile(fnew.getAbsolutePath());
            Byte[] imageInByte= toObjects(bytes);
            return imageInByte;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static byte[] getRawBytesFromFile(String path) throws FileNotFoundException, IOException {

        byte[] image;
        File file = new File(path);
        image = new byte[(int)file.length()];

        FileInputStream fileInputStream = new FileInputStream(file);
        fileInputStream.read(image);

        return image;
    }

    Byte[] toObjects(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }
    
}
