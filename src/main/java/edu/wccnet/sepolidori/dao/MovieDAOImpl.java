package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.wccnet.sepolidori.entity.Movie;

public class MovieDAOImpl implements MovieDAO {
	
	private final SessionFactory sessionFactory;
	
	public MovieDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Movie getMovie(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Movie.class, movieId);
	}

	@Override
	public List<Movie> getMovies() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Movie", Movie.class).getResultList();
	}

	@Override
	public void saveMovie(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		session.save(movie);
	}

	@Override
	public void deleteMovie(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		Movie movie = session.get(Movie.class, movieId);
		session.remove(movie);
	}

}
