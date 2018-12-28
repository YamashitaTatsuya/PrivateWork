package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.User;

/**
 * Servlet implementation class TopicListServlet
 */
@WebServlet("/TopicListServlet")
public class TopicListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();

		if(session.getAttribute("userInfo") == null) {
		// ユーザ一覧のサーブレットにリダイレクト
				response.sendRedirect("LoginServlet");
				return;
		}


		// ユーザ一覧情報を取得
		UserDao userDao = new UserDao();
		List<User> userList = userDao.findAll();


		// リクエストスコープにユーザ一覧情報をセット
		request.setAttribute("userList", userList);

		// ユーザ一覧のjspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alluser.jsp");
		dispatcher.forward(request, response);



	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
