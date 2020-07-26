/**
 * @author ntmduyen
 * @filename MedicineDao.java
 * @datetime Jul 24, 2020 - tim5:03:01 PM
 */
package com.shecodes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.entity.Role;
import com.shecodes.utils.DBexecute;

/**
 * @author ntmduyen
 *
 */
@Service
public class MedicineDao {

	private static final Logger logger = LogManager.getLogger(MedicineDao.class);

	@Autowired
	public DBexecute dbExecute;

	public List<Medicine> getListAllMedicine() {
		List<Medicine> lstMedicine = new ArrayList<>();
		try {
			String sql = "SELECT * from medicine";
			logger.info(sql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				long medicineId = rs.getLong(1);
				String medicineName = rs.getString(2);
				String medicineUnit = rs.getString(3);
				String medicineAmount = rs.getString(4);
				String medicinePhysical = rs.getString(5);
				boolean prescriptMedication = rs.getBoolean(6);
				String image = rs.getString(7);
				String expiryDate = rs.getString(8);
				String dateCreated = rs.getString(9);
				Long userCreatedId = rs.getLong(10);
				String description = rs.getString(11);
				boolean enabled = rs.getBoolean(12);
				int maxQuantity = rs.getInt(13);

				lstMedicine.add(new Medicine(medicineId, medicineName, medicineUnit, medicineAmount, medicinePhysical,
						prescriptMedication, image, expiryDate, dateCreated, userCreatedId, description, enabled,
						maxQuantity));
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstMedicine;
	}

	public List<Medicine> getMedicineFromSearchForm(List<FormSearch> formSearch) throws SQLException{
		List<Medicine> lstMedicine = new ArrayList<>();
		for( FormSearch form: formSearch) {
			String medicineName = form.getMedicineName();
			String medicineAmount = form.getMedicineAmount();
			String medicineUnit = form.getMedicineUnit();

			String sql = "SELECT * FROM medicine WHERE medicine_name = ? AND medicine_amount = ? AND medicine_unit = ?";
			logger.info(sql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, medicineName);
			statement.setString(2, medicineAmount);
			statement.setString(3, medicineUnit);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				long medicineId = rs.getLong(1);
				String medicinePhysical = rs.getString(5);
				boolean prescriptMedication = rs.getBoolean(6);
				String image = rs.getString(7);
				String expiryDate = rs.getString(8);
				String dateCreated = rs.getString(9);
				Long userCreatedId = rs.getLong(10);
				String description = rs.getString(11);
				boolean enabled = rs.getBoolean(12);
				int maxQuantity = rs.getInt(13);
				
				lstMedicine.add(new Medicine(medicineId, medicineName, medicineUnit, medicineAmount, medicinePhysical,
						prescriptMedication, image, expiryDate, dateCreated, userCreatedId, description, enabled,
						maxQuantity));

			}
		}
		return lstMedicine;
	}

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 6:14:36 AM
	 * @param storeId
	 * @return
	 */
	public List<Medicine> getAllMedicineOfStore(Long storeId){
		List<Medicine> lstMedicine = new ArrayList<>();
		try {

			String sql = "SELECT m.*, mis.price, mis.sale_percent FROM medicine m " + 
					"LEFT JOIN medicine_in_store  mis ON m.medicine_id = mis.medicine_id " + 
					"LEFT JOIN drug_store d ON d.store_id = mis.store_id WHERE mis.store_id = " + storeId+";";
			logger.info(sql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				long medicineId = rs.getLong(1);
				String medicineName = rs.getString(2);
				String medicineUnit = rs.getString(3);
				String medicineAmount = rs.getString(4);
				String medicinePhysical = rs.getString(5);
				boolean prescriptMedication = rs.getBoolean(6);
				String image = rs.getString(7);
				String expiryDate = rs.getString(8);
				String dateCreated = rs.getString(9);
				Long userCreatedId = rs.getLong(10);
				String description = rs.getString(11);
				boolean enabled = rs.getBoolean(12);
				int maxQuantity = rs.getInt(13);
				int price = rs.getInt(14);
				int salePercent = rs.getInt(15);
				Medicine medicine = new Medicine(medicineId, medicineName, medicineUnit, medicineAmount, medicinePhysical,
						prescriptMedication, image, expiryDate, dateCreated, userCreatedId, description, enabled,
						maxQuantity);
				medicine.setPrice(price);
				medicine.setSalePercent(salePercent);
				float realPrice = price / 100 * (100 - salePercent);
				medicine.setRealPrice(realPrice);
				lstMedicine.add(medicine);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstMedicine;
	}
}
