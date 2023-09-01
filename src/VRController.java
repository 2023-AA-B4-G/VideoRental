import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VRController {

    private List<Customer> customers = new ArrayList<Customer>() ;

    private List<Video> videos = new ArrayList<Video>() ;

    void clearRentals(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer == null) {
            System.out.println("No customer found");
        } else {
            System.out.println("Name: " + foundCustomer.getName() +
                    "\tRentals: " + foundCustomer.getRentals().size());
            for (Rental rental : foundCustomer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }

            List<Rental> rentals = new ArrayList<Rental>();
            foundCustomer.setRentals(rentals);
        }
    }

    public void listVideos() {
        System.out.println("List of videos");

        for (Video video : videos) {
            System.out.println("Price code: " + video.getPriceCode() + "\tTitle: " + video.getTitle());
        }
        System.out.println("End of list");
    }

    public void listCustomers() {
        System.out.println("List of customers");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName() +
                    "\tRentals: " + customer.getRentals().size());
            for (Rental rental : customer.getRentals()) {
                System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ");
                System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode());
            }
        }
        System.out.println("End of list");
    }

    public void returnVideo(String customerName, String videoTitle) {
        Customer foundCustomer = foundCustomer(customerName);

        List<Rental> customerRentals = foundCustomer.getRentals();
        for (Rental rental : customerRentals) {
            if (rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented()) {
                rental.returnVideo();
                rental.getVideo().setRented(false);
                break;
            }
        }
    }

    boolean isCustomerExist(String customerName) {
        return foundCustomer(customerName) != null;
    }
    Customer foundCustomer(String customerName) {
        Customer foundCustomer = null;
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                foundCustomer = customer;
                break;
            }
        }
        if (foundCustomer == null) return null;
        return foundCustomer;
    }

    void addVideo(String title, int videoType, int priceCode) {
        Date registeredDate = new Date();
        Video video = new Video(title, videoType, priceCode, registeredDate);
        videos.add(video);
    }

    void addCustomer(String name) {
        Customer customer = new Customer(name);
        customers.add(customer);
    }

    Video findVideo(String videoTitle) {
        Video foundVideo = null;
        for (Video video : videos) {
            if (video.getTitle().equals(videoTitle) && video.isRented() == false) {
                foundVideo = video;
                break;
            }
        }
        return foundVideo;
    }

    void createRental(Video foundVideo, Customer foundCustomer) {
        Rental rental = new Rental(foundVideo);
        foundVideo.setRented(true);

        List<Rental> customerRentals = foundCustomer.getRentals();
        customerRentals.add(rental);
        foundCustomer.setRentals(customerRentals);
    }

    void rentVideo(String videoTitle, String CustomerName) {
        Customer foundCustomer = foundCustomer(CustomerName);
        Video foundVideo = findVideo(videoTitle);
        if (foundVideo == null) return;

        createRental(foundVideo, foundCustomer);
    }

    String getReport(String CustomerName) {
        Customer foundCustomer = foundCustomer(CustomerName);
        if (foundCustomer == null) {
            return "No customer found";
        } else {
            String result = foundCustomer.getReport();
            return result;
        }
    }

    void init() {
        Customer james = new Customer("James") ;
        Customer brown = new Customer("Brown") ;
        customers.add(james) ;
        customers.add(brown) ;

        Video v1 = new Video("v1", Video.CD, Video.REGULAR, new Date()) ;
        Video v2 = new Video("v2", Video.DVD, Video.NEW_RELEASE, new Date()) ;
        videos.add(v1) ;
        videos.add(v2) ;

        Rental r1 = new Rental(v1) ;
        Rental r2 = new Rental(v2) ;

        james.addRental(r1) ;
        james.addRental(r2) ;
    }
}