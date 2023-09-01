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
				case 3: ui.registerCustomer() ; break ;
				case 4: ui.registerVideo() ; break ;
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


	public void clearRentals() {
		String customerName = inputCustomerName();
		controller.clearRentals(customerName);
	}

	public void returnVideo() {
		String customerName = inputCustomerName();
		if (!controller.isCustomerExist(customerName)) {
			return;
		}
		String videoTitle = inputVideoTitle();

		controller.returnVideo(customerName, videoTitle);
	}

	private Customer enterCustomer() {
		String customerName = inputCustomerName();
		return controller.foundCustomer(customerName);
	}

	public void getCustomerReport() {
		String customerName = inputCustomerName();
		String report = controller.getReport(customerName);

		System.out.println(report);
	}

	public void rentVideo() {
		String customerName = inputCustomerName();
		if (!controller.isCustomerExist(customerName)) {
			return;
		}

		String videoTitle = inputVideoTitle();
		controller.rentVideo(videoTitle, customerName);
	}

	private void registerCustomer() {
		String name = inputCustomerName();
		controller.addCustomer(name);
	}

	private void registerVideo() {
		String title = inputVideoTitle();
		int videoType = inputVideoType();
		int priceCode = inputVideoPriceCode();
		controller.addVideo(title, videoType, priceCode);
	}

	private int inputVideoPriceCode() {
		System.out.println("Enter price code( 1 for Regular, 2 for New Release ):");
		int priceCode = scanner.nextInt();
		return priceCode;
	}

	private int inputVideoType() {
		System.out.println("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):");
		int videoType = scanner.nextInt();
		return videoType;
	}

	private String inputVideoTitle() {
		System.out.println("Enter video title: ");
		String videoTitle = scanner.next();
		return videoTitle;
	}

	private String inputCustomerName() {
		System.out.println("Enter customer name: ");
		String customerName = scanner.next();
		return customerName;
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
}
