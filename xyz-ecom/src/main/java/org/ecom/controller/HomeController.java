package org.ecom.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.ecom.dao.CategoryDao;
import org.ecom.dao.ProductDao;
import org.ecom.entity.Category;
import org.ecom.entity.Product;
import org.ecom.form.CategoryForm;
import org.ecom.form.DeleteForm;
import org.ecom.form.NewProductForm;
import org.ecom.form.ProductForm;
import org.ecom.service.EcomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class which provides all operations.
 * @author AnandPrakash
 *
 */
@Controller
public class HomeController {

	@Autowired
	ProductDao productDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	EcomService ecomService;

	/**
	 * Service to load home screen
	 * @param productForm
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String home(@ModelAttribute(name = "productForm") ProductForm productForm, Model model) {
		List<Product> allProduct = ecomService.getProductList();
		List<String> dropDown = populateDropDown();
		model.addAttribute("products", allProduct);
		model.addAttribute("dropDown", dropDown);
		return "home";
	}

	/**
	 * Service to load admin screen
	 * @param productForm
	 * @param model
	 * @return
	 */
	@GetMapping("/admin")
	public String adminHome(@ModelAttribute(name = "productForm") ProductForm productForm, Model model) {
		List<Product> allProduct = ecomService.getProductList();
		List<String> dropDown = populateDropDown();
		model.addAttribute("products", allProduct);
		model.addAttribute("dropDown", dropDown);
		return "adminhome";
	}

	/**
	 * Search product data
	 * @param productForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String showProduct(@ModelAttribute(name = "productForm") ProductForm productForm, Model model) {
		List<String> dropDown = populateDropDown();

		String catType = productForm.getCatType();
		String searchValue = productForm.getSearch();
		List<Product> allProduct = ecomService.serachProduct(catType, searchValue);
		model.addAttribute("products", allProduct);
		model.addAttribute("dropDown", dropDown);
		return "home";
	}

	/**
	 * Service to serve the search functionality on admin screen
	 * @param productForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showadmin", method = RequestMethod.GET)
	public String showProductAdmin(@ModelAttribute(name = "productForm") ProductForm productForm, Model model) {
		List<String> dropDown = populateDropDown();

		String catType = productForm.getCatType();
		String searchValue = productForm.getSearch();
		List<Product> allProduct = ecomService.serachProduct(catType, searchValue);
		model.addAttribute("products", allProduct);
		model.addAttribute("dropDown", dropDown);
		return "adminhome";
	}

	
	/**
	 * Method to open category screen
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/openCategory", method = RequestMethod.GET)
	public String openCategory(Model model) {
		return "category";
	}

	/**
	 * Method to open product screen
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/openProduct", method = RequestMethod.GET)
	public String openProduct(@ModelAttribute(name = "newProductForm") NewProductForm newProductForm, Model model) {
		List<Category> categories = (List<Category>) categoryDao.findAll();
		model.addAttribute("categories", categories);
		return "product";
	}

	/**
	 * Method to open category detail screen
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categoryDetail", method = RequestMethod.GET)
	public String showCategory(Model model) {
		//ecomService.cacheData();
		List<Category> catList = ecomService.getCategoryList();
		model.addAttribute("categories", catList);
		return "categorydetails";
	}
	
	/**
	 * Method to open delete screen
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/openDelete", method = RequestMethod.GET)
	public String openDelete(@ModelAttribute(name = "deleteForm") DeleteForm deleteForm, Model model) {
		//ecomService.cacheData();
		List<Product> allProduct = ecomService.getProductList();
		model.addAttribute("products", allProduct);
		return "delete";
	}

	/**
	 * Method for placing order for a product
	 * @param SKU
	 * @param quantity
	 * @return
	 */
	@RequestMapping(value = "/buyProduct/{SKU}/{quantity}", method = RequestMethod.GET)
	@ResponseBody
	public String buyProduct(@PathVariable(name = "SKU") String SKU, @PathVariable(name = "quantity") int quantity) {
		synchronized (this) {
		List<Product> p = productDao.findBySKU(SKU);
		int stock = p.get(0).getStock();
		if (stock >= quantity) {
			productDao.buyProduct(SKU, stock - quantity);
			ecomService.cacheData();
			p = productDao.findBySKU(SKU);
			stock = p.get(0).getStock();
			return "Order placed successfully. Available Stock :"+stock;
		} else {
			return "Item out of stock";
		}
		}
	}
	
	/**
	 * Service to Delete a product
	 * @param deleteForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@ModelAttribute(name = "deleteForm") DeleteForm deleteForm, Model model) {
		synchronized (this) {
			String id = deleteForm.getId();
			Product p = (Product) productDao.findSingleProductBySKU(id);
			productDao.delete(p);
			ecomService.cacheData();
			model.addAttribute("SKU", id);
		}

		return "deletesuccess";
	}

	/**
	 * Service to add a new category
	 * @param categoryForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute(name = "categoryForm") CategoryForm categoryForm, Model model) {
		synchronized (this) {
		Category cat = new Category();
		cat.setCat_id(categoryForm.getCat_id());
		cat.setLastUpdate(Calendar.getInstance().getTime());
		cat.setName(categoryForm.getName());

		categoryDao.save(cat);
		model.addAttribute("ID", cat.getCat_id());
		ecomService.cacheData();
		}
		return "CategorySuccess";
	}

	/**
	 * Service to add a new product
	 * @param newProductForm
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute(name = "newProductForm") NewProductForm newProductForm, Model model) {
		synchronized (this) {
		Product p = new Product();
		p.setBrandName(newProductForm.getBrandName());
		p.setCatId(Integer.parseInt(newProductForm.getCatId()));
		p.setColor(newProductForm.getColor());
		p.setDescription(newProductForm.getDescription());
		p.setLastUpdate(Calendar.getInstance().getTime());
		p.setName(newProductForm.getName());
		p.setPrice(newProductForm.getPrice());
		p.setSize(newProductForm.getSize());
		p.setSKU(newProductForm.getSKU());
		p.setStock(newProductForm.getStock());

		productDao.save(p);
		model.addAttribute("SKU", p.getSKU());
		ecomService.cacheData();
		}
		return "productSuccess";
	}

	/**
	 * Method to populate category list
	 * @return
	 */
	public  List<String> populateDropDown() {
		List<String> dropDown = new ArrayList<String>();
		dropDown.add("Color");
		dropDown.add("Brand");
		dropDown.add("Price");
		dropDown.add("Size");
		dropDown.add("SKU");
		dropDown.add("CategoryId");
		return dropDown;
	}
}
