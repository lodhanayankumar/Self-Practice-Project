package get_set;

public class bookgs {

    private long isbnNo;
    private String title;
    private String author;
    private String publisher;
    private double price;

    public bookgs() {
    }

    public long getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(long isbnNo) {
		this.isbnNo = isbnNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public bookgs(long isbnNo, String title, String author, String publisher, double price) {
        this.isbnNo = isbnNo;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

}