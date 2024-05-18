package com.example.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.repository.CategoryRepository;
import com.example.entities.CategoryEntity;


@SpringBootApplication
public class CategoryApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CategoryApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		CategoryEntity p1 = new CategoryEntity();
		repo.save(p1);
		CategoryEntity p2 = new CategoryEntity();
		repo.save(p2);
		
		List<CategoryEntity> categorias = repo.findAll();
		System.out.println(categorias);
		
	}

}
