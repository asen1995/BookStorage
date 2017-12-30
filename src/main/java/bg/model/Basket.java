package bg.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static bg.action.BookShop.*;

public class Basket {

	private List<Book> booksBasket;
	private List<Book> allBooks;
	private Scanner scanner;

	public Basket() {
		booksBasket = new ArrayList<Book>();
		allBooks = new ArrayList<Book>();
	}

	public void salesMenu(List<Book> books, Scanner scanner) {
		this.allBooks = books;
		this.scanner = scanner;
		this.booksBasket = new ArrayList<Book>();
		System.out.println("Меню \"покупки\"");

		while (true) {

			pressEnterToContinue();
			try {
				System.out.println("\n Моля изберете какво искате да направите\n" + "\n0 -Да прегледате кошницата"
						+ "\n1 -Да добавите нова книга" + "\n2 - Да премахнете книга от кошницата"
						+ "\n3 - да преминете към плащане" + "\n4 - да откажете покупката");

				int answer = scanner.nextInt();

				switch (answer) {
				case 0: {
					this.printContentOfBasket();
					break;
				}
				case 1: {
					this.addNewBookInTheBasket();
					break;
				}

				case 2: {
					this.removeBookFromTheBasket();
					break;
				}

				case 3: {
					this.purchase();
					return;
				}

				case 4: {
					this.cancelSale();
					return;
				}
				default:
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("Въвеждате грешни данни.Моля въведете цифрата на опцията.");
				continue;
			}
		}

	}

	private void purchase() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("Кошницата е празна...");
		}
		System.out.println("Калкулираме... един момент..");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double price = basketInformation();
		while (true) {
			System.out.println(" Ще закупите ли ? \n1-да \n - всяка друга цифра не");
			try {
				int choice = scanner.nextInt();
				if (choice == 1) {
					while (true) {
						System.out.print("Плащате : ");
						double clientMoney = scanner.nextDouble();
						if (clientMoney < price) {
							System.out.println("Не ви достигат " + (price - clientMoney)
									+ " за да закупите съдържанието на кошницата");
						} else {
							System.out.println("Ресто : " + (clientMoney - price));
							break;
						}

					}
				} else {
					return;
				}
				break;
			} catch (InputMismatchException e) {
				System.err.println("Моля въведете цифра като отговор!!");
				pressEnterToContinue();
				continue;
			}

		}

	}

	private void cancelSale() {
		pressEnterToContinue();
		System.out.println("Покупката е отказана");
		this.booksBasket = null;
	}

	private void removeBookFromTheBasket() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("Koшницата е празна");
			return;
		}
		for (int i = 0; i < booksBasket.size(); i++) {
			Book book = booksBasket.get(i);
			System.out.println("[" + i + "] \"" + book.getTitle() + "\" с цена : " + book.getPrice());

		}

		while (true) {
			try {
				System.out.println("Моля въведете цифрата на книгата,която искате да премахнете от кошницата");
				int bookIndex = scanner.nextInt();
				if (bookIndex < 0 || bookIndex > booksBasket.size() - 1) {
					System.out.println("въведената цифра е извън рамките посочените");

				}
				System.out.println("Книгата \"" + booksBasket.get(bookIndex).getTitle() + "\" беше премахната");
				booksBasket.remove(bookIndex);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Въвеждате грешни данни.Моля въведете цифра!");
				pressEnterToContinue();
				continue;
			}
		}
	}

	private void addNewBookInTheBasket() {
		pressEnterToContinue();
		if (allBooks.size() > 0) {
			for (int i = 0; i < allBooks.size(); i++) {
				Book book = allBooks.get(i);
				System.out.println("[" + i + "] \"" + book.getTitle() + "\" с цена : " + book.getPrice());

			}

			while (true) {
				try {
					System.out.println("Моля въведете цифрата на книгата,която искате да добавите в кошницата");
					int bookIndex = scanner.nextInt();
					if (bookIndex < 0 || bookIndex > allBooks.size() - 1) {
						System.out.println("въведената цифра е извън рамките посочените");
						break;
					}
					booksBasket.add(allBooks.get(bookIndex));
					System.out.println("Книгата " + allBooks.get(bookIndex).getTitle()
							+ " е добавена в кошницата ви! Ще продължите ли да добавяте ?\n 1-да всичко друго не");
					int ans = scanner.nextInt();
					if (ans != 1) {
						break;
					}
				} catch (InputMismatchException e) {
					System.err.println("Моля въведете цифра. Опитайте отново.");
				}
			}
		} else {
			System.out.println("Магазинът няма книги за продаване.");
		}
	}

	private void printContentOfBasket() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("Кошницата е празна! ");
		} else {
			basketInformation();
		}

	}

	private double basketInformation() {
		System.out.println("съдържанието на кошницата е :" + booksBasket.size() + " книги.");
		double currentPrice = 0.0;
		for (int i = 0; i < booksBasket.size(); i++) {
			Book book = booksBasket.get(i);
			System.out.println(book.getTitle() + "\" с цена : " + book.getPrice());
			currentPrice += book.getPrice();

		}
		System.out.println("Цена на кошницата " + currentPrice);
		return currentPrice;
	}

}
