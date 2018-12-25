package controller;

import java.io.IOException;

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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {

		// TODO 未実装：ログインセッションがない場合、ログイン画面にリダイレクトさせる
		// セッションスコープからインスタンスを取得

		//HttpSessionインスタンスの取得//
		HttpSession session = request.getSession();

		if(session.getAttribute("userInfo") == null) {
		// ユーザ一覧のサーブレットにリダイレクト
				response.sendRedirect("LoginServlet");
				return;

		}


		// URLからGETパラメータとしてIDを受け取る
		String id = request.getParameter("id");


		// 確認用：idをコンソールに出力
		System.out.println(id);

		// TODO  未実装：idを引数にして、idに紐づくユーザ情報を出力する

		// リクエストパラメータの入力項目を引数に渡して、Daoのメソッドを実行
		UserDao userDao = new UserDao();
		User user = userDao.findByInfo(id);



		/** テーブルに該当のデータが見つかった場合 **/

		request.setAttribute("syouser", user);


		// TODO  未実装：ユーザ情報をリクエストスコープにセットしてjspにフォワード

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/syousai.jsp");
		dispatcher.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
