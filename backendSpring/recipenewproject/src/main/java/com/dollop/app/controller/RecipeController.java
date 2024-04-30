package com.dollop.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.model.Recipe;
import com.dollop.app.model.User;
import com.dollop.app.service.IRecipeService;
import com.dollop.app.service.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/recipies")
public class RecipeController {
	@Autowired
	private IRecipeService recipeService;
	
	@Autowired
	private IUserService userService;
	
	
	@PostMapping()
	public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Recipe createdRecipe = recipeService.createRecipe(recipe, user);
		return createdRecipe;
	}
	
	@PutMapping("/{id}")
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) throws Exception {
		Recipe updatedRecipe = recipeService.updateRecipe(recipe, id);
		return updatedRecipe;
	}
	
	@GetMapping("/api/all")
	public List<Recipe>getAllRecipe(){
		System.err.println("Hello");
		List<Recipe> recipe = recipeService.findAllRecipe();
		return recipe;
	}
	
	@DeleteMapping("/{recipeId}")
	public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
		recipeService.deleteRecipe(recipeId);
		return "recipe deleted successfully";
	}
	
	@PutMapping("/{id}/like")
	public Recipe likeRecipe(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
		User user = userService.findUserByJwt(jwt);
		Recipe updatedRecipe = recipeService.likeRecipe(id, user);
		return updatedRecipe;
	}

}