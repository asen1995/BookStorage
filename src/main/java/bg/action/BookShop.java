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

		System.out.println("добре дошли в нашият магазин за учебници и книги.\n");
		do {
			pressEnterToContinue();
			System.out.println("\n\n Какво бихте желали да направите? Моля изберете опция \n"
					+ "0 - да си тръгнете от магазина \n" + "1 - да разгледате наличните книги \n"
					+ "2 - да преминете към меню \"покупки\" \n" + "3 - да добавите нова книга към библиотеката ни \n"
					+ "4 - да добавите нов книгоразпространител към библиотеката ни \n");
			try {
				System.out.print("Въведете цифра:");
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
							.println("Вие избрахте грешна/несъществуваща опция. Моля натиснете Enter за продължаване");
					scanner.nextLine();
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("ERROR!! Вие не въведохте цифра,ами нещо друго. Моля опитайте отново!");
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
		System.out.println("Вие избрахте да въведете нова книга");
		Book newBook = new Book();

		String title = null;
		int attempts = 1;
		while (true) {

			System.out.print("\n Заглавиеd: ");
			title = scanner.nextLine();
			if (title.length() < 3 || !title.matches(TITLE_PATTERN)) {
				System.out.println("Вашето заглавие е невалидно или празно,опитайте отново.");
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
			System.out.print("\nМоля въведете автор : ");
			author = scanner.nextLine();
			if (!author.matches(NAME_PATTERN)) {
				System.out.println(
						"Вашето авторско име е невалидно или празно,трябва да е поне 3 букви,опитайте отново.");
				continue;
			} else {
				authors.add(author);
				System.out.println("\n Авторът е добавен, искате ли да добавите още автори? \n1-да\n всичко друго-не ");
				if (scanner.nextLine().equals("1")) {

					pressEnterToContinue();
					continue;
				} else {
					if (authors.size() < 1) {
						System.out.println("Трябва да въведете поне един автор!! Моля направете го!");

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
			System.out.println("Моля въведете издание на книгата - в цифри");
			try {
				int izdanie = scanner.nextInt();
				newBook.setIzdanie(izdanie);
				break;
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифри за тиража .. ! Опитайте отново");
				pressEnterToContinue();
			}
		}

		pressEnterToContinue();

		while (true) {
			System.out.println("Моля въведете ISBN на книгата - в цифри");
			try {
				long Isbn = scanner.nextLong();
				newBook.setISBN(Isbn);
				break;
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифри за ISBN .. ! Опитайте отново");
				pressEnterToContinue();
			}
		}

		pressEnterToContinue();
		System.out.println("Моля въведете дата на излизане на книгата:");
		newBook.setDate(getDate());

		pressEnterToContinue();
		System.out.println("Моля въведете тираж на книгата - в цифри");
		while (true) {

			try {
				int tiraj = scanner.nextInt();
				newBook.setTiraj(Integer.toString(tiraj));
				break;
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифри за тиража .. ! Опитайте отново");
				pressEnterToContinue();
				continue;
			}
		}

		pressEnterToContinue();
		while (true) {
			System.out.print("Книгата \"" + newBook.getTitle()
					+ "\" има ли сертификат от министерството на образованието? \n1- да \n всичко друго НЕ! ");
			try {
				int angswer = scanner.nextInt();
				if (angswer == 1) {
					System.out.println("Въведете дата на издаване на сертификата");
					Date date = getDate();
					newBook.setCertificate(new Certificate(date));
				}
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифра.");
				pressEnterToContinue();
				continue;
			}
			break;
		}

		pressEnterToContinue();
		while (true) {
			try {
				System.out.println(
						"Моля,изберете опция за книгоразпространител \n 1-въвежане на нов \n 2- избиране на съществуващ");
				int answer = 1;
				answer = scanner.nextInt();
				if (answer != 1) {
					System.out.println("Изберете съществуващ, посочвайки номера му");
					for (int count = 0; count < distributors.size(); count++) {
						System.out.println(count + " - " + distributors.get(count).getNameOfDistributor());
					}
					int choice;
					do {
						choice = scanner.nextInt();
					} while (choice < 0 || choice > distributors.size());
					System.out.println("избрахте " + choice);
					newBook.setBookDistributor(distributors.get(choice));

				} else {
					// add new
					newBook.setBookDistributor(addNewBookDistributor());
				}
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифра за опцията..");
				pressEnterToContinue();
				continue;
			}
			break;
		}

		pressEnterToContinue();
		while (true) {
			try {
				System.out.print("Моля въведете цена на книга \"" + newBook.getTitle() + "\" : ");
				double priceOfTheBook = scanner.nextDouble();
				if (priceOfTheBook == 0 || priceOfTheBook < 0) {
					System.out.println("Книгата не може да е без пари! Опитайте пак \n");
					continue;
				}
				newBook.setPrice(priceOfTheBook);
				break;
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете валидна цена.. Опитайте отново.");
				pressEnterToContinue();
			}
		}

		return newBook;

	}

	private BookDistributor addNewBookDistributor() {

		pressEnterToContinue();
		System.out.println("Добавяне на нов дистрибутор");

		BookDistributor bookdistributor = new BookDistributor();
		scanner.nextLine();
		while (true) {

			pressEnterToContinue();
			System.out.println("ИМЕ : ");
			String name = scanner.nextLine();
			if (!name.matches(NAME_PATTERN)) {
				System.out.println("НЕВАЛИДНО ИМЕ!! трябва да въведете поне 3 символа. ОПИТАЙТЕ ОТНОВО");

				continue;
			}
			bookdistributor.setNameOfDistributor(name);
			break;

		}
		while (true) {

			pressEnterToContinue();
			System.out.println("телефон : ");

			String telephone = scanner.nextLine();
			if (!telephone.matches(TELEPHONE_PATTERN)) {
				System.out.println("телефона трябва да съдържа поне 10 цифри!!");
				continue;
			}
			bookdistributor.setTelephone(telephone);
			break;
		}

		while (true) {

			pressEnterToContinue();
			System.out.println("адрес : ");
			String address = scanner.nextLine();
			if (!address.matches(ADDRESS_PATTERN)) {
				System.out.println("адреса трябва да е поне 5 букви");
				continue;
			}
			bookdistributor.setAddress(address);
			break;
		}

		System.out.println("\nДобавен книгоразпространител " + bookdistributor + " \n");

		distributors.add(bookdistributor);
		return bookdistributor;
	}

	private Date getDate() {
		System.out.println("Моля въведете дата: ");
		int day = 1, month = 1, year = 2000;
		while (true) {

			pressEnterToContinue();
			try {
				System.out.print("\nМоля въведете ден: ");
				day = scanner.nextInt();
				if (day < 1 || day > 31) {

					System.out.print("Невалиден ден от месеца( " + day + " ),започнете отново! \n\n ");
					continue;
				}
				System.out.print("\nМоля въведете месец: ");
				month = scanner.nextInt();
				if (month < 1 || month > 12) {

					System.out.print("\n Невалиден месец  " + month);
					continue;
				}
				System.out.print("\nМоля въведете година: ");
				year = scanner.nextInt();

				if (year < 1500 || year > 3000) {
					System.out.println("Невалидна година " + year + ". Опитайте отново.");
					continue;
				}
				if (LocalDate.of(year, month, day).isAfter(LocalDate.now())) {
					System.err.println(LocalDate.of(year, month, day) + " този ден,все още не е настъпил! \n"
							+ "Днешния ден е" + LocalDate.now());
					pressEnterToContinue();
					continue;
				}

				break;
			} catch (InputMismatchException e) {
				System.err.println("Въвеждате невалидни данни!Моля въвеждайте числа. Опитайте отново");
				pressEnterToContinue();
			}
		}

		return new Date(year, month, day);
	}

	public void endWork() {
		System.out.println("заповядайте пак ....");

		information.saveInformation(books, distributors);// not impl
		if (scanner != null) {
			scanner.close();
			System.out.println("stream closed succsesfullty");
		}
	}

}
