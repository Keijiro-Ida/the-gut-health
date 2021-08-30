package listener;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import DAO.RemindDAO;
import model.CancelMailLogic;
import model.Remind;
import model.SendMailLogic;

/**
 * アプリ起動時、終了時のスレッドの閉鎖と起動
 *
 */
@WebListener
public class Listener implements ServletContextListener {

	/**
	 * Default constructor. 
	 */
	public Listener() {
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("アプリ終了listener起動");
		RemindDAO remindDAO = new RemindDAO();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Remind> list = remindDAO.findAll(now);
		CancelMailLogic cancelBO = new CancelMailLogic();
		try {
			for (Remind remind : list) {
				System.out.println("cancel thread" + remind.getReimindId());
				cancelBO.execute(remind.getActId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("アプリ起動listener起動");
		RemindDAO remindDAO = new RemindDAO();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Remind> list = remindDAO.findAll(now);
		try {
			for (Remind remind : list) {
				SendMailLogic threadBO = new SendMailLogic(remind);
				threadBO.execute();
				System.out.println("start thread" + remind.getReimindId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
