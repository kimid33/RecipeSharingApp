package com.dollop.app.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.model.Recipe;
import com.dollop.app.model.User;
import com.dollop.app.repository.RecipeRepository;
import com.dollop.app.service.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {
	
	@Autowired
	private RecipeRepository recipeRepository;

	@Override
	public Recipe createRecipe(Recipe recipe, User user) {
		Recipe createdRecipe = new Recipe();
		createdRecipe.setTitle(recipe.getTitle());
		createdRecipe.setImage(recipe.getImage());
		createdRecipe.setDescription(recipe.getDescription());
		createdRecipe.setUser(user);
		createdRecipe.setCreatedAt(LocalDateTime.now());
		return recipeRepository.save(createdRecipe);
	}

	@Override
	public Recipe findRecipeByid(Long id) throws Exception {
		Optional<Recipe> opt = recipeRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new Exception("recipe not found with id "+id);
	}

	@Override
	public void deleteRecipe(Long id) throws Exception {
	findRecipeByid(id);
	recipeRepository.deleteById(id);

	}

	@Override
	public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
		Recipe oldRecipe = findRecipeByid(id);
		if(recipe.getTitle()!=null)
		{
			oldRecipe.setTitle(recipe.getTitle());
		}
		if(recipe.getImage()!=null) {
			oldRecipe.setImage(recipe.getImage());
		}
		if(recipe.getDescription()!=null) {
			oldRecipe.setDescription(recipe.getDescription());
		}
		return recipeRepository.save(oldRecipe);
	}

	@Override
	public List<Recipe> findAllRecipe() {
		return recipeRepository.findAll();
	}

	@Override
	public Recipe likeRecipe(Long recipeId, User user) throws Exception {
	 Recipe recipe = findRecipeByid(recipeId);
	 if(recipe.getLikes().contains(user.getId())) {
		 recipe.getLikes().remove(user.getId());
	 }
	 else {
		 recipe.getLikes().add(user.getId());
	 }
		return recipeRepository.save(recipe);
	}

}
