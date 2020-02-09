package dn.mrv.wm.repository;

import dn.mrv.wm.model.Invoice;

/**
 * Extends Jpa repo & declare custom query
 */
public interface InvoiceRepository extends MyBaseRepository<Invoice, Long>   {
}
