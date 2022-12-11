package edu.wccnet.sepolidori.service;

import java.util.List;

import edu.wccnet.sepolidori.entity.Rating;

public interface RatingService {
	
	public Rating getRating(int ratingId);
	public List<Rating> getRatings();
	public void saveRating(Rating rating);
	public void deleteRating(int ratingId);
	
}
