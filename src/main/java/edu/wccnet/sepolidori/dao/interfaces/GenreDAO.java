package edu.wccnet.sepolidori.dao.interfaces;

import java.util.List;

import edu.wccnet.sepolidori.entity.Genre;

public interface GenreDAO {
	
	public Genre getGenre(String genreName);
	public List<Genre> getGenres();
	public void saveGenre(Genre genre);
	public void deleteGenre(int genreId);
	
}
