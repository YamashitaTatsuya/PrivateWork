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
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListServlet")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる
		// セッションスコープからインスタンスを取得
		//HttpSessionインスタンスの取得//


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
		// TODO  未実装：検索処理全般
		//検索ボタンを押した時の処理を記入
        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
        String searchloginId = request.getParameter("searchLoginId");
		String searchName = request.getParameter("searchName");
		String searchBirthDate1 = request.getParameter("searchBirthDate1");
		String searchBirthDate2 = request.getParameter("searchBirthDate2");



		// ユーザ一覧情報を取得
				UserDao userDao = new UserDao();

				List<User> userList = userDao.findSearch(searchloginId,searchName,searchBirthDate1,searchBirthDate2);


				// リクエストスコープにユーザ一覧情報をセット
				request.setAttribute("userList", userList);


				// ユーザ一覧のjspにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alluser.jsp");
				dispatcher.forward(request, response);

	}

}
