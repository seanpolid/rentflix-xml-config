package edu.wccnet.sepolidori.service.concrete;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.interfaces.InvoiceMovieDAO;
import edu.wccnet.sepolidori.entity.InvoiceMovie;
import edu.wccnet.sepolidori.exception_handling.exceptions.InvoiceMovieNotFoundException;
import edu.wccnet.sepolidori.service.interfaces.InvoiceMovieService;

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
	public List<InvoiceMovie> getInvoiceMovies(int movieId) {
		return invoiceMovieDAO.getInvoiceMovies(movieId);
	}

	@Override
	@Transactional
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie) {
		invoiceMovieDAO.saveInvoiceMovie(invoiceMovie);
	}

}
