package edu.wccnet.sepolidori.service.concrete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import edu.wccnet.sepolidori.entity.Movie;

@Service
@SessionScope
public class CartService {
	
	private List<Movie> movies = new ArrayList<Movie>();

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void addMovie(Movie movie) {
		this.movies.add(movie);
	}

	@Override
	public String toString() {
		return "CartService [movies=" + movies + "]";
	}
}
