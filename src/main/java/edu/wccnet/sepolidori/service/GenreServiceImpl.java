package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.entity.Genre;
import edu.wccnet.sepolidori.exception_handling.GenreNotFoundException;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreService genreService;
	
	public GenreServiceImpl(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@Override
	@Transactional
	public Genre getGenre(int genreId) {
		Genre genre = genreService.getGenre(genreId);
		if (genre == null) {
			throw new GenreNotFoundException("Genre " + genreId + " not found.");
		}
		return genre;
	}

	@Override
	@Transactional
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}

	@Override
	@Transactional
	public void saveGenre(Genre genre) {
		genreService.saveGenre(genre);
	}

	@Override
	@Transactional
	public void deleteGenre(int genreId) {
		genreService.deleteGenre(genreId);
	}

}
