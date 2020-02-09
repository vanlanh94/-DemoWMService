package dn.mrv.wm.service.impl;

import dn.mrv.wm.model.Supplier;
import dn.mrv.wm.repository.SupplierRepository;
import dn.mrv.wm.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Define service, call repository
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findById(long id) {
        return supplierRepository.findById(id);
    }

    @Override
    @Transactional
    public Supplier create(Supplier entity) {
        if (entity.getId() != 0) return null;
        return supplierRepository.save(entity);
    }

    @Override
    @Transactional
    public Supplier update(Supplier entity) {
        Optional<Supplier> supplier = supplierRepository.findById(entity.getId());
        if (!supplier.isPresent()) return null;     // todo: handle exception
        return supplierRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (!supplier.isPresent()) return false;
        supplierRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Supplier> findByNameLikeOrderByNameAsc(String name) {
        return supplierRepository.findByNameLikeOrderByNameAsc(name);
    }

    @Override
    public List<Supplier> findByAddressLikeOrCityLikeOrderByIdAsc(String address, String city) {
        return supplierRepository.findByAddressLikeOrCityLikeOrderByIdAsc(address, city);
    }

    @Override
    public List<Supplier> findByNameLikeOrAddressLikeOrCityLikeOrderByIdAsc(String name, String address, String city) {
        return supplierRepository.findByNameLikeOrAddressLikeOrCityLikeOrderByIdAsc(name, address, city);
    }
}
