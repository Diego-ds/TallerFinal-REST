package com.example.tallerdiegogarcia.controller.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tallerdiegogarcia.controller.interfaces.ProductSubCategoryController;
import com.example.tallerdiegogarcia.delegate.interfaces.CategoryDelegate;
import com.example.tallerdiegogarcia.delegate.interfaces.SubCategoryDelegate;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.validate.SubCategoryValidation;

@Controller
public class ProductSubCategoryControllerImp implements ProductSubCategoryController {
	
	@Autowired
	SubCategoryDelegate subCategoryService;
	@Autowired
	CategoryDelegate categoryService;

	@GetMapping("/subcategories/add")
	public String addSubCategory(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("productsubcategory", new Productsubcategory());
		return "subcategories/add-subcategory";
	}

	@GetMapping("/subcategories/del/{id}")
	public String deleteSubCategory(@PathVariable("id") Integer id, Model model) {
		Productsubcategory subcat = subCategoryService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid subcategory Id:" + id));
		try {
			subCategoryService.delete(subcat);
		}catch(IllegalArgumentException e) {
			model.addAttribute("cannotdelete",true);
		}
		model.addAttribute("subcategories", subCategoryService.findAll());
		return "subcategories/index";
	}
	
	@GetMapping("/subcategories/associated/{id}")
	public String associatedCategory(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("subcategories", subCategoryService.findByCategory(id));
		return "subcategories/index";
	}
	
	@GetMapping("/subcategories/show/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Productsubcategory subcat = subCategoryService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid subcategory Id:" + id));
		List<Productsubcategory> subs = new ArrayList<Productsubcategory>();
		subs.add(subcat);
		model.addAttribute("subcategories",subs);
		return "subcategories/index";
	}

	@GetMapping("/subcategories/")
	public String indexProducts(Model model) {
		model.addAttribute("subcategories", subCategoryService.findAll());
		return "subcategories/index";
	}

	@PostMapping("/subcategories/add")
	public String saveSubcategory( @Validated(SubCategoryValidation.class) @ModelAttribute Productsubcategory productsubcategory, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
				 model.addAttribute("categories", categoryService.findAll());
				 return "/subcategories/add-subcategory";
			}
			subCategoryService.addProductSubCategory(productsubcategory);
		}
		return "redirect:/subcategories/";
		
		
	}

	@GetMapping("/subcategories/edit/{id}")
	public String showUpdateSubcategory(@PathVariable("id") Integer id, Model model) {
		Optional<Productsubcategory> subcat = subCategoryService.findById(id);
		if (subcat == null)
			throw new IllegalArgumentException("Invalid product Id:" + id);
		
		model.addAttribute("subcategory", subcat.get());
		model.addAttribute("categories", categoryService.findAll());
		return "subcategories/update-subcategory";
	}

	@PostMapping("/subcategories/edit/{id}")
	public String updateSubCategory(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@Validated(SubCategoryValidation.class) @ModelAttribute Productsubcategory subcat,BindingResult bindingResult , Model model) {
		if (action != null && !action.equals("Cancel")) {
			 if (bindingResult.hasErrors()) {
				model.addAttribute("subcategory", subcat);
				subcat.setProductsubcategoryid(id);
				model.addAttribute("categories", categoryService.findAll());
				return "subcategories/update-subcategory";
			 }else {
				subcat.setProductsubcategoryid(id);
				subCategoryService.editProductSubCategory(subcat);
			 }
			model.addAttribute("subcategories", subCategoryService.findAll());	
		}
		
		return "redirect:/subcategories/";
	}
	
	@GetMapping("/subcategories/custom-query")
	public String customQuery(Model model) {
		model.addAttribute("product",new Product());
		model.addAttribute("categories", categoryService.findAll());
		return "subcategories/input-query";
	}
	
	@PostMapping("/subcategories/custom-query") 
	public String queryInformation(@ModelAttribute Product product, Model model,
			BindingResult bindingResult,@RequestParam(value = "action", required = true) 
	String action) {
		
		if (action != null && !action.equals("Cancel")) {
			if (product.getSellstartdate() != null && product.getSellenddate() != null) {
				if (product.getSellstartdate().after(product.getSellenddate())) {
					bindingResult.addError(new FieldError("product", "sellstartdate",
							"La fecha de venta inicial debe ser menor a la fecha final"));
				}
			} 
			
			List<Productsubcategory> subcategories = new ArrayList<Productsubcategory>();
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("categories", categoryService.findAll());
				return "subcategories/input-query";
			 }else {
					subcategories = subCategoryService.findByCategoryAndDates(
							product.getProductsubcategory().getProductcategory().
							getProductcategoryid(), product.getSellstartdate(), product.getSellenddate());
			 }
			
			model.addAttribute("subcategories", subcategories);	
		}
		
		return "subcategories/index-query";
	}
}
