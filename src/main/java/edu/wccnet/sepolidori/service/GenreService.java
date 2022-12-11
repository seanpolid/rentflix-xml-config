package edu.wccnet.sepolidori.service;

import java.util.List;

import edu.wccnet.sepolidori.entity.Genre;

public interface GenreService {
		
	public Genre getGenre(int genreId);
	public List<Genre> getGenres();
	public void saveGenre(Genre genre);
	public void deleteGenre(int genreId);
	
}
