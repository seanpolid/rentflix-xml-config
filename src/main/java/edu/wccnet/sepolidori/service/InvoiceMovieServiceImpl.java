package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public class InvoiceMovieServiceImpl implements InvoiceMovieService {
	
	private final InvoiceMovieService invoiceMovieService;
	
	public InvoiceMovieServiceImpl(InvoiceMovieService invoiceMovieService) {
		this.invoiceMovieService = invoiceMovieService;
	}
	
	@Override
	@Transactional
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId) {
		return invoiceMovieService.getInvoiceMovie(invoiceMovieId);
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
