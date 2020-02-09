package dn.mrv.wm.controller;

import dn.mrv.wm.model.Supplier;
import dn.mrv.wm.service.SupplierService;
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
@RequestMapping("/api/supplier")
public class SupplierController {

    private static final Logger logger = LogManager.getLogger(SupplierController.class);

    @Autowired
    SupplierService supplierService;

    /**
     * Get all supplier
     * @return list supplier (empty if size == 0)
     */
    @GetMapping
    public List<Supplier> getSupplier(){
        return supplierService.findAll();
    }

    /**
     * Get Supplier by Supplier Id
     * @param id Supplier id
     * @return Supplier found + http_ok OR http_not_found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable long id) {
        Optional<Supplier> Supplier = supplierService.findById(id);
        return (!Supplier.isPresent()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(Supplier.get(), HttpStatus.OK);
    }

    /**
     * Create new Supplier
     * @param entity Supplier
     * @return Supplier created + http_ok OR http_bad_request (duplicate id, ...)
     */
    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier entity) {
        Supplier Supplier = supplierService.create(entity);
        return (Supplier == null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(Supplier, HttpStatus.OK);
    }

    /**
     * Update Supplier
     * @param entity Supplier
     * @return Supplier updated + http_ok OR http_not_found
     */
    @PutMapping
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier entity) {
        Supplier Supplier = supplierService.update(entity);
        return (Supplier == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(Supplier, HttpStatus.OK);
    }

    /**
     * Delete Supplier
     * @param id Supplier id
     * @return http_ok OR http_not_found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> deleteSupplier(@PathVariable long id) {
        //todo validate constraint

        return (!supplierService.delete(id)) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find supplier by Name Or Address-City Or Name + Address-City
     * @param name suppliers name
     * @param address suppliers address
     * @param city suppliers city
     * @return suppliers list + http_ok OR http_not_found
     */
    @GetMapping("/findSupplier")
    public ResponseEntity<List<Supplier>> findSupplier(@RequestParam(value = "name", required = false) String name,
                                                       @RequestParam(value = "address", required = false) String address,
                                                       @RequestParam(value = "city", required = false) String city) {
        List<Supplier> suppliers = Collections.emptyList();
        if ((name != null) && (address != null) && (city != null)) {
            suppliers = supplierService.findByNameLikeOrAddressLikeOrCityLikeOrderByIdAsc(
                    "%" + name + "%", "%" + address + "%", "%" + city + "%");
        }
        else {
            if (name != null) {
                suppliers = supplierService.findByNameLikeOrderByNameAsc("%" + name + "%");
            }
            if (address != null && city != null) {
                suppliers = supplierService.findByAddressLikeOrCityLikeOrderByIdAsc(
                        "%" + address + "%", "%" + city + "%");
            }
        }
        return (suppliers.isEmpty()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(suppliers, HttpStatus.OK);
    }
}
