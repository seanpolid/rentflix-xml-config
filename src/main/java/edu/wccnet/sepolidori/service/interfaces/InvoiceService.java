package edu.wccnet.sepolidori.service.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Invoice;

public interface InvoiceService {
	
	public Invoice getInvoice(int invoiceId);
	public List<Invoice> getInvoices();
	public List<Invoice> getInvoices(int customerId);
	public void saveInvoice(Invoice invoice);
	public void deleteInvoices();
	
}
