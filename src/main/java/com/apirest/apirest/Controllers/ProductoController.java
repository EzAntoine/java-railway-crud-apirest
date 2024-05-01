package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Repositories.ProductoRepository;
import com.apirest.apirest.Entities.Producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProducts() {
        
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById (@PathVariable Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found."));
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto newProduct) {
        
        return productoRepository.save(newProduct);
    }

    @PutMapping("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto detailProduct) {
        
        Producto product = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found."));
     
        product.setName(detailProduct.getName());
        product.setPrice(detailProduct.getPrice());

        return productoRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public Producto deleteProduct(@PathVariable Long id) {
        
        Producto product = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found."));
     
        productoRepository.delete(product);
        return product;
    }

    
}
