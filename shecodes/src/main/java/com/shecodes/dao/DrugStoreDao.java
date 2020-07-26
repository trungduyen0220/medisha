package com.shecodes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.entity.DrugStore;
import com.shecodes.utils.DBexecute;

@Service
public class DrugStoreDao {
	private static final Logger logger = LogManager.getLogger(MedicineDao.class);

	@Autowired
	public DBexecute dbExecute;
	
	public DrugStore getDrugStoreDetail(Long drugStoreId) {
		DrugStore drugStore = new DrugStore();
		try {
			String sql = "SELECT * FROM drug_store WHERE store_id = ?";
			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setLong(1, drugStoreId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Long storeId = rs.getLong(1);
				String storeImage = rs.getString(2);
				String storeUrl = rs.getString(3);
				String storeName = rs.getString(4);
				String storeTel = rs.getString(5);
				String storeAddress = rs.getString(6);
				Long parentDrugStoreId = rs.getLong(7);
				boolean enabled = rs.getBoolean(8);
				String description = rs.getString(9);
				drugStore = new DrugStore(storeId, storeImage, storeUrl, storeName, storeTel, storeAddress, parentDrugStoreId, enabled, description);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return drugStore;
	}
}
