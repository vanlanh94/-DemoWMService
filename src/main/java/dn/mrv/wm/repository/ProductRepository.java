package dn.mrv.wm.repository;

import dn.mrv.wm.model.Product;

/**
 * Extends Jpa repo & declare custom query
 */
public interface ProductRepository extends MyBaseRepository<Product, Long>  {
}
