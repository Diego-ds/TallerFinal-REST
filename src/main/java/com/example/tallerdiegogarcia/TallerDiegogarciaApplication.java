package com.example.tallerdiegogarcia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.example.tallerdiegogarcia.model.Employee;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.model.UserType;
import com.example.tallerdiegogarcia.model.UserWeb;
import com.example.tallerdiegogarcia.repositories.EmployeeRepository;
import com.example.tallerdiegogarcia.repositories.ProductCategoryRepository;
import com.example.tallerdiegogarcia.repositories.ProductRepository;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;
import com.example.tallerdiegogarcia.repositories.UserRepository;


@SpringBootApplication
public class TallerDiegogarciaApplication {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}
	public static void main(String[] args) {
		SpringApplication.run(TallerDiegogarciaApplication.class, args);
	}
	
	@Bean
	public RestTemplate getTemplate() {
		RestTemplate template = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		template.setMessageConverters(messageConverters);
		return template;
	}

	@Bean
	public CommandLineRunner dummy(ProductRepository repo,ProductSubCategoryRepository subcat,
			ProductCategoryRepository cat,UserRepository userRepository, EmployeeRepository empRepo) {

		return (args) -> {
			//Entities for testing
			UserWeb user1 = new UserWeb();
			user1.setPassword("{noop}admin");
			user1.setUsername("admin");
			user1.setType(UserType.ADMIN);
			UserWeb user2 = new UserWeb();
			user2.setPassword("{noop}operator");
			user2.setUsername("operator");
			user2.setType(UserType.OPERATOR);
			userRepository.save(user1);
			userRepository.save(user2);
			Productcategory c = new Productcategory();
			c.setName("Lacteos");
			cat.save(c);
			Productcategory c2 = new Productcategory();
			c2.setName("Aseo");
			cat.save(c2);
			Productsubcategory s = new Productsubcategory();
			s.setName("Yougurts");
			s.setProductcategory(c);
			subcat.save(s);
			Product p = new Product();
			p.setName("Colanta fresa");
			p.setSize(10);
			p.setWeight(5);
			p.setSellstartdate(LocalDate.of(2022, 04, 01));
			p.setSellenddate(LocalDate.of(2022, 05, 15));
			p.setProductnumber("15");
			p.setProductsubcategory(s);
			repo.save(p);
			Employee emp1 = new Employee();
			emp1.setLoginid("Alejandro");
			empRepo.save(emp1);
			Employee emp2 = new Employee();
			emp2.setLoginid("Diego");
			empRepo.save(emp2);
			Employee emp3 = new Employee();
			emp3.setLoginid("Romulo");
			empRepo.save(emp3);
		};
	}
}
