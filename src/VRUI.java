import java.util.Scanner;

public class VRUI {
	private static Scanner scanner = new Scanner(System.in) ;
	private static VRController controller;


	public static void main(String[] args) {
		VRUI ui = new VRUI() ;
		controller = new VRController();

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.register("customer") ; break ;
				case 4: ui.register("video") ; break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: controller.init() ; break ;
				default: break ;
			}
		}
		System.out.println("Bye");
	}

	public void listVideos() {

		controller.listVideos();
	}

	public void listCustomers() {
		controller.listCustomers();
	}

	public int showCommand() {
		System.out.println("\nSelect a command !");
		System.out.println("\t 0. Quit");
		System.out.println("\t 1. List customers");
		System.out.println("\t 2. List videos");
		System.out.println("\t 3. Register customer");
		System.out.println("\t 4. Register video");
		System.out.println("\t 5. Rent video");
		System.out.println("\t 6. Return video");
		System.out.println("\t 7. Show customer report");
		System.out.println("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;

	}

	public void clearRentals() {
		System.out.println("Enter customer name: ");
		String customerName = scanner.next();

		controller.clearRentals(customerName);
	}

	public void returnVideo() {
		Customer foundCustomer = enterCustomer();
		if (foundCustomer == null) return;

		String videoTitle = enterVideo();
		controller.returnVideo(foundCustomer, videoTitle);
	}

	private String enterVideo() {
		System.out.println("Enter video title to return: ");
		String videoTitle = scanner.next();
		return videoTitle;
	}

	private Customer enterCustomer() {
		System.out.println("Enter customer name: ");
		String customerName = scanner.next();

		return controller.findCustomer(customerName);
	}

	public void getCustomerReport() {
		System.out.println("Enter customer name: ");
		String customerName = scanner.next();

		Customer foundCustomer = controller.findCustomer(customerName);

		if (foundCustomer == null) {
			System.out.println("No customer found");
		} else {
			String result = foundCustomer.getReport();
			System.out.println(result);
		}
	}

	public void rentVideo() {
		System.out.println("Enter customer name: ");
		String customerName = scanner.next();

		Customer foundCustomer = controller.findCustomer(customerName);

		if (foundCustomer == null) return;

		System.out.println("Enter video title to rent: ");
		String videoTitle = scanner.next();

		controller.rent(videoTitle, foundCustomer);
	}

	public void register(String object) {
		if (object.equals("customer")) {
			System.out.println("Enter customer name: ");
			String name = scanner.next();
			controller.addCustomer(name);
		} else {
			System.out.println("Enter video title to register: ");
			String title = scanner.next();

			System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
			int videoType = scanner.nextInt();

			System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
			int priceCode = scanner.nextInt();

			controller.addVideo(title, videoType, priceCode);
		}
	}

}
