// Assignment: 1
// Student: Michael Kaminer



package HW1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Represents a library containing a collection of books.
 */
public class Library extends Object {

    /**
     * Represents a book with a name, author, unique ID, and quantity.
     */
    public static class Book {            //nested class Book
        private String name;
        private String author;
        /**
         * Gets the author of the book.
         *
         * @return The author of the book.
         */
        public String getAuthor() {
            return author;
        }


        private final int book_id;
        /**
         * Gets the unique ID of the book.
         *
         * @return The unique ID of the book.
         */
        public  int getBook_id() {
            return book_id;
        }

        private int quantity;
        /**
         * Constructs a new Book with the given name and author.
         *
         * @param name   The name of the book.
         * @param author The author of the book.
         */
        public Book(String name, String author) {
            this.book_id = ++idx;
            this.name = name;
            this.author = author;
            this.quantity = 1;
        }
        /**
         * Returns a string representation of the book.
         *
         * @return A string representation of the book.
         */
        @Override
        public String toString() {
            return "Book{" +
                    "name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", book_id=" + book_id +
                    ", quantity=" + quantity +
                    '}'+ "\n";
        }

    }


    public static int idx;
   private DataStructure library;
   private List<Book> borrowedBooks ;

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }


    /**
     * Constructs a new Library with an empty collection of books.
     */
    public Library() {
        library = new DataStructure<>();
        borrowedBooks = new ArrayList<>();
    }
    /**
     * Adds a new book to the library with the given name and author.
     *
     * @param name   The name of the book to add.
     * @param author The author of the book to add.
     */
    public void addBook(String name, String author) {
        Book newBook = new Book(name, author);
        library.addToEnd(newBook);
    }
    /**
     * Adds the given book to the library.
     *
     * @param book The book to add to the library.
     */
    public void addBook(Book book) {
        library.addToEnd(book);
    }
    /**
     * Returns a string representation of the library.
     *
     * @return A string representation of the library.
     */
    @Override
    public String toString() {
        return "Library{" +
                "library=" +  library +
                '}';
    }
    /**
     * Removes books from the library with the given unique ID.
     *
     * @param uniqueID The unique ID of the books to remove.
     */
    public void removeBooks(int uniqueID) {
        int measure_of_change = library.size();
        for (int i =0;i<library.size();i++){
            Book book = (Book) library.getValue(i);
            if (book.getBook_id() == uniqueID) library.delete(book);
    }
        if (measure_of_change==library.size()) System.out.println("Didn't find this ID to delete. ");
        else System.out.println("Removal was successful. ID: " +uniqueID+" has been removed. ");
    }
    /**
     * Borrows a book from the library with the given unique ID.
     *
     * @param uniqueID The unique ID of the book to borrow.
     * @return The borrowed book, or null if not found.
     */
    public Library.Book borrowBook(int uniqueID) {
        for (int i =0;i<library.size();i++){
            Book book = (Book) library.getValue(i);
            if (book.getBook_id() == uniqueID){
                borrowedBooks.add(book);
                System.out.println("The book was borrowed: " +book.name+" ID: "+book.getBook_id());
                library.delete(book);
                return book;
            }}
            return null;
        }
    /**
     * Borrows all books from the library by a specific author.
     *
     * @param author_name The name of the author whose books to borrow.
     * @return A list of borrowed books by the author.
     */
    public List<Book> borrowAllBooks(String author_name){
        int measure_of_change = borrowedBooks.size();
        for (int i=library.size()-1;i>=0 ;i--){
            Book book = (Book)library.getValue(i);
            if (book.getAuthor().equals(author_name)) {
                borrowedBooks.add(book);
                library.delete(book);
            }
        }
        if (measure_of_change==borrowedBooks.size()){
            System.out.println("Didn't find any book of this author. ");
        }else {
            System.out.println("All books by this author have been successfully borrowed. ");
        }
        return borrowedBooks;
    }
    /**
     * Sorts the library by unique ID of the books.
     */
    public void sortedByUniqueID() {
        /**First we find maxBookID**/
        int maxId = 0;
        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getValue(i);
            if (book.getBook_id() > maxId) {
                maxId = book.getBook_id();
            }
        }

/**Now let's find the minID**/
        int minId = maxId;
        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getValue(i);
            if (book.getBook_id() < minId) {
                minId = book.getBook_id();
            }
        }



        int diapason = maxId - minId + 1;
        

        /**Creating counting array of every book ID from MinID to MaxID**/
        int[] count = new int[diapason];
        for (int i = 0; i < library.size(); i++) {
            Book book = (Book) library.getValue(i);
            count[book.getBook_id() - minId]++;
        }

        /**Creating additional library that will gather sorted books**/
  DataStructure <Book> newLibra = new DataStructure<>();
        int index = diapason;
        /**Count sorting O(n)    ^_^**/
        for(int i=count.length-1;i>=0;i--){
            for(int j=0;j<count[i];j++){

                int currentID = i+minId;
                Book book = findBookByID(currentID);
                if(book!=null) {
                 newLibra.addToEnd(book);
                }
            }
        }
        library=newLibra;
        System.out.println("Updated library: \n"+library );
    }

    /**
     * Finds a book in the library by its unique ID.
     *
     * @param uniqueID The unique ID of the book to find.
     * @return The found book, or null if not found.
     */
        private Book findBookByID(int uniqueID){
            for(int i=0;i<library.size();i++){
                Book book = (Book)library.getValue(i);
                if (book.getBook_id()==uniqueID) return book;
            }
            return null;
        }

    /**
     * Calculates the total number of books in the library.
     *
     * @return The total number of books in the library.
     */
    public int totalBooksInLibrary(){
        return totalAvailableBooks()+totalLoanBooks();
    }
    /**
     * Calculates the total number of available books in the library.
     *
     * @return The total number of available books in the library.
     */
    public int totalAvailableBooks(){
        return library.size();
    }
    /**
     * Calculates the total number of borrowed books from the library.
     *
     * @return The total number of borrowed books from the library.
     */
    public int totalLoanBooks(){
        return borrowedBooks.size();
    }
    /**
     * Finds the author who has the most books in the library.
     *
     * @return The author with the most books.
     */
    public String authorWithMostBooks(){
        int max = 0;
        String theAuthor="";
        /**The simplest case, when there is only one author who wrote most books.**/
        for (int i =0;i<library.size();i++){
            Book book = (Book)library.getValue(i);
            String author = book.getAuthor();
            int current_author = wroteBooks(author);
            if (current_author>max) {
                max =current_author;
                theAuthor = author;
            }
        }

        /**Here I start to check another case, when there are more than one author with most books.**/
        int howManyMaximums = 0;
        for (int i =0;i<library.size();i++){
            Book book = (Book)library.getValue(i);
            String author = book.getAuthor();
            int current_author = wroteBooks(author);
            if (current_author==max) {
                howManyMaximums++;
            }
        }
        /**Now we know how mane authors like this are there.

        //Creating an array to collect these authors' names.**/
        String[] arrOfMaxes = new String[howManyMaximums];
        int index = 0;
        for (int i =0;i<library.size();i++){
            Book book = (Book)library.getValue(i);
            String author = book.getAuthor();
            int current_author = wroteBooks(author);
            if (current_author==max && !containsAuthor(arrOfMaxes,author)) {
                arrOfMaxes[index++]= author;
            }
        }

        /**Before this point, array got copies of the same author and I deleted them
         But this led to creation of some null cells in the array, so now I remove all the nulls**/
        int point = howManyMaximums;
        for(String str:arrOfMaxes) {
            if (str == null) point--;
        }

            /**Now we show one or more authors with most books**/
            if (point <= 1) {
                System.out.println("The author: " + theAuthor + " wrote " + max + " books!");
                return theAuthor;
            } else  {
                System.out.println("There are any of authors who wrote most " +point+ " books: ");
                return Arrays.toString(Arrays.copyOf(arrOfMaxes, point));
            }
    }
    /**
     * Checks if the array of authors contains the given author.
     *
     * @param array  The array of authors.
     * @param author The author to check for.
     * @return true if the author is found in the array, false otherwise.
     */
    private boolean containsAuthor(String[] array, String author){
        //This method helps to prevent appearance of copies in arrOfMaxes
        for (String name : array){
            if(name!=null && name.equals(author)) return true;
        }
        return false;
    }
    /**
     * Counts the number of books written by the given author in the library.
     *
     * @param author The author to count books for.
     * @return The number of books written by the author.
     */
    private int wroteBooks(String author){
        int author_counter=0;
        for (int i =0;i<library.size();i++){
            Book book = (Book)library.getValue(i);
            if (book.getAuthor().equals(author))author_counter++;
        }
        return author_counter;
    }
    /**
     * Returns a borrowed book to the library by its unique ID.
     *
     * @param uniqueID The unique ID of the book to return.
     * @return true if the book was returned successfully, false otherwise.
     */
    public boolean returnBook(int uniqueID){
        boolean wasBorrowedAndReturned=isBorrowed(uniqueID);
        for (int i=0;i<borrowedBooks.size();i++){
            Book book = borrowedBooks.get(i);
            if (book.getBook_id()==uniqueID){
                library.addToEnd(book);
                borrowedBooks.remove(book);
            }
        }
        if (!wasBorrowedAndReturned)
            System.out.println("This ID not found in borrowed list. ");
        if (!isBorrowed(uniqueID) && wasBorrowedAndReturned)
            System.out.println("Returning book with ID: "+uniqueID+" to the library. ");
        return (!isBorrowed(uniqueID) && wasBorrowedAndReturned);
    }

    /**
     * Checks if a book with the given unique ID is borrowed.
     *
     * @param uniqueID The unique ID of the book to check.
     * @return true if the book is borrowed, false otherwise.
     */
    public boolean isBorrowed(int uniqueID){
        for (int i =0;i<borrowedBooks.size();i++){
            Book book = borrowedBooks.get(i);
                if (borrowedBooks.contains(book) && book.getBook_id()==uniqueID){
                    System.out.println("Book: "+book+" ID: "+uniqueID+ " was borrowed");
                    return true;
                }
            }
            return false;
        }
    }