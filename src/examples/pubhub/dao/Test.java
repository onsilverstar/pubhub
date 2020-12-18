package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;

public class Test {
	public static void main(String[] args){
	    TagDAO dao = new TagDAOImpl();
	    BookDAO daobook=new BookDAOImpl();
	    List<Book> list = daobook.getAllBooks();

	    for (int i = 0; i < list.size(); i++){
	      Book t = list.get(i);
	      //dao.addTag(t, "comedy");
	      dao.removeTag(t, "movie");
	      //System.out.println(t.getIsbn13());
	     System.out.println(dao.getTags(t));
	    }
	  }
}
