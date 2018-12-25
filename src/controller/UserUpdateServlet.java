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

import common.Common;
import dao.UserDao;
import model.User;

/**
 * Servlet implementation class UserUpdateServelet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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
		request.setCharacterEncoding("UTF-8");


		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println(id);

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();

		User user = userDao.findByUpInfo(id);

		/** テーブルに該当のデータが見つかった場合 **/

		request.setAttribute("kouuser", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kousin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		//自分で足したコード//

        // リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの入力項目を取得
        String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String birthDate = request.getParameter("birthDate");



		//パスワードとパスワード確認の入力内容が異なる場合//
		if(!password.equals(password2)) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "入力された内容は正しくありません。確認パスワードが異なります。");
			User user = new User();
			user.setLoginId(loginId);
			user.setName(name);
			user.setBirthDate(Common.formatDate(birthDate));


			request.setAttribute("kouuser", user);
			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kousin.jsp");
			dispatcher.forward(request, response);

					return;
			}



		//入力項目に一つでも未入力のものがある場合//
		if (name.isEmpty() || birthDate.isEmpty()) {
			// リクエストスコープにエラーメッセージをセット//
			request.setAttribute("errMsg", "入力された内容は正しくありません。未入力の項目があります。");


			User user = new User();
			user.setLoginId(loginId);
			user.setName(name);
			user.setBirthDate(Common.formatDate(birthDate));


			request.setAttribute("kouuser", user);


			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/kousin.jsp");
			dispatcher.forward(request, response);

			return;
		}


		UserDao userDao = new UserDao();
		try {


			if(password.isEmpty() && password2.isEmpty()) {
				userDao.UpDateInfo2(loginId,name,birthDate);
			}else {
				userDao.UpDateInfo(loginId,password,password2,name,birthDate);
			}

		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");
	}

		//ここまで//



}
