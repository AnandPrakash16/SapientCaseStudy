package org.ecom.controller;

import java.util.List;

import org.ecom.dao.ProductDao;
import org.ecom.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	ProductDao productDao;

	@GetMapping("/findBrand/{brand}")
	@ResponseBody
    public List<Product> findByBrand(@PathVariable String brand) {
			List<Product> list = (List<Product>) productDao.findByBrand(brand);
			return list;
    }
	
	@GetMapping("/findPrice/{price}")
	@ResponseBody
    public List<Product> findByPrice(@PathVariable double price) {
			List<Product> list = (List<Product>) productDao.findByPrice(price);
			return list;
    }
	
	@GetMapping("/findColor/{color}")
	@ResponseBody
    public List<Product> findByColor(@PathVariable String color) {
			List<Product> list = (List<Product>) productDao.findByColor(color);
			return list;
    }
	
	@GetMapping("/findSize/{size}")
	@ResponseBody
    public List<Product> findBySize(@PathVariable String size) {
			List<Product> list = (List<Product>) productDao.findBySize(size);
			return list;
    }
	
	@GetMapping("/findSKU/{sku}")
	@ResponseBody
    public List<Product> findBySKU(@PathVariable String sku) {
			List<Product> list = (List<Product>) productDao.findBySKU(sku);
			return list;
    }
	
	@GetMapping("/findall")
	@ResponseBody
    public List<Product> findAll() {
			List<Product> list =  (List<Product>) productDao.findAll();
			return list;
    }
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Product> allProduct = (List<Product>) productDao.findAll();
		model.addAttribute("products",allProduct);
		return "home.html";
	}
}
