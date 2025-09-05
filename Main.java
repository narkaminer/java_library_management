// Assignment: 1
// Student: Michael Kaminer


import HW1.DataStructure;
import HW1.Library;
/**
 * This class contains the main method to test the Library functionality.
 */
public class Main {
    /**
     * The main method to test the Library functionality.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        Library myLib = new Library();
        Library.idx =111;
        myLib.addBook("The Dark Tower: The Gunslinger","Stephen King");
        myLib.addBook("11/22/63","Stephen King");
        myLib.addBook("Crime and Punishment","Dostoevsky");
        myLib.addBook("The Brothers Karamazov","Dostoevsky");
        myLib.addBook("Island","Aldous Huxley");
        myLib.addBook("Brave New World","Aldous Huxley");
        myLib.addBook("1984","George Orwell");
        myLib.addBook("The Castle","Franz Kafka");
        myLib.addBook("The Idiot","Dostoevsky");
        myLib.addBook("The Shining","Stephen King");
        System.out.println(myLib.toString());
       myLib.borrowBook(112);
//        System.out.println(myLib.isBorrowed(112));
       myLib.borrowAllBooks("Aldous Huxley");
        System.out.println("#########\n"+myLib.toString());
        System.out.println("*********\n"+myLib.getBorrowedBooks());


        System.out.println(myLib.authorWithMostBooks());
        System.out.println(myLib.totalAvailableBooks());
        System.out.println(myLib.totalBooksInLibrary());
        System.out.println(myLib.totalLoanBooks());
        System.out.println(myLib.returnBook(112));
        myLib.returnBook(115);
        myLib.sortedByUniqueID();
        Library.Book another_book = new Library.Book("Snuff","Viktor Pelevin");
        myLib.addBook(another_book);

        myLib.sortedByUniqueID();
        System.out.println("//////////////////");

        myLib.returnBook(117);
        myLib.sortedByUniqueID();

        myLib.removeBooks(115);
        myLib.sortedByUniqueID();
    }
}