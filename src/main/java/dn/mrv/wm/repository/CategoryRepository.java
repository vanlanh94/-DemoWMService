package dn.mrv.wm.repository;


import dn.mrv.wm.model.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Extends Jpa repo & declare custom query
 */
public interface CategoryRepository extends MyBaseRepository<Category, Long> {

    @Query("SELECT c FROM Category c ORDER BY c.name ASC")
    List<Category> findAll(); //todo add pageable
    List<Category> findByNameLikeOrderByNameAsc(String name);
    List<Category> findByDescriptionLikeOrderByIdAsc(String description);
    List<Category> findByNameLikeOrDescriptionLikeOrderByNameAscIdAsc(String name, String description);
}
