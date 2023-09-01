import java.util.Date;

abstract public  class Video {
	private String title ;

	private PriceCode priceCode ;

	private int videoType ;

	private Date registeredDate ;
	private boolean rented ;

	public Video(String title, PriceCode priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	abstract public int  getLateReturnPointPenalty();
	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	abstract public int getLimit();
}
