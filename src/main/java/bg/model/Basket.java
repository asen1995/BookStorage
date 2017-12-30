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
		System.out.println("���� \"�������\"");

		while (true) {

			pressEnterToContinue();
			try {
				System.out.println("\n ���� �������� ����� ������ �� ���������\n" + "\n0 -�� ���������� ���������"
						+ "\n1 -�� �������� ���� �����" + "\n2 - �� ���������� ����� �� ���������"
						+ "\n3 - �� ��������� ��� �������" + "\n4 - �� �������� ���������");

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
				System.err.println("��������� ������ �����.���� �������� ������� �� �������.");
				continue;
			}
		}

	}

	private void purchase() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("��������� � ������...");
		}
		System.out.println("�����������... ���� ������..");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double price = basketInformation();
		while (true) {
			System.out.println(" �� �������� �� ? \n1-�� \n - ����� ����� ����� ��");
			try {
				int choice = scanner.nextInt();
				if (choice == 1) {
					while (true) {
						System.out.print("������� : ");
						double clientMoney = scanner.nextDouble();
						if (clientMoney < price) {
							System.out.println("�� �� �������� " + (price - clientMoney)
									+ " �� �� �������� ������������ �� ���������");
						} else {
							System.out.println("����� : " + (clientMoney - price));
							break;
						}

					}
				} else {
					return;
				}
				break;
			} catch (InputMismatchException e) {
				System.err.println("���� �������� ����� ���� �������!!");
				pressEnterToContinue();
				continue;
			}

		}

	}

	private void cancelSale() {
		pressEnterToContinue();
		System.out.println("��������� � ��������");
		this.booksBasket = null;
	}

	private void removeBookFromTheBasket() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("Ko������� � ������");
			return;
		}
		for (int i = 0; i < booksBasket.size(); i++) {
			Book book = booksBasket.get(i);
			System.out.println("[" + i + "] \"" + book.getTitle() + "\" � ���� : " + book.getPrice());

		}

		while (true) {
			try {
				System.out.println("���� �������� ������� �� �������,����� ������ �� ���������� �� ���������");
				int bookIndex = scanner.nextInt();
				if (bookIndex < 0 || bookIndex > booksBasket.size() - 1) {
					System.out.println("���������� ����� � ����� ������� ����������");

				}
				System.out.println("������� \"" + booksBasket.get(bookIndex).getTitle() + "\" ���� ����������");
				booksBasket.remove(bookIndex);
				break;
			} catch (InputMismatchException e) {
				System.out.println("��������� ������ �����.���� �������� �����!");
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
				System.out.println("[" + i + "] \"" + book.getTitle() + "\" � ���� : " + book.getPrice());

			}

			while (true) {
				try {
					System.out.println("���� �������� ������� �� �������,����� ������ �� �������� � ���������");
					int bookIndex = scanner.nextInt();
					if (bookIndex < 0 || bookIndex > allBooks.size() - 1) {
						System.out.println("���������� ����� � ����� ������� ����������");
						break;
					}
					booksBasket.add(allBooks.get(bookIndex));
					System.out.println("������� " + allBooks.get(bookIndex).getTitle()
							+ " � �������� � ��������� ��! �� ���������� �� �� �������� ?\n 1-�� ������ ����� ��");
					int ans = scanner.nextInt();
					if (ans != 1) {
						break;
					}
				} catch (InputMismatchException e) {
					System.err.println("���� �������� �����. �������� ������.");
				}
			}
		} else {
			System.out.println("��������� ���� ����� �� ���������.");
		}
	}

	private void printContentOfBasket() {
		pressEnterToContinue();
		if (booksBasket.size() == 0) {
			System.out.println("��������� � ������! ");
		} else {
			basketInformation();
		}

	}

	private double basketInformation() {
		System.out.println("������������ �� ��������� � :" + booksBasket.size() + " �����.");
		double currentPrice = 0.0;
		for (int i = 0; i < booksBasket.size(); i++) {
			Book book = booksBasket.get(i);
			System.out.println(book.getTitle() + "\" � ���� : " + book.getPrice());
			currentPrice += book.getPrice();

		}
		System.out.println("���� �� ��������� " + currentPrice);
		return currentPrice;
	}

}
