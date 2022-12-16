package edu.wccnet.sepolidori.service.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Rating;

public interface RatingService {
	
	public Rating getRating(String ratingName);
	public List<Rating> getRatings();
	public void saveRatings(List<Rating> ratings);
	public void deleteRating(int ratingId);
	
}
