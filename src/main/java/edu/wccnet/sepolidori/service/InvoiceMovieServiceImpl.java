package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import edu.wccnet.sepolidori.entity.InvoiceMovie;
import edu.wccnet.sepolidori.exception_handling.InvoiceMovieNotFoundException;

public class InvoiceMovieServiceImpl implements InvoiceMovieService {
	
	private final InvoiceMovieService invoiceMovieService;
	
	public InvoiceMovieServiceImpl(InvoiceMovieService invoiceMovieService) {
		this.invoiceMovieService = invoiceMovieService;
	}
	
	@Override
	@Transactional
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId) {
		InvoiceMovie invoiceMovie = invoiceMovieService.getInvoiceMovie(invoiceMovieId);
		if (invoiceMovie == null) {
			throw new InvoiceMovieNotFoundException("Invoice movie " + invoiceMovieId + " not found.");
		}
		return invoiceMovie;
	}

	@Override
	@Transactional
	public List<InvoiceMovie> getInvoiceMovies() {
		return invoiceMovieService.getInvoiceMovies();
	}

	@Override
	@Transactional
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie) {
		invoiceMovieService.saveInvoiceMovie(invoiceMovie);
	}

}
