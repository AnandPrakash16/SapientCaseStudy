package org.ecom.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.ecom.dao.CategoryDao;
import org.ecom.dao.ProductDao;
import org.ecom.entity.Category;
import org.ecom.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EcomService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	//Extract the data from db and cache it for faster serach
	List<Product> productList = new ArrayList<>();
	List<Category> categoryList =new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron="0 0 0/2 * * ?")
	public void cacheData() {
		productList = (List<Product>) productDao.findAll();
		categoryList = (List<Category>) categoryDao.findAll();
	}
	
	
	@PostConstruct
	public void loadData() {
		
		List<Category> cList = new ArrayList<Category>();
		cList.add(new Category("Shirt",Calendar.getInstance().getTime(),1));
		cList.add(new Category("Trouser",Calendar.getInstance().getTime(),2));
		cList.add(new Category("Jeans",Calendar.getInstance().getTime(),3));
		cList.add(new Category("Socks",Calendar.getInstance().getTime(),4));
		cList.add(new Category("Cap",Calendar.getInstance().getTime(),5));
		categoryDao.saveAll(cList);
		
		List<Product> pList = new ArrayList<>();
		pList.add(new Product("SH123","Black","M","Shirt", "Black Shirt with two pockets",25,350,Calendar.getInstance().getTime(),1,"LEVIS"));
		pList.add(new Product("TR345","White","S","Trouser", "Trouser with two pockets",20,2250,Calendar.getInstance().getTime(),2,"PEOPLE"));
		pList.add(new Product("SH124","Red","L","Shirt", "Red Shirt with short sleeves",10,2100,Calendar.getInstance().getTime(),1,"PEOPLE"));
		pList.add(new Product("J234","Green","L","Jeans", "Slim fit Jeans",100,3567,Calendar.getInstance().getTime(),3,"WROGN"));
		pList.add(new Product("C888","Yellow","M","Cap", "Green cap",34,370,Calendar.getInstance().getTime(),5,"ADIDAS"));
		pList.add(new Product("SO111","Green","S","Socks", "Green Socks",10,357,Calendar.getInstance().getTime(),4,"Nike"));
		pList.add(new Product("J256","White","XL","Jeans", "Slim fit XL Jeans",50,4500,Calendar.getInstance().getTime(),3,"PUMA"));
		productDao.saveAll(pList);
	}


	/**
	 * Method to search the product
	 * @param catType
	 * @param searchValue
	 * @return
	 */
	public List<Product> serachProduct(String catType,String searchValue){
		List<Product> allProduct = null;
		try {
		if (catType.equalsIgnoreCase("Brand")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream()
						.filter(p -> p.getBrandName().equalsIgnoreCase(searchValue)).collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findByBrand(searchValue);
			}
		}
		if (catType.equalsIgnoreCase("Color")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream()
						.filter(p -> p.getColor().equalsIgnoreCase(searchValue)).collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findByColor(searchValue);
			}
		}
		if (catType.equalsIgnoreCase("SKU")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream().filter(p -> p.getSKU().equalsIgnoreCase(searchValue))
						.collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findBySKU(searchValue);
			}
		}
		if (catType.equalsIgnoreCase("Size")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream()
						.filter(p -> p.getSize().equalsIgnoreCase(searchValue)).collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findBySize(searchValue);
			}

		}
		if (catType.equalsIgnoreCase("Price")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream()
						.filter(p -> p.getPrice() == Double.parseDouble(searchValue)).collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findByPrice(Double.parseDouble(searchValue));
			}
		}
		if (catType.equalsIgnoreCase("CategoryId")) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList.stream()
						.filter(p -> p.getCatId() == Integer.parseInt(searchValue)).collect(Collectors.toList());
			} else {
				allProduct = (List<Product>) productDao.findByCatId(searchValue);
			}
		}
		if (catType.isEmpty() || searchValue.isEmpty()) {
			if (productList != null && productList.size() > 0) {
				allProduct = productList;
			} else {
				allProduct = (List<Product>) productDao.findAll();
			}
		}
		}catch(Exception ex) {
			System.out.println("Exception occues while searching.. Reason :"+ex.getMessage());
		}
		return allProduct;
	}
	/**
	 * @return the productList
	 */
	public List<Product> getProductList() {
		return productList;
	}


	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	/**
	 * @return the categoryList
	 */
	public List<Category> getCategoryList() {
		return categoryList;
	}


	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	
	
}
