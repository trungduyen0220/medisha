/**
 * @author ntmduyen
 * @filename MedicineService.java
 * @datetime Jul 24, 2020 - tim8:50:07 AM
 */
package com.shecodes.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shecodes.entity.FormSearch;
import com.shecodes.entity.Medicine;

/**
 * @author ntmduyen
 *
 */
@Service
@Transactional
public interface MedicineService {
	
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 4:49:45 PM
	 * @return
	 */
	public List<Medicine> getAllMedicine();
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 6:10:56 AM
	 * @param storeId
	 * @return
	 */
	public List<Medicine> getAllMedicineOfStore(Long storeId);
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 6:10:53 AM
	 * @param formSearch
	 * @return
	 * @throws SQLException
	 */
	public List<Medicine> getMedicineBySearchForm(List<FormSearch> formSearch) throws SQLException;
	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 26, 2020 - 6:10:49 AM
	 * @param medicineId
	 * @return
	 */
	public Medicine getMedicineFromId(Long medicineId);

}
