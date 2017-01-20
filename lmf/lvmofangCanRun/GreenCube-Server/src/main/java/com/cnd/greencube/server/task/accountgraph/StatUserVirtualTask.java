package com.cnd.greencube.server.task.accountgraph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cnd.greencube.server.api.JdbcAPI;
import com.cnd.greencube.server.dao.jdbc.JdbcDAO;
import com.cnd.greencube.server.entity.StatVirtualAccountDetail;
import com.cnd.greencube.server.util.StringUtils;
import com.cnd.greencube.server.util.uuid.Key;

/**
 * 用户虚拟账户统计任务类
 * 
 * @author huxg
 * 
 */
public class StatUserVirtualTask {
	/**
	 * 执行任务统计
	 */
	@SuppressWarnings("unchecked")
	protected void execute() {
		// 按人、月份统计总收入，
		String sqlIncome = "select C_USER_ID, SUM(N_AMOUNT) as INCOME,DATE_FORMAT(D_TRAN_TIME, '%Y%m') as MONTHY  from T_VIRTUAL_ACCOUNT_DETAIL where N_PAYMENT_TYPE = 1 group by c_user_id, DATE_FORMAT(D_TRAN_TIME, '%Y%m')";

		// 按人、月份统计总支出，
		String sqlPaid = "select C_USER_ID, SUM(N_AMOUNT) as OUTCOME ,DATE_FORMAT(D_TRAN_TIME, '%Y%m') as MONTHY  from T_VIRTUAL_ACCOUNT_DETAIL where N_PAYMENT_TYPE = 2 group by c_user_id, DATE_FORMAT(D_TRAN_TIME, '%Y%m')";

		JdbcDAO jdbc = JdbcAPI.getJdbc();

		// 清空数据
		jdbc.getJdbcTemplate().execute("delete from T_STAT_VIRTUAL_ACCOUNT_DETAIL");

		// 重新生成数据
		List<Map<String, String>> rowsIncome = jdbc.queryExt(sqlIncome);
		StatVirtualAccountDetail s;
		Map<String, StatVirtualAccountDetail> mp = new HashMap<String, StatVirtualAccountDetail>();
		for (Map<String, String> row : rowsIncome) {
			// 将统计数据保存到数据库中
			s = new StatVirtualAccountDetail();
			s.setId(Key.key());
			s.setIncome(StringUtils.isEmpty(row.get("INCOME")) ? 0 : Integer.parseInt(row.get("INCOME")));
			s.setMonthy(Integer.parseInt(row.get("MONTHY")));
			s.setUserId(row.get("C_USER_ID"));
			mp.put(s.getUserId(), s);
		}

		List<Map<String, String>> rowsOutcome = jdbc.queryExt(sqlPaid);
		for (Map<String, String> row : rowsOutcome) {
			s = mp.get(row.get("C_USER_ID"));
			if (s == null) {
				s = new StatVirtualAccountDetail();
				s.setId(Key.key());
				s.setExpend(StringUtils.isEmpty(row.get("OUTCOME")) ? 0 : Integer.parseInt(row.get("OUTCOME")));
				s.setMonthy(Integer.parseInt(row.get("MONTHY")));
				s.setUserId(row.get("C_USER_ID"));
				int revenue = s.getIncome() - s.getExpend();
				s.setRevenue(revenue > 0 ? revenue : 0);
				mp.put(s.getUserId(), s);
			} else {
				s.setExpend(StringUtils.isEmpty(row.get("OUTCOME")) ? 0 : Integer.parseInt(row.get("OUTCOME")));

				int revenue = s.getIncome() - s.getExpend();
				s.setRevenue(revenue > 0 ? revenue : 0);
			}
		}

		Iterator<String> iter = mp.keySet().iterator();

		Connection conn = null;
		try {
			conn = jdbc.getJdbcTemplate().getDataSource().getConnection();
			boolean isAutoCommit = conn.getAutoCommit();

			conn.setAutoCommit(false);
			PreparedStatement p = conn.prepareStatement("insert into T_VIRTUAL_ACCOUNT_DETAIL values (?, ? ,?, ? ,? ,?) ");

			int i = 0;
			while (iter.hasNext()) {
				s = mp.get(iter.next());
				p.setString(1, s.getId());
				p.setString(2, s.getUserId());
				p.setInt(3, s.getMonthy());
				p.setInt(4, s.getIncome());
				p.setInt(5, s.getExpend());
				p.setInt(6, s.getRevenue());
				p.addBatch();

				if (i % 1000 == 0) {
					p.executeBatch();
				}
				i++;
			}

			p.executeBatch();
			conn.commit();

			conn.setAutoCommit(isAutoCommit);

			System.out.println("刷新执行完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
