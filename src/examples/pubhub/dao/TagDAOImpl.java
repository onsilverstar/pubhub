package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
//import examples.pubhub.model.Book_Tags;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {
	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	/*------------------------------------------------------------------------------------------------*/

	// Closing all resources is important, to prevent memory leaks. 
	// Ideally, you really want to close them in the reverse-order you open them
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}


	@Override
	public List<String> getTags(Book book) {
		// TODO Auto-generated method stub
		List<String> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Books INNER JOIN Book_Tags ON Books.isbn_13 = Book_Tags.isbn_13 WHERE Books.isbn_13=?";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, book.getIsbn13());
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result

				// Each variable in our Book object maps to a column in a row from our results.
				tags.add(rs.getString("tag_name"));
				
				// The SQL DATE data type maps to java.sql.Date... which isn't well supported anymore. 
				// We use a LocalDate instead, because this is Java 8.
				
				// The PDF file is tricky; file data is stored in the DB as a BLOB - Binary Large Object. It's
				// literally stored as 1's and 0's, so this one data type can hold any type of file.
				
				// Finally we add it to the list of Book objects returned by this query.
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return tags;
	}


	@Override
	public List<Book> getBooksbyTag(String title) {
		List<Book> books = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();	// Get our database connection from the manager
			String sql = "SELECT * FROM Books INNER JOIN Book_Tags ON Books.isbn_13 = Book_Tags.isbn_13 WHERE Book_Tags.tag_name=?";			// Our SQL query
			stmt = connection.prepareStatement(sql);	// Creates the prepared statement from the query
			stmt.setString(1, title);
			ResultSet rs = stmt.executeQuery();			// Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// We need to populate a Book object with info for each row from our query result
				Book book = new Book();

				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
				
				// The SQL DATE data type maps to java.sql.Date... which isn't well supported anymore. 
				// We use a LocalDate instead, because this is Java 8.
				
				// The PDF file is tricky; file data is stored in the DB as a BLOB - Binary Large Object. It's
				// literally stored as 1's and 0's, so this one data type can hold any type of file.
				
				// Finally we add it to the list of Book objects returned by this query.
				
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed, 
			// or else we could wind up with a memory leak
			closeResources();
		}
		
		// return the list of Book objects populated by the DB.
		return books;
	}


	@Override
	public boolean addTag(Book book, String tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO Book_Tags VALUES (?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, book.getIsbn13());
			stmt.setString(2, tag);
			
			// If we were able to add our book to the DB, we want to return true. 
			// This if statement both executes our query, and looks at the return 
			// value to determine how many rows were changed
			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}


	@Override
	public boolean removeTag(Book book, String tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM Book_Tags WHERE Book_Tags.tag_name=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag);

			if (stmt.executeUpdate() != 0)
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

}
