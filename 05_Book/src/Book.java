public class Book {
    private final String name;
    private final String author;
    private final int countOfPages;
    private final int ISBN;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getCountOfPages() {
        return countOfPages;
    }

    public int getISBN() {
        return ISBN;
    }

    public Book(String name, String author, int countOfPages, int ISBN) {
        this.name = name;
        this.author = author;
        this.countOfPages = countOfPages;
        this.ISBN = ISBN;
    }


}
