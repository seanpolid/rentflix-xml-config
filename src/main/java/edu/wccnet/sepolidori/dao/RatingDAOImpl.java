package edu.wccnet.sepolidori.dao;

import java.util.List;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.wccnet.sepolidori.entity.Rating;

@Repository
public class RatingDAOImpl implements RatingDAO {
	
	private final SessionFactory sessionFactory;
	
	public RatingDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Rating getRating(String ratingName) {
		Session session = sessionFactory.getCurrentSession();
		Query<Rating> query = session.createQuery("FROM Rating r WHERE r.name = :name", Rating.class);
		query.setParameter("name", ratingName);
		return query.getSingleResult();
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
