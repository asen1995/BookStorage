package bg.app;

import java.time.LocalDate;
import java.util.Date;

import bg.action.BookShop;

public class App {

	public static void main(String[] args) {
	
		BookShop shop = new BookShop();
		
		shop.startWork();
		
		shop.endWork();
		
	}
}
