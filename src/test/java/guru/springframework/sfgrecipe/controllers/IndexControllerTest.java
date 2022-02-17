package guru.springframework.sfgrecipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import guru.springframework.sfgrecipe.controllers.domain.Recipe;
import guru.springframework.sfgrecipe.repositories.RecipeRepository;
import guru.springframework.sfgrecipe.services.RecipeService;
import guru.springframework.sfgrecipe.services.RecipeServiceImpl;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeServiceImpl recipeService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }
    
    @Test
    void testGetIndexPage() {


        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipeData.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipeData);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String htmlTemplateName = indexController.getIndexPage(model);

        assertEquals(htmlTemplateName, "index");
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

        
    @Test
    void testGetIndexPage2() {

        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet<>();
        recipeData.add(recipe);


        when(recipeService.getRecipes()).thenReturn(recipeData);

        Model model = new ExtendedModelMap();
        String htmlTemplateName = indexController.getIndexPage(model);

        assertEquals(htmlTemplateName, "index");

        Set<Recipe> recipes = (Set<Recipe>)model.getAttribute("recipes");
        //System.out.println(recipes);
        assertEquals(recipes.size(), 1);
    }
}
