package edu.wccnet.sepolidori.service.concrete;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import edu.wccnet.sepolidori.dao.interfaces.GenreDAO;
import edu.wccnet.sepolidori.entity.Genre;
import edu.wccnet.sepolidori.exception_handling.exceptions.GenreNotFoundException;
import edu.wccnet.sepolidori.service.interfaces.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreDAO genreDAO;
	
	public GenreServiceImpl(GenreDAO genreDAO) {
		this.genreDAO = genreDAO;
	}
	
	@Override
	@Transactional
	public Genre getGenre(String genreName) {
		Genre genre = genreDAO.getGenre(genreName);
		if (genre == null) {
			throw new GenreNotFoundException("Genre " + genreName + " not found.");
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
	public void saveGenres(List<Genre> genres) {
		for (Genre genre : genres) {
			genreDAO.saveGenre(genre);
		}
	}

	@Override
	@Transactional
	public void deleteGenre(int genreId) {
		genreDAO.deleteGenre(genreId);
	}

}
