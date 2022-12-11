package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Rating;
import edu.wccnet.sepolidori.exception_handling.RatingNotFoundException;

@Service
public class RatingServiceImpl implements RatingService {
	
	private final RatingService ratingService;
	
	public RatingServiceImpl(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@Override
	@Transactional
	public Rating getRating(int ratingId) {
		Rating rating = ratingService.getRating(ratingId);
		if (rating == null) {
			throw new RatingNotFoundException("Rating " + ratingId + " not found.");
		}
		return rating;
	}

	@Override
	@Transactional
	public List<Rating> getRatings() {
		return ratingService.getRatings();
	}

	@Override
	@Transactional
	public void saveRating(Rating rating) {
		ratingService.saveRating(rating);
	}

	@Override
	@Transactional
	public void deleteRating(int ratingId) {
		ratingService.deleteRating(ratingId);
	}

}
