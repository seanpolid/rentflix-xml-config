package edu.wccnet.sepolidori.dao.concrete;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import edu.wccnet.sepolidori.dao.interfaces.InvoiceMovieDAO;
import edu.wccnet.sepolidori.entity.InvoiceMovie;

@Repository
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
	public List<InvoiceMovie> getInvoiceMovies(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		Query<InvoiceMovie> query = session.createQuery("FROM InvoiceMovie im WHERE im.movie.id = :movieId", InvoiceMovie.class);
		query.setParameter("movieId", movieId);
		return query.getResultList();
	}

	@Override
	public void saveInvoiceMovie(InvoiceMovie invoiceMovie) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(invoiceMovie);
	}

}
