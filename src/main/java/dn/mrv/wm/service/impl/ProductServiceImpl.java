package dn.mrv.wm.service.impl;

import dn.mrv.wm.model.Product;
import dn.mrv.wm.repository.ProductRepository;
import dn.mrv.wm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Define service, call DAO
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public Product create(Product entity) {
        if (entity.getId() != 0) return null;
        return productRepository.save(entity);
    }

    @Override
    @Transactional
    public Product update(Product entity) {
        Optional<Product> product = productRepository.findById(entity.getId());
        if (!product.isPresent()) return null;     // todo: handle exception
        return productRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) return false;
        productRepository.deleteById(id);
        return true;
    }
}
