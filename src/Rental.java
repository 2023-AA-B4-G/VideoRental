import java.util.Date;

public class Rental {
	private Video video ;
	private int status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = 0 ;
		rentDate = new Date() ;
	}

	public String getRentalInfo() {
		return "\tTitle: " + getVideo().getTitle() + " " + "\tPrice Code: " + getVideo().getPriceCode();
	}

	public String getRentalReport() {
		return "\t" + getVideo().getTitle() + "\tDays rented: " + getDaysRented() + "\tCharge: " + getDaysRented()
				+ "\tPoint: " + getEachPoint() + "\n";
	}

	public double getEachCharge() {
		double eachCharge = 0;
		switch (getVideo().getPriceCode()) {
		case Video.REGULAR:
			eachCharge += 2;
			if (getDaysRented() > 2)
				eachCharge += (getDaysRented() - 2) * 1.5;
			break;
		case Video.NEW_RELEASE:
			eachCharge = getDaysRented() * 3;
			break;
		}
		return eachCharge;
	}

	public int getDaysRented() {
		int daysRented = 0;
		if (getStatus() == 1) { // returned Video
			long diff = getReturnDate().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		} else { // not yet returned
			long diff = new Date().getTime() - getRentDate().getTime();
			daysRented = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
		}
		return daysRented;
	}

	public int getEachPoint() {
		int point = 1;

		if ((getVideo().getPriceCode() == Video.NEW_RELEASE) )
			point++;

		if ( getDaysRented() > getDaysRentedLimit() )
			point -= Math.min(point, getVideo().getLateReturnPointPenalty()) ;

		return point;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == 1 ) {
			this.status = 1;
			returnDate = new Date() ;
		}
	}
	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getDaysRentedLimit() {
		if ( getDaysRented() <= 2) {
			return 0 ;
		} else {
			return video.getLimit();
		}
	}
}
