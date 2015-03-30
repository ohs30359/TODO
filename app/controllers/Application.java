package controllers;

import java.util.List;

import models.Event;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.timeLine;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render(eventForm(),eventList()));
    }

    /**
     * イベントテーブルの条件検索を実行
     * @return
     */
	public static Result select(){

		List<Event> events = Event.find.where().like("date",  getParameter("date")).findList();

		bubbleSearch(events);



		if(getParameter("time")!=null){
			return ok(timeLine.render(events,getParameter("date")));
		}else{
			return ok(index.render(eventForm(),events));
		}
	}

	/**
	 * 新規イベント追加処理
	 *  バグによるエラーのため、Formから直接参照中
	 * @return
	 */
	public static Result insert(){

		Event event = new Event();
		event.date = getParameter("date");
		event.time = getParameter("time");
		event.text = getParameter("text");
		event.title = getParameter("title");

		event.save();

		return redirect("/");
	}

	/**
	 * イベントの消化更新
	 */
	public static Result update(){

		Event event = Event.find.byId(Long.parseLong(getParameter("primary")));

		event.update();

		return ok(index.render(eventForm(), eventList()));
	}

	/**
	 * イベントの削除
	 * @return
	 */
	public static Result delete(){

		Event event = Event.find.byId(Long.parseLong(getParameter("primary")));

		event.delete();

		return redirect("/");
	}



	/**内部メソッド
	 * イベントテーブルの全リストを取得
	 * @return List<Event>
	 */
	private static List<Event> eventList(){
		List<Event> events = Event.find.where().orderBy("date").findList();
		return bubbleSearch(events);
	}

	/**内部メソッド
	 * イベントテーブルのフォームを生成
	 * @return
	 */
	private static Form<Event> eventForm(){
		Form<Event> f = new Form<Event>(Event.class);
		return f;
	}

	/**内部メソッド
	 * フォームのname属性から値を取得
	 * @param str
	 * @return
	 */
	private static String getParameter(String str){
		String value = Form.form().bindFromRequest().get(str);
		return value;
	}

	/**
	 * イベントテーブルを時間でソートする
	 * @param bubble
	 * @return
	 */
	private static List<Event> bubbleSearch(List<Event> bubble) {

        for (int i = 0; i < bubble.size(); i++) {

            for (int j = bubble.size() - 1; j - 1 >= i; j--) {

                int s1 = Integer.parseInt(bubble.get(j).time.replaceAll(":",""));
                int s2 = Integer.parseInt(bubble.get(j-1).time.replaceAll(":",""));

                int m1 = Integer.parseInt(bubble.get(j).date.replaceAll("/",""));
                int m2 = Integer.parseInt(bubble.get(j-1).date.replaceAll("/",""));

                Event s3 = bubble.get(j-1);

                if (s2 > s1 && m2>=m1) {
                    bubble.set(j - 1, bubble.get(j));
                    bubble.set(j, s3);
                }
            }
        }
        return bubble;
	}

}
