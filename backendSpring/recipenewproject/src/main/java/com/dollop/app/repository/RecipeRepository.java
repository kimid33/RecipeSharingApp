package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
