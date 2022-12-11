package edu.wccnet.sepolidori.service;

import java.util.List;

import edu.wccnet.sepolidori.entity.Movie;

public interface MovieService {
	
	public Movie getMovie(int movieId);
	public List<Movie> getMovies();
	public void saveMovie(Movie movie);
	public void deleteMovie(int movieId);
	
}
