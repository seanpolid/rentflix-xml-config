package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.wccnet.sepolidori.entity.Genre;

@Repository
public class GenreDAOImpl implements GenreDAO {
	
	private final SessionFactory sessionFactory;
	
	public GenreDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Genre getGenre(int genreId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Genre.class, genreId);
	}

	@Override
	public List<Genre> getGenres() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From Genre", Genre.class).getResultList();
	}

	@Override
	public void saveGenre(Genre genre) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(genre);
	}

	@Override
	public void deleteGenre(int genreId) {
		Session session = sessionFactory.getCurrentSession();
		Genre genre = session.get(Genre.class, genreId);
		session.remove(genre);
	}

}
