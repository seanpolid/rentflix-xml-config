package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.exception_handling.MovieNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieService movieService;
	
	public MovieServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@Override
	@Transactional
	public Movie getMovie(int movieId) {
		Movie movie = movieService.getMovie(movieId);
		if (movie == null) {
			throw new MovieNotFoundException("Movie " + movieId + " not found.");
		}
		return movie;
	}

	@Override
	@Transactional
	public List<Movie> getMovies() {
		return movieService.getMovies();
	}

	@Override
	@Transactional
	public void saveMovie(Movie movie) {
		movieService.saveMovie(movie);
	}

	@Override
	@Transactional
	public void deleteMovie(int movieId) {
		movieService.deleteMovie(movieId);
	}

}
