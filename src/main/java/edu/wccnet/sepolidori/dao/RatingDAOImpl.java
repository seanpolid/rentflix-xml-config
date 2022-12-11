package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import edu.wccnet.sepolidori.entity.Rating;

public class RatingDAOImpl implements RatingDAO {
	
	private final SessionFactory sessionFactory;
	
	public RatingDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Rating getRating(int ratingId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Rating.class, ratingId);
	}

	@Override
	public List<Rating> getRatings() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Rating", Rating.class).getResultList();
	}

	@Override
	public void saveRating(Rating rating) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rating);
	}

	@Override
	public void deleteRating(int ratingId) {
		Session session = sessionFactory.getCurrentSession();
		Rating rating = session.get(Rating.class, ratingId);
		session.remove(rating);
	}

}
