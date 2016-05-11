package se.bank.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class rewardsPoint {

	Connection con;
	Statement st;

	public rewardsPoint() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			con = DriverManager.getConnection("jdbc:mysql://localhost/se_bank",
					"root", "root");
			st = con.createStatement();

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

	}

	public int countRewardPoints(int amount, String account_number)
			throws SQLException {

		String query2 = "select * from reward_points where account_number="
				+ account_number;

		ResultSet rs1 = st.executeQuery(query2);
		System.out.println("rs1 = " + rs1);
		int old_reward_money = 0;
		int old_reward_point = 0;
		int exist = 0;

		while (rs1.next()) {
			old_reward_money = rs1.getInt("left_reward_money");
			old_reward_point = rs1.getInt("reward_points");
			exist = 1;
		}

		int rewardPoint = (amount + old_reward_money) / 50;
		int new_reward_money = (amount + old_reward_money) % 50;
		String query1;
		
		rewardPoint+= old_reward_point;

		if (exist != 1) {
			query1 = "insert into reward_points VALUES('" + account_number
					+ "'," + rewardPoint + ", " + new_reward_money + ");";
		} else {
			query1 = "update reward_points set reward_points=" + rewardPoint
					+ ", left_reward_money=" + new_reward_money
					+ " where account_number=" + account_number + ";";
		}

		try {
			if (st.executeUpdate(query1) == 1) {
				int reward_points = 0;
				ResultSet rs = st.executeQuery(query2);
				while (rs.next()) {
					reward_points = rs.getInt("reward_points");
				}

				int amounttoadd = 0;

				while (reward_points >= 50) {
					reward_points = reward_points - 50;
					String query3 = "update reward_points set reward_points="
							+ reward_points + " where account_number="
							+ account_number + ";";

					amounttoadd += 10;

					if (st.executeUpdate(query3) != 1) {
						return 0;
					}
					
					System.out.println(amounttoadd);

				}

				String query7 = "select * from transaction where transaction_id = (select max(transaction_id) from transaction) and account_number="
						+ account_number + ";";
				
				System.out.println(query7);

				ResultSet rs3 = st.executeQuery(query7);
				float amount1 = 0;
				String trans_id1 = "null";
				String date = "null";

				while (rs3.next()) {
					amount1 = rs3.getFloat("balance");
					trans_id1 = rs3.getString("transaction_id");
					date = rs3.getString("transaction_date");
				}

				amount1 += amounttoadd;

				String query4 = "insert into transaction values('"
						+ account_number + "','" + trans_id1 + "','" + date
						+ "','Reward','" + account_number + "','"
						+ amounttoadd + "','db','" + amount1
						+ "','Reward Point');";

				System.out.println(query4);
				if (st.executeUpdate(query4) != 1) {
					return 0;
				}
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
