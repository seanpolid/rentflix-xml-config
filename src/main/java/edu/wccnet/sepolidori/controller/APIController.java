package edu.wccnet.sepolidori.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.entity.Genre;
import edu.wccnet.sepolidori.entity.Invoice;
import edu.wccnet.sepolidori.entity.InvoiceMovie;
import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.entity.Rating;
import edu.wccnet.sepolidori.entity.ReturnMovie;
import edu.wccnet.sepolidori.service.concrete.CartService;
import edu.wccnet.sepolidori.service.interfaces.CustomerService;
import edu.wccnet.sepolidori.service.interfaces.GenreService;
import edu.wccnet.sepolidori.service.interfaces.InvoiceMovieService;
import edu.wccnet.sepolidori.service.interfaces.InvoiceService;
import edu.wccnet.sepolidori.service.interfaces.MovieService;
import edu.wccnet.sepolidori.service.interfaces.RatingService;

@RestController
@RequestMapping("/api")
public class APIController {
	
	private final CustomerService customerService;
	private final InvoiceService invoiceService;
	private final InvoiceMovieService invoiceMovieService;
	private final MovieService movieService;
	private final GenreService genreService;
	private final RatingService ratingService;
	private final CartService cartService;
	
	public APIController (InvoiceService invoiceService, 
						CustomerService customerService, 
						RatingService ratingService, 
						MovieService movieService, 
						InvoiceMovieService invoiceMovieService, 
						GenreService genreService, 
						CartService cartService) {
		this.customerService = customerService;
		this.invoiceService = invoiceService;
		this.invoiceMovieService = invoiceMovieService;
		this.movieService = movieService;
		this.genreService = genreService;
		this.ratingService = ratingService;
		this.cartService = cartService;
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
	
	@GetMapping("/movies")
	public List<Movie> getMovies() {
		return movieService.getMovies();
	}
	
	@GetMapping("/movies/{movieName}")
	public Movie getMovie(@PathVariable String movieName) {
		return movieService.getMovie(movieName);
	}
	
	@GetMapping("/invoicemovies/{movieId}")
	public List<InvoiceMovie> getInvoiceMovies(@PathVariable int movieId) {
		return invoiceMovieService.getInvoiceMovies(movieId);
	}
	
	@PostMapping("/cart/{movieId}")
	public void addToCart(@PathVariable int movieId) {
		Movie movie = movieService.getMovie(movieId);
		cartService.addMovie(movie);
		System.out.println(cartService);
	}
	
	@PatchMapping("/return")
	public void returnMovies(@RequestBody List<ReturnMovie> returnMovies) {
		for (ReturnMovie returnMovie : returnMovies) {
			int invoiceMovieId = returnMovie.getInvoiceMovieId();
			InvoiceMovie invoiceMovie = invoiceMovieService.getInvoiceMovie(invoiceMovieId);
			invoiceMovie.setReturnDate(LocalDateTime.now());
			invoiceMovieService.saveInvoiceMovie(invoiceMovie);
		}
	}
	
	@GetMapping("/invoices/{customerId}")
	public List<Invoice> getInvoices(@PathVariable int customerId) {
		System.out.println(invoiceService.getInvoices(customerId));
		return invoiceService.getInvoices(customerId);
	}
	
	@DeleteMapping("/invoices")
	public void deleteInvoices() {
		invoiceService.deleteInvoices();
	}

}
