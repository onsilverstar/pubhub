package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Book_Tags;

public interface TagDAO {
	public List<String> getTags(Book book);
	public List<Book> getBooksbyTag(String title);
	public boolean addTag(Book book, String tag);
	public boolean removeTag(Book book, String tag);
}
