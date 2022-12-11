package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.InvoiceMovieDAO;
import edu.wccnet.sepolidori.entity.InvoiceMovie;
import edu.wccnet.sepolidori.exception_handling.InvoiceMovieNotFoundException;

@Service
public class InvoiceMovieServiceImpl implements InvoiceMovieService {
	
	private final InvoiceMovieDAO invoiceMovieDAO;
	
	public InvoiceMovieServiceImpl(InvoiceMovieDAO invoiceMovieDAO) {
		this.invoiceMovieDAO = invoiceMovieDAO;
	}
	
	@Override
	@Transactional
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId) {
		InvoiceMovie invoiceMovie = invoiceMovieDAO.getInvoiceMovie(invoiceMovieId);
		if (invoiceMovie == null) {
			throw new InvoiceMovieNotFoundException("Invoice movie " + invoiceMovieId + " not found.");
		}
		return invoiceMovie;
	}

	@Override
	@Transactional
	public List<InvoiceMovie> getInvoiceMovies() {
		return invoiceMovieDAO.getInvoiceMovies();
	}

	@Override
	@Transactional
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie) {
		invoiceMovieDAO.saveInvoiceMovie(invoiceMovie);
	}

}
