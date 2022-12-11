package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.RatingDAO;
import edu.wccnet.sepolidori.entity.Rating;
import edu.wccnet.sepolidori.exception_handling.RatingNotFoundException;

@Service
public class RatingServiceImpl implements RatingService {
	
	private final RatingDAO ratingDAO;
	
	public RatingServiceImpl(RatingDAO ratingDAO) {
		this.ratingDAO = ratingDAO;
	}
	
	@Override
	@Transactional
	public Rating getRating(int ratingId) {
		Rating rating = ratingDAO.getRating(ratingId);
		if (rating == null) {
			throw new RatingNotFoundException("Rating " + ratingId + " not found.");
		}
		return rating;
	}

	@Override
	@Transactional
	public List<Rating> getRatings() {
		return ratingDAO.getRatings();
	}

	@Override
	@Transactional
	public void saveRating(Rating rating) {
		ratingDAO.saveRating(rating);
	}

	@Override
	@Transactional
	public void deleteRating(int ratingId) {
		ratingDAO.deleteRating(ratingId);
	}

}
