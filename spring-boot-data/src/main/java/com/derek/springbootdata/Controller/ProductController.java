package com.derek.springbootdata.Controller;

import java.util.List;

//import com.derek.springbootdata.ProductCategory;
import com.derek.springbootdata.entity.Product;
import com.derek.springbootdata.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // @RequestMapping(value = "", method = RequestMethod.GET)
    // public List<ProductCategory> getCategories(){
    //     return productRepository.findAll();
    // }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public List<Product> getProducts(@PathVariable String name){
        return productRepository.findByName(name);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping(value="/{id}", method =RequestMethod.PUT)
    public Product update(@RequestBody Product product){
        return productRepository.save(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        productRepository.deleteById(id);

    }

    
}
