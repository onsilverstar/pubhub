package examples.pubhub.model;

public class Book_Tags {
	private String isbn13;
	private String tag_name;
	public String getIsbn13() {
		return isbn13;
	}
	public Book_Tags()
	{
		this.isbn13=null;
		this.tag_name=null;
	}
	public Book_Tags(String isbn, String name)
	{
		this.isbn13=isbn;
		this.tag_name=name;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	

}
