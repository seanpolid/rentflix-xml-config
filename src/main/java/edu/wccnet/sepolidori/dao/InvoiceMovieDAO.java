package edu.wccnet.sepolidori.dao;

import java.util.List;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public interface InvoiceMovieDAO {
	
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId);
	public List<InvoiceMovie> getInvoiceMovies();
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie);
	
}