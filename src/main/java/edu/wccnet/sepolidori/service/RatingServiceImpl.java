package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import edu.wccnet.sepolidori.entity.Rating;

public class RatingServiceImpl implements RatingService {
	
	private final RatingService ratingService;
	
	public RatingServiceImpl(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@Override
	@Transactional
	public Rating getRating(int ratingId) {
		return ratingService.getRating(ratingId);
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
