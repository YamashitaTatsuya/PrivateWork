package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.Board;
import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */

public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */

    public User findByLoginInfo(String loginId, String password) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? AND password = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, password);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     */
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更した
            String sql = "SELECT * FROM user where login_id != 'admin' ";



             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }

//自分で足したコード//

    /**
     * 全てのトピックを取得する
     * @return
     */
    public List<Board> findtopic() {
        Connection conn = null;
        List<Board> topicList = new ArrayList<Board>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更した
            String sql = "SELECT * FROM user where login_id != 'admin' ";



             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                topicList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return topicList;
    }





    /**
     * 検索したユーザ情報を取得する
     * @return
     */
    public List<User> findSearch(String searchloginId, String searchName, String searchBirthDate1, String searchBirthDate2) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備//

            // TODO: 未実装：検索結果を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE login_id != 'admin' ";



            if(!searchloginId.isEmpty()) {
            	sql += "AND login_id='" + searchloginId + "' ";
            }

            if(!searchName.isEmpty()) {
            	sql += "AND name LIKE '%" + searchName + "%' ";
            }

            if(!(searchBirthDate1.isEmpty())) {
            	sql += "AND birth_date >= '" + searchBirthDate1 +"' ";
            }

            if(!(searchBirthDate2.isEmpty())) {
            	sql += "AND birth_date < '" + searchBirthDate2 + "'";
            }

            System.out.println(sql);

             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }




//新規登録画面のコード//

    /**
     * ログインIDに紐づくユーザ情報を返す
     * @param loginId
     * @return
     * @throws SQLException
     */
    public void NewInfo(String loginId,String password,String password2,String name,String birthDate) throws SQLException {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // INSERT文を準備
            String sql = "INSERT INTO user(login_id,password,name,birth_date,create_date,update_date)VALUES(?,?,?,?,now(),now())";


            //暗号化のコード
            //ハッシュを生成したい元の文字列
            String source = password;
            //ハッシュ生成前にバイト配列に置き換える際のCharset
            Charset charset = StandardCharsets.UTF_8;
            //ハッシュアルゴリズム
            String algorithm = "MD5";

            //ハッシュ生成処理
            byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
            String result = DatatypeConverter.printHexBinary(bytes);
            //標準出力
            System.out.println(result);




             // INSERTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, result);
            pStmt.setString(3, name);
            pStmt.setString(4, birthDate);

            pStmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;

        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }

//詳細画面のコード//
/**
 * ログインIDに紐づくユーザ情報を返す
 * @param loginId
 * @return
 */
public User findByInfo(String id) {
    Connection conn = null;
    try {
        // データベースへ接続
        conn = DBManager.getConnection();

        // SELECT文を準備
        String sql = "SELECT * FROM user WHERE id = ?";

         // SELECTを実行し、結果表を取得
        PreparedStatement pStmt = conn.prepareStatement(sql);
        pStmt.setString(1, id);

        ResultSet rs = pStmt.executeQuery();

         // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
        if (!rs.next()) {
            return null;
        }

        String syologinId = rs.getString("login_id");
        String syonameData = rs.getString("name");
        Date syobirthDate = rs.getDate("birth_date");
        String syocreateDate = rs.getString("create_date");
        String syoupdateDate = rs.getString("update_date");
        return new User(syologinId, syonameData,syobirthDate,syocreateDate,syoupdateDate);


    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    } finally {
        // データベース切断
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}




//////////更新画面のコード////////////
/**
* ログインIDに紐づくユーザ情報を返す
* @param loginId
* @return
*/

//更新画面でのデータベースから情報を取得する場合//
public User findByUpInfo(String id) {
  Connection conn = null;
  try {
      // データベースへ接続
      conn = DBManager.getConnection();

      // SELECT文を準備
      String sql = "SELECT * FROM user WHERE id = ?";

       // SELECTを実行し、結果表を取得
      PreparedStatement pStmt = conn.prepareStatement(sql);
      pStmt.setString(1, id);

      ResultSet rs = pStmt.executeQuery();

       // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
      if (!rs.next()) {
          return null;
      }

      String kouloginId = rs.getString("login_id");
      String kounameData = rs.getString("name");
      Date koubirthDate = rs.getDate("birth_date");

      return new User(kouloginId, kounameData, koubirthDate);

  } catch (SQLException e) {
      e.printStackTrace();
      return null;
  } finally {
      // データベース切断
      if (conn != null) {
          try {
              conn.close();
          } catch (SQLException e) {
              e.printStackTrace();
              return null;
          }
      }
  }
}

//更新画面でのデータベースへ情報を登録する場合//
/**
 * ログインIDに紐づくユーザ情報を返す
 * @param loginId
 * @return
 * @throws SQLException
 */
public void UpDateInfo(String loginId,String password,String password2,String name,String birthDate) throws SQLException {
    Connection conn = null;
    try {
        // データベースへ接続
        conn = DBManager.getConnection();

        // INSERT文を準備
        String sql = "UPDATE user SET password=?, name=?, birth_date=?, update_date=now() where login_id = ?";

         // INSERTを実行し、結果表を取得
        PreparedStatement pStmt = conn.prepareStatement(sql);


        //ハッシュを生成したい元の文字列
        String source = password;
        //ハッシュ生成前にバイト配列に置き換える際のCharset
        Charset charset = StandardCharsets.UTF_8;
        //ハッシュアルゴリズム
        String algorithm = "MD5";

        //ハッシュ生成処理
        byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
        String result = DatatypeConverter.printHexBinary(bytes);

        //標準出力
        System.out.println(result);

        pStmt.setString(1, result);
        pStmt.setString(2, name);
        pStmt.setString(3, birthDate);
        pStmt.setString(4, loginId);

        pStmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw e;

    } catch (NoSuchAlgorithmException e) {

		e.printStackTrace();
	} finally {
        // データベース切断
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}

//更新画面でのデータベースへ情報を登録する場合(パスワードなし）//

/**
 * ログインIDに紐づくユーザ情報を返す
 * @param loginId
 * @return
 * @throws SQLException
 */
public void UpDateInfo2(String loginId,String name,String birthDate) throws SQLException {
    Connection conn = null;
    try {
        // データベースへ接続
        conn = DBManager.getConnection();

        // INSERT文を準備
        String sql = "UPDATE user SET name=?, birth_date=?, update_date=now() where login_id = ?";

         // INSERTを実行し、結果表を取得
        PreparedStatement pStmt = conn.prepareStatement(sql);


        pStmt.setString(1, name);
        pStmt.setString(2, birthDate);
        pStmt.setString(3, loginId);


        pStmt.executeUpdate();


    } catch (SQLException e) {
        e.printStackTrace();
        throw e;

    } finally {
        // データベース切断
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}

//////////削除画面のコード////////////

//削除画面で表示するコード//
/**
* ログインIDに紐づくユーザ情報を返す
* @param loginId
* @return
*/
public User findByDelInfo(String id) {
Connection conn = null;
try {
    // データベースへ接続
    conn = DBManager.getConnection();

    // SELECT文を準備
    String sql = "SELECT * FROM user WHERE id = ?";

     // SELECTを実行し、結果表を取得
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setString(1, id);

    ResultSet rs = pStmt.executeQuery();

     // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
    if (!rs.next()) {
        return null;
    }

    String delloginId = rs.getString("login_id");


    return new User(delloginId);

} catch (SQLException e) {
    e.printStackTrace();
    return null;
} finally {
    // データベース切断
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
}



//削除画面でデータベースから削除するコード//

public void DelInfo(String loginId) throws SQLException {
Connection conn = null;
try {
    // データベースへ接続
    conn = DBManager.getConnection();

    // SELECT文を準備
    String sql = "DELETE FROM user WHERE login_id = ?";

     // SELECTを実行し、結果表を取得
    PreparedStatement pStmt = conn.prepareStatement(sql);
    pStmt.setString(1, loginId);

    pStmt.executeUpdate();


} catch (SQLException e) {
    e.printStackTrace();

    throw e;

} finally {
    // データベース切断
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
}


//ここまで//

}

