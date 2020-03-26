package org.ecom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.ecom.entity.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductDao extends CrudRepository<Product, Integer>{

	@Transactional
	@Query(value = "select * from PRODUCT where SKU = ?1", nativeQuery = true)
	Product  findSingleProductBySKU(String SKU);
	
	@Transactional
	@Query(value = "select * from PRODUCT where BRAND_NAME = ?1", nativeQuery = true)
	List<Product>  findByBrand(String brand);
	
	@Transactional
	@Query(value = "select * from PRODUCT where PRICE = ?1", nativeQuery = true)
	List<Product> findByPrice(double price);
	
	@Transactional
	@Query(value = "select * from PRODUCT where COLOR = ?1", nativeQuery = true)
	List<Product> findByColor(String color);
	
	@Transactional
	@Query(value = "select * from PRODUCT where SIZE = ?1", nativeQuery = true)
	List<Product> findBySize(String size);
	
	@Transactional
	@Query(value = "select * from PRODUCT where SKU = ?1", nativeQuery = true)
	List<Product> findBySKU(String sku);
	
	@Transactional
	@Query(value = "select * from PRODUCT where CAT_ID = ?1", nativeQuery = true)
	List<Product> findByCatId(String CAT_ID);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update PRODUCT SET STOCK= :STOCK WHERE  SKU =:SKU", nativeQuery = true)
	int  buyProduct(String SKU,int STOCK);
	
}
