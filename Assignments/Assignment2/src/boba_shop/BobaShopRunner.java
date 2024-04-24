package boba_shop;

public class BobaShopRunner {

	public static void main(String[] args) {
		//Make a valid BubbleTea object
		Tea blackTea = new Tea("Black Tea");
		Milk wholeMilk = new Milk("Whole Milk");
		Flavoring ube = new Flavoring("Ube");
		Topping redBean = new Topping("Red Bean");
		BubbleTea b1 = new BubbleTea("Large", blackTea, wholeMilk, ube, 1, redBean, true);
		//Printing out an object will automatically call its toString method
		System.out.println(b1);
				
		//TODO: test an invalid BubbleTea object
				
		//Get the same tea through the menu
		System.out.println(Menu.makeBubbleTea("Large", blackTea, wholeMilk, ube, 1, redBean, true));
				
		//Get the list of specials from the menu:
		System.out.println("\nSpecials:");
		System.out.println(Menu.getSpecials());
				
		//Get special number 2
		System.out.println(Menu.getSpecial("Large", 5));
		System.out.println();
				
		//TODO: test the receipt object
				
		//Order a tea through a cashier
		Cashier cashier = new Cashier("Jim");
		System.out.println("Order with Jim: ");
		System.out.println(cashier.orderBubbleTea("Large", blackTea));
		System.out.println();
		System.out.println(cashier.orderSpecial("Small", 3, 15.0));


	}

}
