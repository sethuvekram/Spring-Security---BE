package com.App.service;

import com.App.model.Products;
import com.App.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Products addProduct(Map<String,String> products) {
        Products category = Products.builder().name(products.get("name")).description(products.get("description")).price(products.get("price")).category(products.get("category")).build();
        return productRepository.save(category);
    }

    public Products updateProduct(Products products) {
        Optional<Products> category = productRepository.findById(products.getId());
        Products products1 = category.get();
        products1.setCategory(products.getCategory());
        products1.setName(products.getName());
        products1.setPrice(products.getPrice());
        products1.setDescription(products.getDescription());

        return productRepository.save(products1);

    }







//        public Products updateProduct(SingularAttribute<AbstractPersistable, Serializable> id, Map<String, String> updates) {
//            // Get the product to update
//            Optional<Products> productOptional = categoryRepository.findById(id);
//            if (productOptional.isEmpty()) {
//                throw new EntityNotFoundException("Product not found with id: " + id);
//            }
//            Products product = productOptional.get();
//
//            // Update the fields
//            if (updates.containsKey("name")) {
//                product.setName(updates.get("name"));
//            }
//            if (updates.containsKey("description")) {
//                product.setDescription(updates.get("description"));
//            }
//            if (updates.containsKey("price")) {
//                product.setPrice(String.valueOf(Double.parseDouble(updates.get("price"))));
//            }
//            if (updates.containsKey("category")) {
//                product.setCategory(updates.get("category"));
//            }
//
//            // Save the updated product
//            return categoryRepository.save(product);
//        }


    public String deleteProduct(int id) {
        productRepository.deleteById(id);
        return id + "deleted";
    }

    public List<Products> getAllProducts() {
        return productRepository.findAll();

    }
    public Optional<Products> getProductById(int id){
        return productRepository.findById(id);
    }

    public List<Products> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

}

