public class Printer {
    private String queue = "";
    private int pagesCount = 0;
    private int totalPagesCount = 0;
    private boolean isPrinting = false;

    /*Подготовка документа к печати*/
    public void append(String documentText) {
        queue += "Document: " + documentText + "; Author  is unknown" + "; Number of pages is unknown" + "\n";
        isPrinting = false;
    }

    /*Подготовка документа к печати*/
    public void append(String documentText, String author) {
        queue += "Document: " + documentText + "; Author - " + author + "; Number of pages is unknown" + "\n";
        isPrinting = false;
    }

    /*Подготовка документа к печати*/
    public void append(String documentText, String author, int pages) {
        queue += "Document: " + documentText + "; Author - " + author + "; Number of pages " + pages + "\n";
        totalPagesCount += pages;
        pagesCount += pages;
        isPrinting = false;
    }

    /*Очистка очереди печати*/
    public void clear() {
        isPrinting = true;
        queue = "";
    }

    /*Подготовка документа к печати*/
    public void print() {
        if (!isPrinting) {
            System.out.println(queue);
            isPrinting = true;
            clear();
            pagesCount = 0;
        } else {
            System.out.println("Документов для печати нет.");
        }
    }

    /*Метод, возвращающий общее количество страниц, которые добавлены в принтер, но ещё не распечатаны*/
    public int getPendingPagesCount() {
        if (!isPrinting) {
            return pagesCount;
        }
        System.out.println("Все документы распечатаны.");
        return 0;
    }

    /*Метод, возвращающий общее количество распечатанных страниц за всё время
    существования объекта класса Printer*/
    public int getPrintingPages() {
        return totalPagesCount;
    }


}
