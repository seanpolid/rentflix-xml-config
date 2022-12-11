package edu.wccnet.sepolidori.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name="movie")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="year_made")
	private int yearMade;
	
	@Column(name="release_date")
	private LocalDateTime releaseDate;
	
	@Column(name="total_copies")
	private int totalCopies;
	
	@Column(name="cost")
	private BigDecimal cost;
	
	@Column(name="length")
	private int length;
	
	@ManyToOne
	@JoinColumn(name="rating_id")
	private Rating rating;
	
	@ManyToOne
	@JoinColumn(name="genre_id")
	private Genre genre;
	
	@OneToMany(mappedBy="movie")
	private List<InvoiceMovie> invoiceMovies = new ArrayList<InvoiceMovie>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYearMade() {
		return yearMade;
	}

	public void setYearMade(int yearMade) {
		this.yearMade = yearMade;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public List<InvoiceMovie> getInvoiceMovies() {
		return invoiceMovies;
	}

	public void setInvoiceMovies(List<InvoiceMovie> invoiceMovies) {
		this.invoiceMovies = invoiceMovies;
	}
	
	public void addInvoiceMovie(InvoiceMovie invoiceMovie) {
		this.invoiceMovies.add(invoiceMovie);
	}
	
	public void removeInvoiceMovie(InvoiceMovie invoiceMovie) {
		this.invoiceMovies.remove(invoiceMovie);
	}
	
}
