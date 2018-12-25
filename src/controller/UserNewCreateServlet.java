package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

/**
 * Servlet implementation class UserNewCreateServlet
 */
@WebServlet("/UserNewCreateServlet")
public class UserNewCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    public UserNewCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
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



		/*自分で足したコード*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shinki.jsp");
		dispatcher.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//自分で足したコード//

        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");





		/** 新規登録が失敗した場合 **/




		//入力項目に一つでも未入力のものがある場合//
		if (loginId.isEmpty() || password.isEmpty() || password2.isEmpty() ||name.isEmpty() || birthDate.isEmpty()) {
			// リクエストスコープにエラーメッセージをセット//
			request.setAttribute("errMsg", "入力された内容は正しくありません。未入力の項目があります。");
			request.setAttribute("loginId",loginId);
			request.setAttribute("name",name);
			request.setAttribute("birthDate",birthDate);

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shinki.jsp");
			dispatcher.forward(request, response);

			return;
		}


		//パスワードとパスワード確認の入力内容が異なる場合//
		if(!password.equals(password2)) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません。確認パスワードが異なります。");
			request.setAttribute("loginId",loginId);
			request.setAttribute("name",name);
			request.setAttribute("birthDate",birthDate);

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shinki.jsp");
			dispatcher.forward(request, response);

			return;
		}


		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		try {
			userDao.NewInfo(loginId, password,password2,name,birthDate);
		} catch (SQLException e) {

			//すでに登録されているログインIDが入力された場合//

			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません。入力したログインIDはすでに登録済みです。");
			request.setAttribute("name",name);
			request.setAttribute("birthDate",birthDate);

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shinki.jsp");
			dispatcher.forward(request, response);

			return;

		}


		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");


		//ここまで//

	}

}
