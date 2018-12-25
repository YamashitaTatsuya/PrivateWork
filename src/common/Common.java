package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	/**
	 * 文字列を日付に変換する
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date){
        // 変換対象の日付文字列

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Date型変換
        Date formatDate = null;
        try {
			formatDate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        return formatDate;

	}

}
