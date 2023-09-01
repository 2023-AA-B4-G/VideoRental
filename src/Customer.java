import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);

	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = this.rentals;

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			result += each.getRentalReport();
			totalCharge += each.getEachCharge();
			totalPoint += each.getEachPoint() ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		if ( totalPoint >= 10 ) {
			System.out.println("Congrat! You earned one free coupon");
		}
		if ( totalPoint >= 30 ) {
			System.out.println("Congrat! You earned two free coupon");
		}
		return result ;
	}

	void clearRental() {
		List<Rental> rentals = new ArrayList<Rental>();
		setRentals(rentals);
	}

	String getRentalList() {
		String result = "Name: " + getName() + "\tRentals: " + rentals.size();
		for (Rental rental : rentals) {
			result += rental.getRentalInfo();
		}
		return result;
	}

	void returnVideo(String videoTitle) {
		for (Rental rental : rentals) {
			if (rental.returnVideo(videoTitle)) break;
		}
	}

	void rentVideo(Video foundVideo) {
		Rental rental = new Rental(foundVideo);
		rentals.add(rental);
		setRentals(rentals);
	}
}
