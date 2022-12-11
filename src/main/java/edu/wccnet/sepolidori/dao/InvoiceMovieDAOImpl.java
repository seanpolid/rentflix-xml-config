package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.wccnet.sepolidori.entity.InvoiceMovie;

public class InvoiceMovieDAOImpl implements InvoiceMovieDAO {
	
	private final SessionFactory sessionFactory;
	
	public InvoiceMovieDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public InvoiceMovie getInvoiceMovie(int invoiceMovieId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(InvoiceMovie.class, invoiceMovieId);
	}

	@Override
	public List<InvoiceMovie> getInvoiceMovies() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM InvoiceMovie", InvoiceMovie.class).getResultList();
	}

	@Override
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie) {
		Session session = sessionFactory.getCurrentSession();
		session.save(invoiceMovie);
	}

}
