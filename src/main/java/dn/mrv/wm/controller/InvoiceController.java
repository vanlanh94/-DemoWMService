package dn.mrv.wm.controller;

import dn.mrv.wm.model.Invoice;
import dn.mrv.wm.service.InvoiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private static final Logger logger = LogManager.getLogger(InvoiceController.class);

    @Autowired
    InvoiceService invoiceService;

    /**
     * Get all Invoice
     * @return list Invoice (empty if size == 0)
     */
    @GetMapping
    public List<Invoice> getInvoice(){
        return invoiceService.findAll();
    }

    /**
     * Get Invoice by Invoice Id
     * @param id Invoice id
     * @return Invoice found + http_ok OR http_not_found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable long id) {
        Optional<Invoice> invoice = invoiceService.findById(id);
        return (!invoice.isPresent()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(invoice.get(), HttpStatus.OK);
    }

    /**
     * Create new Invoice
     * @param entity Invoice
     * @return Invoice created + http_ok OR http_bad_request (duplicate id, id > 0 ...)
     */
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice entity) {
        Invoice invoice = invoiceService.create(entity);
        return (invoice == null) ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    /**
     * Update Invoice
     * @param entity Invoice
     * @return Invoice updated + http_ok OR http_not_found
     */
    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice entity) {
        Invoice invoice = invoiceService.update(entity);
        return (invoice == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    /**
     * Delete Invoice
     * @param id Invoice id
     * @return http_ok OR http_not_found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable long id) {
        //todo validate constraint

        return (!invoiceService.delete(id)) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}
