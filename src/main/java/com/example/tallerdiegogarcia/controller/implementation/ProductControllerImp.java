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

import com.example.tallerdiegogarcia.controller.interfaces.ProductController;
import com.example.tallerdiegogarcia.delegate.interfaces.CategoryDelegate;
import com.example.tallerdiegogarcia.delegate.interfaces.ProductDelegate;
import com.example.tallerdiegogarcia.delegate.interfaces.SubCategoryDelegate;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.validate.ProductValidation;

@Controller
public class ProductControllerImp implements ProductController {
	@Autowired
	ProductDelegate productService;
	@Autowired
	SubCategoryDelegate subCategoryService;
	@Autowired
	CategoryDelegate categoryService;
	

	@GetMapping("/products/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());

		model.addAttribute("subcategories", subCategoryService.findAll());

		return "products/add-product";
	}

	@GetMapping("/products/del/{id}")
	public String deleteProduct(@PathVariable("id") Integer id, Model model) {
		Product product = productService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		productService.delete(product);
		model.addAttribute("products", productService.findAll());
		return "products/index";
	}

	@GetMapping("/products/")
	public String indexProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "products/index";
	}

	@PostMapping("/products/add")
	public String saveProduct( @Validated(ProductValidation.class) @ModelAttribute Product product, BindingResult bindingResult, Model model, @RequestParam(value = "action", required = true) String action) {
		if (!action.equals("Cancel")) {
			if(product.getSellstartdate().after(product.getSellenddate())) {
				bindingResult.addError(new FieldError("product","sellstartdate","La fecha de inicio de venta debe ser menor a la fecha final"));
			}
			if(product.getProductsubcategory()==null) {
				bindingResult.addError(new FieldError("product","productsubcategory","El producto debe estar asociado a una subcategoria"));
			}
			if (bindingResult.hasErrors()) {
				 /*System.out.println(bindingResult.getFieldErrorCount("size"));
				 System.out.println(bindingResult.getFieldErrorCount("weight"));
				 System.out.println(bindingResult.getFieldErrorCount("productnumber"));
				 System.out.println(bindingResult.getFieldErrorCount("sellenddate"));
				 System.out.println(bindingResult.getFieldErrorCount("sellstartdate"));*/
				 model.addAttribute("subcategories", subCategoryService.findAll());
					return "/products/add-product";
				 }
			 productService.addProduct(product);
		}
		return "redirect:/products/";
		
		
	}

	@GetMapping("/products/edit/{id}")
	public String showUpdateProduct(@PathVariable("id") Integer id, Model model) {
		Optional<Product> product = productService.findById(id);
		if (product == null)
			throw new IllegalArgumentException("Invalid product Id:" + id);
		
		model.addAttribute("product", product.get());
		model.addAttribute("subcategories", subCategoryService.findAll());
		return "products/update-product";
	}
	
	@GetMapping("/products/associated/{id}")
	public String associatedSub(@PathVariable("id") Integer id, Model model) {	
		model.addAttribute("products", productService.findBySubcategory(id));
		return "products/index";
	}
	
	@GetMapping("/products/orderqtyquery")
	public String customQueryOrderQty(Model model) {	
		model.addAttribute("products", productService.findByWorkorderQuantity());
		return "products/index";
	}
	
	@GetMapping("/products/show/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Product p = productService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
		List<Product> prods = new ArrayList<Product>();
		prods.add(p);
		model.addAttribute("products",prods);
		return "products/index";
	}
	
	

	@PostMapping("/products/edit/{id}")
	public String updateProduct(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
			@Validated(ProductValidation.class) @ModelAttribute Product product,BindingResult bindingResult , Model model) {
		if (action != null && !action.equals("Cancel")) {
			if(product.getSellstartdate().after(product.getSellenddate())) {
				bindingResult.addError(new FieldError("product","sellstartdate","La fecha de inicio de venta debe ser menor a la fecha final"));
			}
			if(product.getProductsubcategory()==null) {
				bindingResult.addError(new FieldError("product","productsubcategory","El producto debe estar asociado a una subcategoria"));
			}
			 if (bindingResult.hasErrors()) {
				model.addAttribute("product", product);
				product.setProductid(id);
				model.addAttribute("subcategories", subCategoryService.findAll());
				return "products/update-product";
			 }else {
				product.setProductid(id);
				productService.editProduct(product);	
			 }
			model.addAttribute("products", productService.findAll());	
		}
		
		return "redirect:/products/";
	}
	
}
