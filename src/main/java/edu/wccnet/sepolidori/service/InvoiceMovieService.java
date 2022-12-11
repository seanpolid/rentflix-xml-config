package edu.wccnet.sepolidori.service;

import java.util.List;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public interface InvoiceMovieService {
	
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId);
	public List<InvoiceMovie> getInvoiceMovies();
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie);
	
}