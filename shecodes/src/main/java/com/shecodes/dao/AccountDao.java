package com.shecodes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.entity.Role;
import com.shecodes.entity.User;
import com.shecodes.utils.DBexecute;

/**
 * @author ntmduyen
 */
@Service
public class AccountDao {

	private static final Logger logger = LogManager.getLogger(MedicineDao.class);

	@Autowired
	public DBexecute dbContext;
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 11:50:37 PM
	 * @return
	 */
	public List<User> getAllUser() {
		List<User> lstUser = new ArrayList<>();
		try {
			String sql = "SELECT * FROM USER";
			Connection con = dbContext.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Long userId = rs.getLong(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				int gender = rs.getInt(6);
				String email = rs.getString(7);
				String address = rs.getString(8);
				int enabled = rs.getInt(9);
				lstUser.add(new User(userId, username, password, firstName, lastName, gender, email, address, enabled));
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstUser;
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 11:54:18 PM
	 * @param userId
	 * @return
	 */
	public List<Role> getListRoleOfUser(Long userId) {
		List<Role> lstRole = new ArrayList<>();
		try {
			String sql = "SELECT r.role_id, r.role_name " + 
					"FROM role r\r\n" + 
					"LEFT JOIN user_role ur ON ur.role_id = r.role_id " + 
					"LEFT JOIN user u ON u.user_id = ur.user_id " + 
					"WHERE u.user_id = ?";
			logger.info(sql);

			Connection con = dbContext.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, userId);

			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Long roleId = rs.getLong(1);
				String roleName = rs.getString(2);
				lstRole.add(new Role(roleId, roleName));
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstRole;
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 12:26:53 AM
	 * @param userName
	 * @return
	 */
	public Long getUserIdFromUserName(String userName) {
		List<Long> lstUserId = new ArrayList<>();
		try {
			String sql = "SELECT user_id FROM user WHERE user_name = ?";
			Connection con = dbContext.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, userName);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Long userId = rs.getLong(1);
				lstUserId.add(userId);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstUserId.get(0);
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 23, 2020 - 3:34:15 PM
	 * @param username
	 * @return
	 */
	public User getUserInfo(Long userId) {
		try {
			User user = null;
			Connection con = dbContext.getConnection();
			Statement s = con.createStatement();
			String query = "select * from User where user_id='" + userId + "'";
			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				int gender = rs.getInt(6);
				String email = rs.getString(7);
				String address = rs.getString(8);
				int enabled = rs.getInt(9);
				user = new User(userId, username, password, firstName, lastName, gender, email, address, enabled);
			}
			con.close();
			return user;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}
}
