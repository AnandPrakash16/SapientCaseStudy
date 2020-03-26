package org.ecom.controller;

import java.util.List;

import org.ecom.dao.CategoryDao;
import org.ecom.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {


	@Autowired
	CategoryDao categoryDao;
	
	@GetMapping("/findById/{catId}")
	@ResponseBody
    public List<Category> findBySKU(@PathVariable int catId) {
			List<Category> list = (List<Category>) categoryDao.findByCatId(catId);
			return list;
    }
	
}
