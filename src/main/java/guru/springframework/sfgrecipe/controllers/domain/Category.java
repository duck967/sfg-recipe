package guru.springframework.sfgrecipe.controllers.domain;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@EqualsAndHashCode(exclude = {"recipies"})
@Entity
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipies;

}
