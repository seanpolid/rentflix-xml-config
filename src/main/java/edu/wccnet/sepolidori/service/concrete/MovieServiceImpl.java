package edu.wccnet.sepolidori.service.concrete;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.interfaces.MovieDAO;
import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.exception_handling.exceptions.MovieNotFoundException;
import edu.wccnet.sepolidori.service.interfaces.GenreService;
import edu.wccnet.sepolidori.service.interfaces.MovieService;
import edu.wccnet.sepolidori.service.interfaces.RatingService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieDAO movieDAO;
	private final GenreService genreService;
	private final RatingService ratingService;
	
	public MovieServiceImpl(MovieDAO movieDAO, RatingService ratingService, GenreService genreService) {
		this.movieDAO = movieDAO;
		this.genreService = genreService;
		this.ratingService = ratingService;
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
	public Movie getMovie(String movieName) {
		Movie movie = movieDAO.getMovie(movieName);
		if (movie == null) {
			throw new MovieNotFoundException("Movie " + movieName + " not found.");
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

	@Override
	public Movie prepMovie(Movie movie) {
		System.out.println("getting rating");
		System.out.println(movie.getRating().getName());
		String ratingName = movie.getRating().getName();
		movie.setRating(ratingService.getRating(ratingName));
		
		System.out.println("getting genre");
		String genreName = movie.getGenre().getName();
		movie.setGenre(genreService.getGenre(genreName));
		
		movie.setId(0);
		return movie;
	}

}
