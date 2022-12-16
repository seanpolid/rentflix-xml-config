package edu.wccnet.sepolidori.service.concrete;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.interfaces.InvoiceDAO;
import edu.wccnet.sepolidori.entity.Invoice;
import edu.wccnet.sepolidori.exception_handling.exceptions.InvoiceNotFoundException;
import edu.wccnet.sepolidori.service.interfaces.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	private final InvoiceDAO invoiceDAO;
	
	public InvoiceServiceImpl(InvoiceDAO invoiceDAO) {
		this.invoiceDAO = invoiceDAO;
	}
	
	@Override
	@Transactional
	public Invoice getInvoice(int invoiceId) {
		Invoice invoice = invoiceDAO.getInvoice(invoiceId);
		if (invoice == null) {
			throw new InvoiceNotFoundException("Invoice " + invoiceId + " not found.");
		}
		return invoice;
	}

	@Override
	@Transactional
	public List<Invoice> getInvoices() {
		return invoiceDAO.getInvoices();
	}

	@Override
	@Transactional
	public List<Invoice> getInvoices(int customerId) {
		return invoiceDAO.getInvoices(customerId);
	}

	@Override
	@Transactional
	public void saveInvoice(Invoice invoice) {
		invoiceDAO.saveInvoice(invoice);
	}

	@Override
	@Transactional
	public void deleteInvoices() {
		invoiceDAO.deleteInvoices();
	}

}
