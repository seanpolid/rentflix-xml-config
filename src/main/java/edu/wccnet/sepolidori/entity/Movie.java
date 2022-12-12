package edu.wccnet.sepolidori.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="movie")
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
	private LocalDate releaseDate;
	
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
	
	@JsonIgnore
	@OneToMany(mappedBy="movie", fetch=FetchType.LAZY)
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

	public String getReleaseDate() {
		return releaseDate.toString();
	}

	public void setReleaseDate(String releaseDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		this.releaseDate = LocalDate.parse(releaseDate);
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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", description=" + description + ", yearMade=" + yearMade
				+ ", releaseDate=" + releaseDate + ", totalCopies=" + totalCopies + ", cost=" + cost + ", length="
				+ length + ", rating=" + rating + ", genre=" + genre + "]";
	}
	
}
