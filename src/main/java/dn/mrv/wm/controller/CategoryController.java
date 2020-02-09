package dn.mrv.wm.controller;

import dn.mrv.wm.model.Category;
import dn.mrv.wm.service.CategoryService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    /**
     * Get all category
     * @return list category (empty if size == 0)
     */
    @GetMapping
    public List<Category> getCategory(){
        logger.info("Get All category");
        return categoryService.findAll();
    }

    /**
     * Get category by category Id
     * @param id category id
     * @return category found + http_ok OR http_not_found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        Optional<Category> category = categoryService.findById(id);
        return (!category.isPresent()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    /**
     * Create new category
     * @param entity category
     * @return category created + http_ok OR http_bad_request (duplicate id, ...)
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category entity) {
        Category category = categoryService.create(entity);
        return (category == null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Update category
     * @param entity category
     * @return category updated + http_ok OR http_not_found
     */
    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category entity) {
        Category category = categoryService.update(entity);
        return (category == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Delete category
     * @param id category id
     * @return http_ok OR http_not_found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable long id) {
        //todo validate constraint

        return (!categoryService.delete(id)) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find category by name OR description OR name + description
     * @param name category name
     * @param desc category description
     * @return category list + http_ok OR http_not_found
     */
    @GetMapping("/findCategory")
    public ResponseEntity<List<Category>> findCategory(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "description", required = false) String desc) {
        List<Category> category = Collections.emptyList();
        if ((name != null) && (desc != null)) {
                category = categoryService.findByNameLikeOrDescriptionLikeOrderByNameAscIdAsc("%" + name + "%", "%" + desc + "%");
        }
        else {
            if (name != null) {
                category = categoryService.findByNameLikeOrderByNameAsc("%" + name + "%");
            }
            if (desc != null) {
                category = categoryService.findByDescriptionLikeOrderByIdAsc("%" + desc + "%");
            }
        }
        return (category.isEmpty()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(category, HttpStatus.OK);
    }

}
