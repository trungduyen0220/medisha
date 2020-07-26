package com.shecodes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shecodes.entity.DrugStore;
import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;
import com.shecodes.utils.DBexecute;

@Service
public class SearchDao {
	
	private static final Logger logger = LogManager.getLogger(MedicineDao.class);

	@Autowired
	public DBexecute dbExecute;
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 5:12:06 PM
	 * @param lstFormSearch
	 * @return
	 */
	public List<DrugStore> getListStoreAfterSearching(List<FormSearch> lstFormSearch){
		List<DrugStore> lstDrugStore = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT d.* FROM drug_store d  " + 
					"LEFT JOIN medicine_in_store  mis ON d.store_id = mis.store_id " + 
					"LEFT JOIN medicine m ON mis.medicine_id = m.medicine_id " 
					+ "WHERE "
					);
 			for(FormSearch formSearch : lstFormSearch) {
 				sql.append("(medicine_name LIKE  '"+formSearch.getMedicineName()+"%'   AND ");
 				sql.append("medicine_unit = '"+formSearch.getMedicineUnit()+"') OR ");
			}
 			String theSql = sql.toString();
 			theSql = theSql.substring(0, theSql.length() - 4) + ";";
			logger.info(theSql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(theSql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				long storeId = rs.getLong(1);
				String storeImage = rs.getString(2);
				String storeUrl = rs.getString(3);
				String storeName = rs.getString(4);
				String storeTel = rs.getString(5);
				String storeAddress = rs.getString(6);
				long parentDrugStoreId = rs.getLong(7);
				boolean enabled = rs.getBoolean(8);
				String description = rs.getString(9);
				DrugStore drugStore = new DrugStore(storeId, storeImage, storeUrl, storeName, storeTel, storeAddress, parentDrugStoreId, enabled, description);
				lstDrugStore.add(drugStore);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstDrugStore;
	}
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 5:12:09 PM
	 * @param lstFormSearch
	 * @param drugStore
	 * @return
	 */
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch, DrugStore drugStore){
		List<Medicine> lstMedicine = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT m.*, mis.price, mis.sale_percent FROM medicine m " + 
					"LEFT JOIN medicine_in_store  mis ON m.medicine_id = mis.medicine_id " + 
					"LEFT JOIN drug_store d ON d.store_id = mis.store_id " 
					+  "WHERE "
					);
			sql.append("(");
			for(FormSearch formSearch : lstFormSearch) {
 				sql.append("(medicine_name LIKE  '"+formSearch.getMedicineName()+"%'   AND ");
 				sql.append("medicine_unit = '"+formSearch.getMedicineUnit()+"') OR ");
			}
 			
 			
 			String theSql = sql.toString();
 			theSql = theSql.substring(0, theSql.length() - 3) +") AND mis.store_id = " + drugStore.getStoreId()+ ";";
			logger.info(theSql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(theSql);
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

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 2:54:31 AM
	 * @return
	 */
	public List<DrugStore> getAllStore(){
		List<DrugStore> lstDrugStore = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM drug_store");
			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(sql.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				long storeId = rs.getLong(1);
				String storeImage = rs.getString(2);
				String storeUrl = rs.getString(3);
				String storeName = rs.getString(4);
				String storeTel = rs.getString(5);
				String storeAddress = rs.getString(6);
				long parentDrugStoreId = rs.getLong(7);
				boolean enabled = rs.getBoolean(8);
				String description = rs.getString(9);
				DrugStore drugStore = new DrugStore(storeId, storeImage, storeUrl, storeName, storeTel, storeAddress, parentDrugStoreId, enabled, description);
				lstDrugStore.add(drugStore);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return lstDrugStore;
	}
	
	public List<Medicine> getAllMedicineByStore(DrugStore drugStore){
		List<Medicine> lstMedicine = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT m.*, mis.price, mis.sale_percent FROM medicine m " + 
					"LEFT JOIN medicine_in_store  mis ON m.medicine_id = mis.medicine_id " + 
					"LEFT JOIN drug_store d ON d.store_id = mis.store_id WHERE mis.store_id = " + drugStore.getStoreId()+";") ;
			
 			String theSql = sql.toString();
			logger.info(theSql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(theSql);
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

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 25, 2020 - 5:12:09 PM
	 * @param lstFormSearch
	 * @param drugStore
	 * @return
	 */
	public List<Medicine> showMedicineByStoreAfterSearching(List<FormSearch> lstFormSearch, Long storeId){
		List<Medicine> lstMedicine = new ArrayList<>();
		try {
			StringBuilder sql = new StringBuilder("SELECT m.*, mis.price, mis.sale_percent FROM medicine m " + 
					"LEFT JOIN medicine_in_store  mis ON m.medicine_id = mis.medicine_id " + 
					"LEFT JOIN drug_store d ON d.store_id = mis.store_id " 
					+  "WHERE "
					);
			sql.append("(");
			for(FormSearch formSearch : lstFormSearch) {
 				sql.append("(medicine_name LIKE  '"+formSearch.getMedicineName()+"%'   AND ");
 				sql.append("medicine_unit = '"+formSearch.getMedicineUnit()+"') OR ");
			}
 			
 			
 			String theSql = sql.toString();
 			theSql = theSql.substring(0, theSql.length() - 3) +") AND mis.store_id = " + storeId+ ";";
			logger.info(theSql);

			Connection con = dbExecute.getConnection();
			PreparedStatement statement = con.prepareStatement(theSql);
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
