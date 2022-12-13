package edu.wccnet.sepolidori.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.entity.Invoice;
import edu.wccnet.sepolidori.entity.InvoiceMovie;
import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.service.CartService;
import edu.wccnet.sepolidori.service.CustomerService;
import edu.wccnet.sepolidori.service.GenreService;
import edu.wccnet.sepolidori.service.InvoiceService;
import edu.wccnet.sepolidori.service.LoggedInUserService;
import edu.wccnet.sepolidori.service.LoginProcessor;
import edu.wccnet.sepolidori.service.MovieService;
import edu.wccnet.sepolidori.service.RatingService;

@Controller
public class MainController {
	
	private final LoginProcessor loginProcessor;
	private final LoggedInUserService loggedInUserService;
	private final CustomerService customerService;
	private final GenreService genreService;
	private final RatingService ratingService;
	private final MovieService movieService;
	private final CartService cartService;
	private final InvoiceService invoiceService;
	
	public MainController(CustomerService customerService, 
						LoggedInUserService loggedInUserService, 
						LoginProcessor loginProcessor, 
						RatingService ratingService, 
						GenreService genreService, 
						MovieService movieService, 
						CartService cartService, 
						InvoiceService invoiceService) {
		this.loginProcessor = loginProcessor;
		this.customerService = customerService;
		this.loggedInUserService = loggedInUserService;
		this.genreService = genreService;
		this.ratingService = ratingService;
		this.movieService = movieService;
		this.cartService = cartService;
		this.invoiceService = invoiceService;
	}
	
	@RequestMapping({"/home", "/"})
	public String home(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		List<Customer> customers = customerService.getCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("customer", loggedInUserService.getCustomer());
		return "login";
	}
	
	@RequestMapping("/loginCustomer")
	public String loginCustomer(@RequestParam int customerId, Model model) {
		Customer customer = loginProcessor.login(customerId);
		return "redirect:/browse";
	}
	
	@RequestMapping("/browse")
	public String browse(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		model.addAttribute("movies", movieService.getMovies());
		return "browse";
	}
	
	@RequestMapping("/movie")
	public String movie(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		model.addAttribute("movie", new Movie());
		model.addAttribute("genres", genreService.getGenres());
		model.addAttribute("ratings", ratingService.getRatings());
		return "add-movie";
	}
	
	@RequestMapping("/addMovie")
	public String addMovie(@ModelAttribute("movie") Movie movie, Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		Movie preppedMovie = movieService.prepMovie(movie);
		movieService.saveMovie(preppedMovie);
		return "redirect:/browse";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		model.addAttribute("movies", cartService.getMovies());
		return "checkout";
	}
	
	@PostMapping("/checkout")
	public String checkout() {
		Invoice invoice = new Invoice();
		BigDecimal total = new BigDecimal(0);
		for (Movie movie : cartService.getMovies()) {
			InvoiceMovie invoiceMovie = new InvoiceMovie();
			invoiceMovie.setId(0);
			invoiceMovie.setMovie(movie);
			invoiceMovie.setInvoice(invoice);
			invoice.addInvoiceMovie(invoiceMovie);
			System.out.println(movie.getCost());
			total.add(movie.getCost());
			System.out.println(total);
		}
		invoice.setCustomer(loggedInUserService.getCustomer());
		invoice.setTotal(total);
		invoice.setDate(LocalDateTime.now());
		invoiceService.saveInvoice(invoice);
		return "redirect:/library";
	}
	
	@RequestMapping("/library")
	public String library(Model model) {
		Customer customer = loggedInUserService.getCustomer();
		model.addAttribute("customer", customer);
		model.addAttribute("movies", invoiceService.getInvoices(customer.getId()));
		return "library";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loggedInUserService.setCustomer(null);
		cartService.setMovies(null);
		return "redirect:/";
	}
	
}
