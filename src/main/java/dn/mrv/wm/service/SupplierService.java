package dn.mrv.wm.service;

import dn.mrv.wm.model.Supplier;

import java.util.List;

public interface SupplierService extends BaseService<Supplier> {
    List<Supplier> findByNameLikeOrderByNameAsc(String name);
    List<Supplier> findByAddressLikeOrCityLikeOrderByIdAsc(String address, String city);
    List<Supplier> findByNameLikeOrAddressLikeOrCityLikeOrderByIdAsc(String name, String address, String city);
}
