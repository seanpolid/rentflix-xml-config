package edu.wccnet.sepolidori.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.GenreDAO;
import edu.wccnet.sepolidori.entity.Genre;
import edu.wccnet.sepolidori.exception_handling.GenreNotFoundException;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreDAO genreDAO;
	
	public GenreServiceImpl(GenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}
	
	@Override
	@Transactional
	public Genre getGenre(int genreId) {
		Genre genre = genreDAO.getGenre(genreId);
		if (genre == null) {
			throw new GenreNotFoundException("Genre " + genreId + " not found.");
		}
		return genre;
	}

	@Override
	@Transactional
	public List<Genre> getGenres() {
		return genreDAO.getGenres();
	}

	@Override
	@Transactional
	public void saveGenre(Genre genre) {
		genreDAO.saveGenre(genre);
	}

	@Override
	@Transactional
	public void deleteGenre(int genreId) {
		genreDAO.deleteGenre(genreId);
	}

}
