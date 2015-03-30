package helper;

import java.util.Calendar;

/**
 * 日付関連の操作を行うクラス
 * @author ohs30359
 *
 */

public class DateManager {

	Calendar now = Calendar.getInstance();

	/*
	 * 現在日時を宣言
	 */

	String year = String.valueOf(now.get(Calendar.YEAR));
	String month = String.valueOf(now.get(Calendar.MONTH) + 1);
	String day = String.valueOf(now.get(Calendar.DATE));
	String hour = String.valueOf(now.get(Calendar.HOUR_OF_DAY));
	String minutes = String.valueOf(now.get(Calendar.MINUTE));


	/**
	 * 本日の日付を取得する
	 * @return : yyyy/mm/dd
	 */
	public String getToday(){

		if(2>month.length()) month = "0"+month;
		if(2>day.length()) day = "0"+day;

		return  year+"/"+month+"/"+day;
	}


	/**
	 * 第一引数で指定した日から第二引数で指定した日までの日数を計算。
	 * 第一引数に対し演算。 - の場合は超過した日数
	 * @param :day yyyy/mm/dd
	 * @param : MinusDay :yyyy/mm/dd
	 * @return : int
	 */
	public int getDay(String day,String MinusDay){

		Calendar objNow = Calendar.getInstance();
		Calendar objPast = Calendar.getInstance();

		String dayArray [] = day.split("/");
		String MinusArray[] = MinusDay.split("/");

		objNow.set(Integer.parseInt(dayArray[0]),Integer.parseInt(dayArray[1])+1,Integer.parseInt(dayArray[2]));
		objPast.set(Integer.parseInt(MinusArray[0]), Integer.parseInt(MinusArray[1])+1, Integer.parseInt(MinusArray[2]));

		int timeDiff = (int) (objNow.getTimeInMillis() - objPast.getTimeInMillis());
		int dayDif = timeDiff / 1000 / 60 / 60 / 24;

		return dayDif;

	}

	/**
	 * 日数の足し算を行う
	 * @param date
	 * @param sumDay
	 * @return
	 */

	public String sumDate(String date,int sumDay){

		Calendar cal = Calendar.getInstance();
		String sumYear = null;
		String sumMonth = null;
		String sumDate = null;

		String array[] = date.split("-");
		cal.set(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]));
		cal.add(Calendar.DATE, sumDay);

		sumYear = String.valueOf(cal.get(Calendar.YEAR));
		sumMonth = String.valueOf(cal.get(Calendar.MONTH));
		sumDate = String.valueOf(cal.get(Calendar.DATE));

		if(2>sumMonth.length()) sumMonth = "0"+sumMonth;
		if(2>sumDate.length()) sumDate = "0"+sumDate;

		return  sumYear+"/"+sumMonth+"/"+sumDate;

	}


	/**
	 * 誕生日を引数として与えることで年齢を算出する
	 *
	 * @param age : yyyy-mm-dd
	 * @return : String
	 */
	 public String getAge(String age) {

		 	String today = "";
		 	String userAge = "";

		 	//各変数を配列変換
		 	String [] array = getToday().split("/");
		 	String [] userArray = age.split("/");

		 	/*
		 	 * 文字列に再変換
		 	 */
		 	for(int i=0;i<array.length;i++)	today +=array[i];
		 	for(int i=0;i<userArray.length;i++)	userAge +=userArray[i];

		 	return String.valueOf((Integer.parseInt(today) - Integer.parseInt(userAge)) / 10000);

	    }


}
