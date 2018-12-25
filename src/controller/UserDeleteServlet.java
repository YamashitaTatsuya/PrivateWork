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
import model.User;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
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
		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");

		// 確認用：idをコンソールに出力
		System.out.println(id);

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
				UserDao userDao = new UserDao();

				User user = userDao.findByDelInfo(id);

		/** テーブルに該当のデータが見つかった場合 **/

		request.setAttribute("deluser", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sakujyo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//自分で足したコード//

		// URLからGETパラメータとしてIDを受け取る
		String loginId = request.getParameter("delId");

		// 確認用：loginidをコンソールに出力
		System.out.println(loginId);

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行


		UserDao userDao = new UserDao();

		try {
			userDao.DelInfo(loginId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		// ユーザーリストのサーブレットにリダイレクト
		response.sendRedirect("UserListServlet");

		//ここまで//
	}

}
