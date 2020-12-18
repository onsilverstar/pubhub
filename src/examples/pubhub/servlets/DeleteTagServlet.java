package examples.pubhub.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class DeleteTagServlet
 */
@WebServlet("/DeleteTag")
public class DeleteTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String isbn13 = request.getParameter("isbn13");
		
		BookDAO dao = DAOUtilities.getBookDAO();
		Book book = dao.getBookByISBN(isbn13);
		
		TagDAO tagdao = DAOUtilities.getTagDAO();
		tagdao.removeTag(book, request.getParameter("tag"));
		
	}
}
