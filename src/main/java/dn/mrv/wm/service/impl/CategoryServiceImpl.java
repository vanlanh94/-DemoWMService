package dn.mrv.wm.service.impl;

import dn.mrv.wm.model.*;
import dn.mrv.wm.repository.CategoryRepository;
import dn.mrv.wm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Define service, call repository
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

//    private Pageable createPageRequest() {
//        return new PageRequest(1,
//                10,
//                new Sort(Sort.Direction.ASC, "name")
//                        .and(new Sort(Sort.Direction.ASC, "id"))
//    );
//    }

    @Override
    public Optional<Category> findById(long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public Category create(Category entity) {
        if (entity.getId() != 0) return null;
        return categoryRepository.save(entity);
    }

    @Override
    @Transactional
    public Category update(Category entity) {
        Optional<Category> category = categoryRepository.findById(entity.getId());
        if (!category.isPresent()) return null;     // todo: handle exception
        return categoryRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) return false;
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Category> findByNameLikeOrderByNameAsc(String name) {
        return categoryRepository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public List<Category> findByDescriptionLikeOrderByIdAsc(String description) {
        return categoryRepository.findByDescriptionLikeOrderByIdAsc(description);
    }

    @Override
    public List<Category> findByNameLikeOrDescriptionLikeOrderByNameAscIdAsc(String name, String description) {
        return categoryRepository.findByNameLikeOrDescriptionLikeOrderByNameAscIdAsc(name, description);
    }
}
