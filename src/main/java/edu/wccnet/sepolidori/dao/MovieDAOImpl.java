package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import edu.wccnet.sepolidori.entity.Movie;

@Repository
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
	public Movie getMovie(String movieName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Movie> query = session.createQuery("FROM Movie m WHERE m.name = :name", Movie.class);
		query.setParameter("name", movieName);
		return query.getSingleResult();
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
