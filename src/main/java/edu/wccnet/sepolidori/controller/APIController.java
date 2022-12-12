package edu.wccnet.sepolidori.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.entity.Genre;
import edu.wccnet.sepolidori.entity.Rating;
import edu.wccnet.sepolidori.service.CustomerService;
import edu.wccnet.sepolidori.service.GenreService;
import edu.wccnet.sepolidori.service.InvoiceMovieService;
import edu.wccnet.sepolidori.service.InvoiceService;
import edu.wccnet.sepolidori.service.MovieService;
import edu.wccnet.sepolidori.service.RatingService;

@RestController
@RequestMapping("/api")
public class APIController {
	
	private final CustomerService customerService;
	private final InvoiceService invoiceService;
	private final InvoiceMovieService invoiceMovieService;
	private final MovieService movieService;
	private final GenreService genreService;
	private final RatingService ratingService;
	
	public APIController (InvoiceService invoiceService, 
						CustomerService customerService, 
						RatingService ratingService, 
						MovieService movieService, 
						InvoiceMovieService invoiceMovieService, 
						GenreService genreService) {
		this.customerService = customerService;
		this.invoiceService = invoiceService;
		this.invoiceMovieService = invoiceMovieService;
		this.movieService = movieService;
		this.genreService = genreService;
		this.ratingService = ratingService;
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		return customerService.getCustomer(customerId);
	}
	
	@PostMapping("/customers")
	public void createCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
	}
	
	@GetMapping("/ratings")
	public List<Rating> getRatings() {
		return ratingService.getRatings();
	}
	
	@PostMapping("/ratings")
	public void createRatings(@RequestBody List<Rating> ratings) {
		for (Rating rating : ratings) {
			rating.setId(0);
		}
		ratingService.saveRatings(ratings);
	}
	
	@GetMapping("/genres")
	public List<Genre> getGenres() {
		return genreService.getGenres();
	}
	
	@PostMapping("/genres")
	public void createGenres(@RequestBody List<Genre> genres) {
		for (Genre genre : genres) {
			genre.setId(0);
		}
		genreService.saveGenres(genres);
	}
	
}
