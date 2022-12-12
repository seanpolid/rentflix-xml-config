package edu.wccnet.sepolidori.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.wccnet.sepolidori.entity.Customer;
import edu.wccnet.sepolidori.entity.Movie;
import edu.wccnet.sepolidori.service.CartService;
import edu.wccnet.sepolidori.service.CustomerService;
import edu.wccnet.sepolidori.service.GenreService;
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
	
	public MainController(CustomerService customerService, 
						LoggedInUserService loggedInUserService, 
						LoginProcessor loginProcessor, 
						RatingService ratingService, 
						GenreService genreService, 
						MovieService movieService, CartService cartService) {
		this.loginProcessor = loginProcessor;
		this.customerService = customerService;
		this.loggedInUserService = loggedInUserService;
		this.genreService = genreService;
		this.ratingService = ratingService;
		this.movieService = movieService;
		this.cartService = cartService;
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
	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("customer", loggedInUserService.getCustomer());
		model.addAttribute("movies", cartService.getMovies());
		return "checkout";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		loggedInUserService.setCustomer(null);
		return "redirect:/";
	}
	
}
