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

@Entity(name="invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date")
	private LocalDateTime date;
	
	@Column(name="total")
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="invoice")
	private List<InvoiceMovie> invoiceMovies = new ArrayList<InvoiceMovie>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
