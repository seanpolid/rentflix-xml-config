package edu.wccnet.sepolidori.dao;

import java.util.List;

import edu.wccnet.sepolidori.entity.Movie;

public interface MovieDAO {
	
	public Movie getMovie(int movieId);
	public List<Movie> getMovies();
	public void saveMovie(Movie movie);
	public void deleteMovie(int movieId);
	
} 
