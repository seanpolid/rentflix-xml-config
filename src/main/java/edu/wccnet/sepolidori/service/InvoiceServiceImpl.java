package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Invoice;
import edu.wccnet.sepolidori.exception_handling.InvoiceNotFoundException;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	private final InvoiceService invoiceService;
	
	public InvoiceServiceImpl(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	@Override
	@Transactional
	public Invoice getInvoice(int invoiceId) {
		Invoice invoice = invoiceService.getInvoice(invoiceId);
		if (invoice == null) {
			throw new InvoiceNotFoundException("Invoice " + invoiceId + " not found.");
		}
		return invoice;
	}

	@Override
	@Transactional
	public List<Invoice> getInvoices() {
		return invoiceService.getInvoices();
	}

	@Override
	@Transactional
	public List<Invoice> getInvoices(int customerId) {
		return invoiceService.getInvoices(customerId);
	}

	@Override
	@Transactional
	public void saveInvoice(Invoice invoice) {
		invoiceService.saveInvoice(invoice);
	}

}
