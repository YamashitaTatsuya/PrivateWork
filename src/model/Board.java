package model;

import java.util.Date;

/**
 * Boardテーブルのデータを格納するためのBeans
 * @author takano
 *
 */
public class Board {
	private int id;
	private String loginId;
	private String name;
	private Date birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	public Board() {

	}

	// ログインセッションを保存するためのコンストラクタ
	public Board(String loginId, String name) {
		this.loginId = loginId;
		this.name = name;
	}

	// 全てのデータをセットするコンストラクタ
	public Board(int id, String loginId, String name, Date birthDate, String password, String createDate,
			String updateDate) {
		this.id = id;
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.password = password;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}


	//自分で足したコード



	//新規登録画面で使うコンストラクタ
	public Board(String loginId,String password, String name, Date birthDate) {
		this.loginId = loginId;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
	}


	//詳細画面で使うコンストラクタ
	public Board(String loginId, String name, Date birthDate, String createDate,String updateDate) {
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	//更新画面で使うコンストラクタ
	//検索機能で使うコンストラクタ
	public Board(String loginId,String name,Date birthDate) {
		this.loginId = loginId;
		this.name = name;
		this.birthDate = birthDate;
	}



	//削除画面で使うコンストラクタ
	public Board(String loginId) {
		this.loginId = loginId;
	}


	//ここまで


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}





}
