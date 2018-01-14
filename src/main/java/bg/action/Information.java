package bg.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bg.model.Book;
import bg.model.BookDistributor;
import bg.model.Certificate;

public class Information {

	public static final File booksStorage = new File("books.xml");
	public static final File bookDistributhorsStorage = new File("bookDistributhors.xml");

	public Information() {
		// xml parsr hre
	}

	public List<Book> loadBookInformation() {
		System.out.println("четене на информация за книги");
		List<Book> books = new ArrayList<Book>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(booksStorage);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("book");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				Book book = new Book();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentSelectedElement = (Element) nNode;

					book.setTitle(currentSelectedElement.getElementsByTagName("title").item(0).getTextContent());

					// authors

					NodeList aut = currentSelectedElement.getElementsByTagName("author");
					List<String> authors = new ArrayList();
					for (int i = 0; i < aut.getLength(); i++) {
						Node node = aut.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element currentSelectedAuthor = (Element) node;
							authors.add(currentSelectedAuthor.getTextContent());

						}
					} // end loop

					book.setAuthors(authors);
					// end authors

					book.setIzdanie(Integer
							.parseInt(currentSelectedElement.getElementsByTagName("izdanie").item(0).getTextContent()));

					// ISBN
					book.setISBN(Long
							.parseLong(currentSelectedElement.getElementsByTagName("ISBN").item(0).getTextContent()));

					// date i taka natatuk
					int day = Integer
							.parseInt(currentSelectedElement.getElementsByTagName("day").item(0).getTextContent());

					int month = Integer
							.parseInt(currentSelectedElement.getElementsByTagName("month").item(0).getTextContent());
					int year = Integer
							.parseInt(currentSelectedElement.getElementsByTagName("year").item(0).getTextContent());

					book.setDate(new Date(year, month, day));
					// date of book

					// tiraj

					book.setTiraj(currentSelectedElement.getElementsByTagName("tiraj").item(0).getTextContent());

					// end tiraj

					// certificate if it has
					if (currentSelectedElement.getElementsByTagName("certificate") != null) {
						int dayC = Integer.parseInt(
								currentSelectedElement.getElementsByTagName("certificateDay").item(0).getTextContent());

						int monthC = Integer.parseInt(currentSelectedElement.getElementsByTagName("certificateMonth")
								.item(0).getTextContent());
						int yearC = Integer.parseInt(currentSelectedElement.getElementsByTagName("certificateYear")
								.item(0).getTextContent());
						book.setCertificate(new Certificate(new Date(yearC, monthC, dayC)));
					}
					// end of certificate date;

					// bookDistributor
					if (currentSelectedElement.getElementsByTagName("bookDistributor") != null) {
						{
							BookDistributor distributor = new BookDistributor();

							distributor.setNameOfDistributor(
									currentSelectedElement.getElementsByTagName("name").item(0).getTextContent());
							distributor.setTelephone(
									currentSelectedElement.getElementsByTagName("telephone").item(0).getTextContent());
							distributor.setAddress(
									currentSelectedElement.getElementsByTagName("address").item(0).getTextContent());

							book.setBookDistributor(distributor);
						}

					} // Selected book element

					book.setPrice(Double.parseDouble(
							currentSelectedElement.getElementsByTagName("price").item(0).getTextContent()));

					books.add(book);

				}
			} // end loop of <book> nodes

		} catch (Exception e) {
			return null;
		}

		return books;
	}

	public List<BookDistributor> loadDistributorsInformation() {
		System.out.println("четене на информация за дистрибутори");
		List<BookDistributor> distributors = new ArrayList<BookDistributor>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(bookDistributhorsStorage);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("distributor");
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				BookDistributor distributor = new BookDistributor();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element currentSelectedElement = (Element) nNode;

					distributor.setNameOfDistributor(
							currentSelectedElement.getElementsByTagName("name").item(0).getTextContent());
					distributor.setTelephone(
							currentSelectedElement.getElementsByTagName("telephone").item(0).getTextContent());
					distributor.setAddress(
							currentSelectedElement.getElementsByTagName("address").item(0).getTextContent());

				}

				distributors.add(distributor);

			} // end loop of <distributor> nodes

		} catch (Exception e) {
			System.out.println("Възникна проблем с четенето на данните за дистрибутори.");
		}

		return distributors;

	}

	public void saveInformation(List<Book> books, List<BookDistributor> distributors) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("books");
			doc.appendChild(rootElement);

			for (Book book : books) {

				// supercars element
				Element currentElement = doc.createElement("book");
				rootElement.appendChild(currentElement);

				// title element
				Element title = doc.createElement("title");
				title.appendChild(doc.createTextNode(book.getTitle()));
				currentElement.appendChild(title);

				// authors element
				for (String author : book.getAuthors()) {
					Element authorel = doc.createElement("author");
					authorel.appendChild(doc.createTextNode(author));
					currentElement.appendChild(authorel);
				}

				// izdanie element
				Element izdanie = doc.createElement("izdanie");
				izdanie.appendChild(doc.createTextNode(Integer.toString(book.getIzdanie())));
				currentElement.appendChild(izdanie);

				// ISBN element
				Element ISBN = doc.createElement("ISBN");
				ISBN.appendChild(doc.createTextNode(Long.toString(book.getISBN())));
				currentElement.appendChild(ISBN);

				// date element
				Element date = doc.createElement("date");

				Element day = doc.createElement("day");
				day.appendChild(doc.createTextNode(Integer.toString(book.getDate().getDay())));
				date.appendChild(day);

				Element month = doc.createElement("month");
				month.appendChild(doc.createTextNode(Integer.toString(book.getDate().getMonth())));
				date.appendChild(month);

				Element year = doc.createElement("year");

				year.appendChild(doc.createTextNode(Integer.toString(book.getDate().getYear())));
				date.appendChild(year);

				currentElement.appendChild(date);

				// tiraj element end

				Element tiraj = doc.createElement("tiraj");
				tiraj.appendChild(doc.createTextNode(book.getTiraj()));
				currentElement.appendChild(tiraj);

				// certificate
				if (book.getCertificate() != null) {
					Element certificate = doc.createElement("certificate");
					// date element
					Element dateForCertificate = doc.createElement("certificateDate");
					certificate.appendChild(dateForCertificate);

					Date dateOfCertificate = book.getCertificate().getDate();

					Element cday = doc.createElement("certificateDay");
					cday.appendChild(doc.createTextNode(Integer.toString(dateOfCertificate.getDay())));
					dateForCertificate.appendChild(cday);

					Element cmonth = doc.createElement("certificateMonth");
					cmonth.appendChild(doc.createTextNode(Integer.toString(dateOfCertificate.getMonth())));
					dateForCertificate.appendChild(cmonth);

					Element cyear = doc.createElement("certificateYear");

					cyear.appendChild(doc.createTextNode(Integer.toString(dateOfCertificate.getYear())));
					dateForCertificate.appendChild(cyear);

					currentElement.appendChild(certificate);
					// end certificate

				}

				// BookDistributor element end

				Element bookDistributor = doc.createElement("bookDistributor");

				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(book.getBookDistributor().getNameOfDistributor()));
				bookDistributor.appendChild(name);

				Element telephone = doc.createElement("telephone");
				telephone.appendChild(doc.createTextNode(book.getBookDistributor().getTelephone()));
				bookDistributor.appendChild(telephone);

				Element address = doc.createElement("address");
				address.appendChild(doc.createTextNode(book.getBookDistributor().getAddress()));
				bookDistributor.appendChild(address);

				currentElement.appendChild(bookDistributor);

				// end bookDistributor

				// price
				Element price = doc.createElement("price");
				price.appendChild(doc.createTextNode(Double.toString(book.getPrice())));
				currentElement.appendChild(price);

				// end price

			} // end loop

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(booksStorage);
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
		} catch (Exception e) {
			System.out.println("Липсващ файл за книгите, или друга повреда.");
		}

		// оттук
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("distributors");
			doc.appendChild(rootElement);

			for (BookDistributor distributor : distributors) {

				// distributor element
				Element currentElement = doc.createElement("distributor");
				rootElement.appendChild(currentElement);

				// name element
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(distributor.getNameOfDistributor()));
				currentElement.appendChild(name);

				// telephone element
				Element telephone = doc.createElement("telephone");
				telephone.appendChild(doc.createTextNode(distributor.getTelephone()));
				currentElement.appendChild(telephone);

				// address element
				Element address = doc.createElement("address");
				address.appendChild(doc.createTextNode(distributor.getAddress()));
				currentElement.appendChild(address);
			} // end loop

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(bookDistributhorsStorage);
			transformer.transform(source, result);

			// Output to console for testing
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			System.out.println("wsi4ko to`no");
		} catch (Exception e) {
			System.out.println("Липсващ файл за дистрибутори, или друга повреда.");
		}

	}

}
