package edu.wccnet.sepolidori.dao.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public interface InvoiceMovieDAO {
	
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId);
	public List<InvoiceMovie> getInvoiceMovies(int movieId);
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie);
	
}
