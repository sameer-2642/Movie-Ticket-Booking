
import java.util.*;

class Movie {

    String title;
    double rating;
    Map<String, boolean[][]> showtimes;

    Movie(String title, double rating, List<String> timings) {
        this.title = title;
        this.rating = rating;
        this.showtimes = new HashMap<>();
        for (String time : timings) {
            // Initialize a 5x5 seating chart for each showtime
            this.showtimes.put(time, new boolean[5][5]);
        }
    }

    void displayShowtimes() {
        System.out.println("\n" + title + " (Rating: " + rating + ")");
        for (String time : showtimes.keySet()) {
            System.out.println(" - Showtime: " + time);
        }
    }

    void displaySeats(String time) {
        if (!showtimes.containsKey(time)) {
            System.out.println("Invalid showtime!");
            return;
        }
        boolean[][] seats = showtimes.get(time);
        System.out.println("\nSeating for " + title + " at " + time + ":");
        System.out.println("   1  2  3  4  5");
        for (int i = 0; i < seats.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "[X]" : "[O]");
            }
            System.out.println();
        }
    }

    boolean bookSeat(String time, int row, int col) {
        if (!showtimes.containsKey(time)) {
            System.out.println("Invalid showtime!");
            return false;
        }
        boolean[][] seats = showtimes.get(time);
        if (row < 1 || row > 5 || col < 1 || col > 5) {
            System.out.println("Invalid seat selection!");
            return false;
        }
        if (seats[row - 1][col - 1]) {
            System.out.println("Seat already booked!");
            return false;
        }
        seats[row - 1][col - 1] = true;
        System.out.println("Seat booked successfully!");
        return true;
    }

    boolean cancelSeat(String time, int row, int col) {
        if (!showtimes.containsKey(time)) {
            System.out.println("Invalid showtime!");
            return false;
        }
        boolean[][] seats = showtimes.get(time);
        if (row < 1 || row > 5 || col < 1 || col > 5) {
            System.out.println("Invalid seat selection!");
            return false;
        }
        if (!seats[row - 1][col - 1]) {
            System.out.println("Seat is already vacant!");
            return false;
        }
        seats[row - 1][col - 1] = false;
        System.out.println("Seat canceled successfully!");
        return true;
    }
}

public class MovieBookingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize movies
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", 8.8, Arrays.asList("12:00 PM", "3:00 PM", "6:00 PM")));
        movies.add(new Movie("The Dark Knight", 9.0, Arrays.asList("1:00 PM", "4:00 PM", "7:00 PM")));
        movies.add(new Movie("Interstellar", 8.6, Arrays.asList("2:00 PM", "5:00 PM", "8:00 PM")));

        while (true) {
            System.out.println("\n=== Movie Booking System ===");
            System.out.println("1. View Movies and Showtimes");
            System.out.println("2. View Seat Availability");
            System.out.println("3. Book a Ticket");
            System.out.println("4. Cancel a Ticket");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("\nAvailable Movies:");
                for (int i = 0; i < movies.size(); i++) {
                    System.out.println((i + 1) + ". " + movies.get(i).title);
                }
            } else if (choice == 2) {
                System.out.print("Enter movie number: ");
                int movieChoice = scanner.nextInt();
                if (movieChoice < 1 || movieChoice > movies.size()) {
                    System.out.println("Invalid choice!");
                    continue;
                }
                Movie movie = movies.get(movieChoice - 1);
                movie.displayShowtimes();
                System.out.print("Enter showtime: ");
                scanner.nextLine(); // consume newline
                String time = scanner.nextLine();
                movie.displaySeats(time);
            } else if (choice == 3) {
                System.out.print("Enter movie number: ");
                int movieChoice = scanner.nextInt();
                if (movieChoice < 1 || movieChoice > movies.size()) {
                    System.out.println("Invalid choice!");
                    continue;
                }
                Movie movie = movies.get(movieChoice - 1);
                movie.displayShowtimes();
                System.out.print("Enter showtime: ");
                scanner.nextLine(); // consume newline
                String time = scanner.nextLine();
                System.out.print("Enter row (1-5): ");
                int row = scanner.nextInt();
                System.out.print("Enter column (1-5): ");
                int col = scanner.nextInt();
                movie.bookSeat(time, row, col);
            } else if (choice == 4) {
                System.out.print("Enter movie number: ");
                int movieChoice = scanner.nextInt();
                if (movieChoice < 1 || movieChoice > movies.size()) {
                    System.out.println("Invalid choice!");
                    continue;
                }
                Movie movie = movies.get(movieChoice - 1);
                movie.displayShowtimes();
                System.out.print("Enter showtime: ");
                scanner.nextLine(); // consume newline
                String time = scanner.nextLine();
                System.out.print("Enter row (1-5): ");
                int row = scanner.nextInt();
                System.out.print("Enter column (1-5): ");
                int col = scanner.nextInt();
                movie.cancelSeat(time, row, col);
            } else if (choice == 5) {
                System.out.println("Thank you for using the Movie Booking System!");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
