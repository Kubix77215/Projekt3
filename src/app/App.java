package app;

import java.util.Scanner;
import repo.Database;
import repo.DbService;
import service.Book;

public class App {
    private boolean isRunning;
    public App() {
        this.isRunning = false;
    }
    public void start() {
        Scanner sc = new Scanner(System.in);
        isRunning = true;
        System.out.println("uruchamiam aplikacje");
        Database booksDB = new Database("booksDB"); // łączy z bazą danych
        while (isRunning) {
            System.out.println("KSIAZKI");
            System.out.println("1 - DODAJ\n2 - USUN\n3 - EDYTUJ");
            int choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("tytul: ");
                    String title = sc.nextLine();
                    System.out.println("autor: ");
                    String author = sc.nextLine();
                    System.out.println("rok: ");
                    int year = sc.nextInt(); sc.nextLine();
                    System.out.println("wartosc [$]: ");
                    int value = sc.nextInt(); sc.nextLine();
                    Book book = new Book(title, author, year, value);
                    DbService.addBookToDatabase(book, booksDB);
                    DbService.infoFromDatabase(book.title, booksDB);
                }
                case 2 -> {
                    System.out.println("nazwa: ");
                    String nazwa = sc.nextLine();
                    DbService.removeBookFromDatabase(nazwa, booksDB);
                }
                case 3 -> {
                    System.out.println("tytul: ");
                    String tytul = sc.nextLine();
                    System.out.println("autor: ");
                    String autor = sc.nextLine();
                    System.out.println("rok: ");
                    int rok = sc.nextInt(); sc.nextLine();
                    System.out.println("wartosc: ");
                    int wartosc = sc.nextInt(); sc.nextLine();
                    Book book = new Book(tytul, autor, rok, wartosc);
                    DbService.updateBook(book, booksDB);
                }
            }
        }
        sc.close();
    }
    public void stop() {
        isRunning = false;
        System.out.println("aplikacja zostala wylaczona");
    }
}