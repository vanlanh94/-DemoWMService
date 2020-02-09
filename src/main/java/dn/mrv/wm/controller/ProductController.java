package dn.mrv.wm.controller;


import dn.mrv.wm.model.Product;
import dn.mrv.wm.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    /**
     * Get all product
     * @return list product (empty if size == 0)
     */
    @GetMapping
    public List<Product> getProduct(){
        return productService.findAll();
    }

    /**
     * Get product by product Id
     * @param id product id
     * @return product found + http_ok OR http_not_found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Optional<Product> product = productService.findById(id);
        return (!product.isPresent()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    /**
     * Create new product
     * @param entity product
     * @return product created + http_ok OR http_bad_request (duplicate id, id > 0 ...)
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product entity) {
        Product product = productService.create(entity);
        return (product == null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Update product
     * @param entity product
     * @return product updated + http_ok OR http_not_found
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product entity) {
        Product product = productService.update(entity);
        return (product == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Delete product
     * @param id product id
     * @return http_ok OR http_not_found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id) {
        //todo validate constraint

        return (!productService.delete(id)) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}
