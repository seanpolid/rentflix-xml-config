package edu.wccnet.sepolidori.entity;

public class ReturnMovie {
	
	private int movieId;
	private int invoiceMovieId;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getInvoiceMovieId() {
		return invoiceMovieId;
	}

	public void setInvoiceMovieId(int invoiceMovieId) {
		this.invoiceMovieId = invoiceMovieId;
	}

	@Override
	public String toString() {
		return "ReturnMovie [movieId=" + movieId + ", invoiceMovieId=" + invoiceMovieId + "]";
	}

}
