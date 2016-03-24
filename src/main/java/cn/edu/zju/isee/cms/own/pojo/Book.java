package cn.edu.zju.isee.cms.own.pojo;

/**
 * Book
 *
 * @author 一饭
 * @date 15/7/2 上午10:35
 */
public class Book {
    private String bookName;
    private double price;

    public Book(String bookName, double price) {
        this.bookName = bookName;
        this.price = price;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
