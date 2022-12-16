package edu.wccnet.sepolidori.dao.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Rating;

public interface RatingDAO {
	
	public Rating getRating(String ratingName);
	public List<Rating> getRatings();
	public void saveRating(Rating rating);
	public void deleteRating(int ratingId);
	
}
