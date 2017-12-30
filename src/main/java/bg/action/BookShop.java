package bg.action;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import bg.model.Basket;
import bg.model.Book;
import bg.model.BookDistributor;
import bg.model.Certificate;
import static bg.action.Patterns.*;

public class BookShop {

	private static Scanner scanner;
	private List<Book> books;
	private List<BookDistributor> distributors;
	private Basket bookShopBasket;
	private Information information;

	public BookShop() {
		scanner = new Scanner(System.in);
		books = null;
		distributors = null;
		bookShopBasket = new Basket();
		information = new Information();
	}

	public void startWork() {
		books = information.loadBookInformation();
		distributors = information.loadDistributorsInformation();

		if (books == null) {
			books = new ArrayList<Book>();
		}
		if (distributors == null) {
			books = new ArrayList<Book>();
		}
		boolean wannaBeHere = true;

		System.out.println("����� ����� � ������ ������� �� �������� � �����.\n");
		do {
			pressEnterToContinue();
			System.out.println("\n\n ����� ����� ������ �� ���������? ���� �������� ����� \n"
					+ "0 - �� �� �������� �� �������� \n" + "1 - �� ���������� ��������� ����� \n"
					+ "2 - �� ��������� ��� ���� \"�������\" \n" + "3 - �� �������� ���� ����� ��� ������������ �� \n"
					+ "4 - �� �������� ��� �������������������� ��� ������������ �� \n");
			try {
				System.out.print("�������� �����:");
				int answer = scanner.nextInt();

				switch (answer) {
				case 0:
					wannaBeHere = false;
					break;
				case 1:
					for (Book book : books) {
						System.out.print(book.getTitle() + "  , ");
					}
					break;

				case 2:
					bookShopBasket.salesMenu(books, scanner);
					break;

				case 3:
					Book newBook = addNewBook();
					System.out.println("add book " + newBook);
					books.add(newBook);
					break;

				case 4:
					addNewBookDistributor();
					break;

				default:
					System.out
							.println("��� �������� ������/�������������� �����. ���� ��������� Enter �� ������������");
					scanner.nextLine();
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("ERROR!! ��� �� ��������� �����,��� ���� �����. ���� �������� ������!");
				pressEnterToContinue();
				continue;

			}
		} while (wannaBeHere);
	}

	public static void pressEnterToContinue() {
		System.err.println("Press Enter to continue............................");
		scanner.nextLine();
	}

	private Book addNewBook() {
		System.out.println("��� �������� �� �������� ���� �����");
		Book newBook = new Book();

		String title = null;
		int attempts = 1;
		while (true) {

			System.out.print("\n ��������d: ");
			title = scanner.nextLine();
			if (title.length() < 3 || !title.matches(TITLE_PATTERN)) {
				System.out.println("������ �������� � ��������� ��� ������,�������� ������.");
				continue;
			}

			newBook.setTitle(title);
			break;
		}

		pressEnterToContinue();

		// authors
		String author;
		List<String> authors = new ArrayList();
		while (true) {
			System.out.print("\n���� �������� ����� : ");
			author = scanner.nextLine();
			if (!author.matches(NAME_PATTERN)) {
				System.out.println(
						"������ �������� ��� � ��������� ��� ������,������ �� � ���� 3 �����,�������� ������.");
				continue;
			} else {
				authors.add(author);
				System.out.println("\n ������� � �������, ������ �� �� �������� ��� ������? \n1-��\n ������ �����-�� ");
				if (scanner.nextLine().equals("1")) {

					pressEnterToContinue();
					continue;
				} else {
					if (authors.size() < 1) {
						System.out.println("������ �� �������� ���� ���� �����!! ���� ��������� ��!");

						pressEnterToContinue();
						continue;
					}
					break;
				}
			}

		}
		newBook.setAuthors(authors);

		pressEnterToContinue();
		// end authors
		attempts = 1;

		while (true) {
			System.out.println("���� �������� ������� �� ������� - � �����");
			try {
				int izdanie = scanner.nextInt();
				newBook.setIzdanie(izdanie);
				break;
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ����� �� ������ .. ! �������� ������");
				pressEnterToContinue();
			}
		}

		pressEnterToContinue();

		while (true) {
			System.out.println("���� �������� ISBN �� ������� - � �����");
			try {
				long Isbn = scanner.nextLong();
				newBook.setISBN(Isbn);
				break;
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ����� �� ISBN .. ! �������� ������");
				pressEnterToContinue();
			}
		}

		pressEnterToContinue();
		System.out.println("���� �������� ���� �� �������� �� �������:");
		newBook.setDate(getDate());

		pressEnterToContinue();
		System.out.println("���� �������� ����� �� ������� - � �����");
		while (true) {

			try {
				int tiraj = scanner.nextInt();
				newBook.setTiraj(Integer.toString(tiraj));
				break;
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ����� �� ������ .. ! �������� ������");
				pressEnterToContinue();
				continue;
			}
		}

		pressEnterToContinue();
		while (true) {
			System.out.print("������� \"" + newBook.getTitle()
					+ "\" ��� �� ���������� �� �������������� �� �������������? \n1- �� \n ������ ����� ��! ");
			try {
				int angswer = scanner.nextInt();
				if (angswer == 1) {
					System.out.println("�������� ���� �� �������� �� �����������");
					Date date = getDate();
					newBook.setCertificate(new Certificate(date));
				}
			} catch (InputMismatchException e) {
				System.err.println("���� �������� �����.");
				pressEnterToContinue();
				continue;
			}
			break;
		}

		pressEnterToContinue();
		while (true) {
			try {
				System.out.println(
						"����,�������� ����� �� �������������������� \n 1-�������� �� ��� \n 2- �������� �� �����������");
				int answer = 1;
				answer = scanner.nextInt();
				if (answer != 1) {
					System.out.println("�������� �����������, ���������� ������ ��");
					for (int count = 0; count < distributors.size(); count++) {
						System.out.println(count + " - " + distributors.get(count).getNameOfDistributor());
					}
					int choice;
					do {
						choice = scanner.nextInt();
					} while (choice < 0 || choice > distributors.size());
					System.out.println("�������� " + choice);
					newBook.setBookDistributor(distributors.get(choice));

				} else {
					// add new
					newBook.setBookDistributor(addNewBookDistributor());
				}
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ����� �� �������..");
				pressEnterToContinue();
				continue;
			}
			break;
		}

		pressEnterToContinue();
		while (true) {
			try {
				System.out.print("���� �������� ���� �� ����� \"" + newBook.getTitle() + "\" : ");
				double priceOfTheBook = scanner.nextDouble();
				if (priceOfTheBook == 0 || priceOfTheBook < 0) {
					System.out.println("������� �� ���� �� � ��� ����! �������� ��� \n");
					continue;
				}
				newBook.setPrice(priceOfTheBook);
				break;
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ������� ����.. �������� ������.");
				pressEnterToContinue();
			}
		}

		return newBook;

	}

	private BookDistributor addNewBookDistributor() {

		pressEnterToContinue();
		System.out.println("�������� �� ��� �����������");

		BookDistributor bookdistributor = new BookDistributor();
		scanner.nextLine();
		while (true) {

			pressEnterToContinue();
			System.out.println("��� : ");
			String name = scanner.nextLine();
			if (!name.matches(NAME_PATTERN)) {
				System.out.println("��������� ���!! ������ �� �������� ���� 3 �������. �������� ������");

				continue;
			}
			bookdistributor.setNameOfDistributor(name);
			break;

		}
		while (true) {

			pressEnterToContinue();
			System.out.println("������� : ");

			String telephone = scanner.nextLine();
			if (!telephone.matches(TELEPHONE_PATTERN)) {
				System.out.println("�������� ������ �� ������� ���� 10 �����!!");
				continue;
			}
			bookdistributor.setTelephone(telephone);
			break;
		}

		while (true) {

			pressEnterToContinue();
			System.out.println("����� : ");
			String address = scanner.nextLine();
			if (!address.matches(ADDRESS_PATTERN)) {
				System.out.println("������ ������ �� � ���� 5 �����");
				continue;
			}
			bookdistributor.setAddress(address);
			break;
		}

		System.out.println("\n������� �������������������� " + bookdistributor + " \n");

		distributors.add(bookdistributor);
		return bookdistributor;
	}

	private Date getDate() {
		System.out.println("���� �������� ����: ");
		int day = 1, month = 1, year = 2000;
		while (true) {

			pressEnterToContinue();
			try {
				System.out.print("\n���� �������� ���: ");
				day = scanner.nextInt();
				if (day < 1 || day > 31) {

					System.out.print("��������� ��� �� ������( " + day + " ),��������� ������! \n\n ");
					continue;
				}
				System.out.print("\n���� �������� �����: ");
				month = scanner.nextInt();
				if (month < 1 || month > 12) {

					System.out.print("\n ��������� �����  " + month);
					continue;
				}
				System.out.print("\n���� �������� ������: ");
				year = scanner.nextInt();

				if (year < 1500 || year > 3000) {
					System.out.println("��������� ������ " + year + ". �������� ������.");
					continue;
				}
				if (LocalDate.of(year, month, day).isAfter(LocalDate.now())) {
					System.err.println(LocalDate.of(year, month, day) + " ���� ���,��� ��� �� � ��������! \n"
							+ "������� ��� �" + LocalDate.now());
					pressEnterToContinue();
					continue;
				}

				break;
			} catch (InputMismatchException e) {
				System.err.println("��������� ��������� �����!���� ���������� �����. �������� ������");
				pressEnterToContinue();
			}
		}

		return new Date(year, month, day);
	}

	public void endWork() {
		System.out.println("����������� ��� ....");

		information.saveInformation(books, distributors);// not impl
		if (scanner != null) {
			scanner.close();
			System.out.println("stream closed succsesfullty");
		}
	}

}
