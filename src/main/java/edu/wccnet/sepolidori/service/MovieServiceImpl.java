package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.MovieDAO;
import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.exception_handling.MovieNotFoundException;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieDAO movieDAO;
	
	public MovieServiceImpl(MovieDAO movieDAO) {
		this.movieDAO = movieDAO;
	}
	
	@Override
	@Transactional
	public Movie getMovie(int movieId) {
		Movie movie = movieDAO.getMovie(movieId);
		if (movie == null) {
			throw new MovieNotFoundException("Movie " + movieId + " not found.");
		}
		return movie;
	}

	@Override
	@Transactional
	public List<Movie> getMovies() {
		return movieDAO.getMovies();
	}

	@Override
	@Transactional
	public void saveMovie(Movie movie) {
		movieDAO.saveMovie(movie);
	}

	@Override
	@Transactional
	public void deleteMovie(int movieId) {
		movieDAO.deleteMovie(movieId);
	}

}
