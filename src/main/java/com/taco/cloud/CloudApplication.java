package com.taco.cloud;

import com.taco.cloud.data.IngredientRepository;
import com.taco.cloud.data.TacoRepository;
import com.taco.cloud.data.UserRepository;
import com.taco.cloud.models.Ingredient;
import com.taco.cloud.models.Ingredient.Type;
import com.taco.cloud.models.Taco;
import com.taco.cloud.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.logging.Logger;

@SpringBootApplication
public class CloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
		return args -> {
			Ingredient flourTortilla = new Ingredient(
					"FLTO", "Flour Tortilla", Type.WRAP);
			Ingredient cornTortilla = new Ingredient(
					"COTO", "Corn Tortilla", Type.WRAP);
			Ingredient groundBeef = new Ingredient(
					"GRBF", "Ground Beef", Type.PROTEIN);
			Ingredient carnitas = new Ingredient(
					"CARN", "Carnitas", Type.PROTEIN);
			Ingredient tomatoes = new Ingredient(
					"TMTO", "Diced Tomatoes", Type.VEGGIES);
			Ingredient lettuce = new Ingredient(
					"LETC", "Lettuce", Type.VEGGIES);
			Ingredient cheddar = new Ingredient(
					"CHED", "Cheddar", Type.CHEESE);
			Ingredient jack = new Ingredient(
					"JACK", "Monterrey Jack", Type.CHEESE);
			Ingredient salsa = new Ingredient(
					"SLSA", "Salsa", Type.SAUCE);
			Ingredient sourCream = new Ingredient(
					"SRCR", "Sour Cream", Type.SAUCE);
			ingredientRepo.save(flourTortilla);
			ingredientRepo.save(cornTortilla);
			ingredientRepo.save(groundBeef);
			ingredientRepo.save(carnitas);
			ingredientRepo.save(tomatoes);
			ingredientRepo.save(lettuce);
			ingredientRepo.save(cheddar);
			ingredientRepo.save(jack);
			ingredientRepo.save(salsa);
			ingredientRepo.save(sourCream);

			Taco taco1 = new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(Arrays.asList(
					flourTortilla, groundBeef, carnitas,
					sourCream, salsa, cheddar));
			Taco taco2 = new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(Arrays.asList(
					cornTortilla, groundBeef, cheddar,
					jack, sourCream));
			Taco taco3 = new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(Arrays.asList(
					flourTortilla, cornTortilla, tomatoes,
					lettuce, salsa));
			tacoRepo.save(taco1);
			tacoRepo.save(taco2);
			tacoRepo.save(taco3);
		};
	}
}
