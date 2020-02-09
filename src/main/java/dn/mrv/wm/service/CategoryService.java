package dn.mrv.wm.service;

import dn.mrv.wm.model.Category;

import java.util.List;

public interface CategoryService extends BaseService<Category> {
    List<Category> findByNameLikeOrderByNameAsc(String name);
    List<Category> findByDescriptionLikeOrderByIdAsc(String description);
    List<Category> findByNameLikeOrDescriptionLikeOrderByNameAscIdAsc(String name, String description);
}
