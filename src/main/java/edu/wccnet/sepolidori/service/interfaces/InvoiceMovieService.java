package edu.wccnet.sepolidori.service.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public interface InvoiceMovieService {
	
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId);
	public List<InvoiceMovie> getInvoiceMovies(int movieId);
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie);
	
}
