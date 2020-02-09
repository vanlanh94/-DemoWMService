package dn.mrv.wm.repository;

import dn.mrv.wm.model.Supplier;

import java.util.List;

/**
 * Extends Jpa repo & declare custom query
 */
public interface SupplierRepository extends MyBaseRepository<Supplier, Long> {
    List<Supplier> findByNameLikeOrderByNameAsc(String name);
    List<Supplier> findByAddressLikeOrCityLikeOrderByIdAsc(String address, String city);
    List<Supplier> findByNameLikeOrAddressLikeOrCityLikeOrderByIdAsc(String name, String address, String city);
}
