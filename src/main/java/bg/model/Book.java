package bg.model;

import java.util.Date;
import java.util.List;

public class Book {

	private String title;
	private List<String> authors;
	private int izdanie;
	private long ISBN;
	private Date date;
	private String tiraj;
	private Certificate certificate;
	private BookDistributor bookDistributor;
	private double price;

	public Book() {
		title = null;
		authors = null;
		date = null;
		tiraj = null;
		certificate = null;
		bookDistributor = null;
		price = 0.0d;
	}

	public Book(String title, List<String> authors, int izdanie, long iSBN, Date date, String tiraj,
			Certificate certificate, BookDistributor bookDistributor) {
		super();
		this.title = title;
		this.authors = authors;
		this.izdanie = izdanie;
		ISBN = iSBN;
		this.date = date;
		this.tiraj = tiraj;
		this.certificate = certificate;
		this.bookDistributor = bookDistributor;
	}

	@Override
	public String toString() {
		StringBuilder bookInformation = new StringBuilder();
		if (title != null)
			bookInformation.append("\nЗаглавие на книгата : " + title);
		if (authors != null) {

			switch (authors.size()) {
			case 1:
				bookInformation.append("\nавтор : " + authors);
				break;

			default:
				bookInformation.append("\nавтори : " + authors);
				break;
			}

		}
		if (izdanie != 0)
			bookInformation.append("\nИздание  : " + izdanie);
		if (ISBN != 0)
			bookInformation.append("\nISBN номер : " + ISBN);
		if (date != null)
			bookInformation.append("\nдата на издаване : " + date);
		if (tiraj != null)
			bookInformation.append("\nтираж : " + tiraj + "\n");
		if (certificate != null) {
			bookInformation.append(certificate);
		} else {
			bookInformation.append("\nНяма издаден сертификат от МО");
		}
		if (bookDistributor != null)
			bookInformation.append("\nКнигоразпространител: " + bookDistributor);

		bookInformation.append("\nЦена : " + price);
		return bookInformation.toString();
	}

	public final double getPrice() {
		return price;
	}

	public final void setPrice(double price) {
		this.price = price;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final List<String> getAuthors() {
		return authors;
	}

	public final void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public final int getIzdanie() {
		return izdanie;
	}

	public final void setIzdanie(int izdanie) {
		this.izdanie = izdanie;
	}

	public final long getISBN() {
		return ISBN;
	}

	public final void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	public final String getTiraj() {
		return tiraj;
	}

	public final void setTiraj(String tiraj) {
		this.tiraj = tiraj;
	}

	public final Certificate getCertificate() {
		return certificate;
	}

	public final void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

	public final BookDistributor getBookDistributor() {
		return bookDistributor;
	}

	public final void setBookDistributor(BookDistributor bookDistributor) {
		this.bookDistributor = bookDistributor;
	}

}
