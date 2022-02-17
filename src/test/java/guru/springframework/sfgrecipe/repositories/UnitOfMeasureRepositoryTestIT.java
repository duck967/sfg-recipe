package guru.springframework.sfgrecipe.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import guru.springframework.sfgrecipe.controllers.domain.UnitOfMeasure;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTestIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    void testFindByDescription() {
        Optional<UnitOfMeasure> umOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", umOptional.get().getDescription());
    }

    @Test
    void testFindByDescriptionCup() {
        Optional<UnitOfMeasure> umOptional = unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", umOptional.get().getDescription());
    }
}
