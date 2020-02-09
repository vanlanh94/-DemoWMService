package dn.mrv.wm.service.impl;

import dn.mrv.wm.model.Invoice;
import dn.mrv.wm.repository.InvoiceRepository;
import dn.mrv.wm.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Define service, call repository
 */
@Service
public class InvoiceServiceImpl implements InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Optional<Invoice> findById(long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public Invoice create(Invoice entity) {
        if (entity.getId() != 0) return null;
        return invoiceRepository.save(entity);
    }

    @Override
    @Transactional
    public Invoice update(Invoice entity) {
        Optional<Invoice> invoice = invoiceRepository.findById(entity.getId());
        if (!invoice.isPresent()) return null;     // todo: handle exception
        return invoiceRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (!invoice.isPresent()) return false;
        invoiceRepository.deleteById(id);
        return true;
    }
}
