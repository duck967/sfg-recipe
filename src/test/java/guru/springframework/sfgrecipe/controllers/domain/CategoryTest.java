package guru.springframework.sfgrecipe.controllers.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {
    
    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }
    
    @Test
    void testGetDescription() {
        Long idValue = 4L;

        category.setId(idValue);

        assertEquals(idValue, category.getId());

    }

    @Test
    void testGetId() {

    }

    @Test
    void testGetRecipies() {

    }
}
