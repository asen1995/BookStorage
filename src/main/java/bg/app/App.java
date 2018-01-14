package bg.app;

import bg.action.BookShop;

public class App {

	public static void main(String[] args) {
	
		BookShop shop = new BookShop();
		
		shop.startWork();
		
		shop.endWork();
		
	}
}
