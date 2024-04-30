package com.dollop.app.service;

import java.util.List;

import com.dollop.app.model.Recipe;
import com.dollop.app.model.User;

public interface IRecipeService {
	
	public Recipe createRecipe(Recipe recipe,User user);
	public Recipe findRecipeByid(Long id) throws Exception;
	public void deleteRecipe(Long id) throws Exception;
	public Recipe updateRecipe(Recipe recipe,Long id) throws Exception;
	public List<Recipe>findAllRecipe();
	public Recipe likeRecipe(Long recipeId, User user) throws Exception;
}
