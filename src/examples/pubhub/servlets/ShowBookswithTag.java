package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ShowBookswithTag
 */
@WebServlet("/ShowTaggedBooks")

public class ShowBookswithTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TagDAO daotag = DAOUtilities.getTagDAO();
		List<Book> searches = daotag.getBooksbyTag(request.getParameter("search"));

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", searches);
		request.getRequestDispatcher("ShowBookswithTag.jsp").forward(request, response);
	}
	//protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Grab the list of Books from the Database
		//TagDAO daotag = DAOUtilities.getTagDAO();
		//List<Book> searches = daotag.getBooksbyTag(request.getParameter("search"));
		//BookDAO dao = DAOUtilities.getBookDAO();
		//List<Book> searches = dao.getAllBooks();

		// Populate the list into a variable that will be stored in the session
		//req.getSession().setAttribute("tags", searches);
		
		//req.getRequestDispatcher("ShowBookswithTag.jsp").forward(req, resp);
		//req.getRequestDispatcher("ShowBookswithTag.jsp").forward(req, resp);
	}

//}
