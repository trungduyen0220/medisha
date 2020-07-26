package com.example.model;

import com.example.entities.Medicine;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineModel {

    public ArrayList<Medicine> getMidicines() {
        ArrayList<Medicine> medicines = new ArrayList<Medicine>();
        try {
            String sql = "select * from Book";
            Connection connection =null; //= new DBContext().getConnection()
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String unit = rs.getString(3);
                int quantity = rs.getInt(4);

                medicines.add(new Medicine(id, name, quantity, unit));
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicineModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicines;
    }

    // Get All Medicine from database
    public List<Medicine> getMedicines() {
        List<Medicine> medicineNameListInDB = new ArrayList<>();
        medicineNameListInDB.add(new Medicine("Cefadroxil"));
        medicineNameListInDB.add(new Medicine("Alpha chymotrypsin"));
        medicineNameListInDB.add(new Medicine("Metronidazol"));
        medicineNameListInDB.add(new Medicine("Paracetamol (Mypara)"));
        medicineNameListInDB.add(new Medicine("Cerepone Dry (Cefprozil)"));
        medicineNameListInDB.add(new Medicine("Ibufen D (Ibuprofen)"));
        medicineNameListInDB.add(new Medicine("Thymorosin (Thymododulin)"));
        medicineNameListInDB.add(new Medicine("Kidafort (Sắt, Vitamin, AcidAdmin)"));
        medicineNameListInDB.add(new Medicine("Calciemgeral (Calcium, Vitamin D3)"));
        medicineNameListInDB.add(new Medicine("Calci carbonat"));
        medicineNameListInDB.add(new Medicine("Vitamin C"));
        medicineNameListInDB.add(new Medicine(""));
        medicineNameListInDB.add(new Medicine(""));
        medicineNameListInDB.add(new Medicine(""));
        medicineNameListInDB.add(new Medicine(""));
        medicineNameListInDB.add(new Medicine(""));
        return  medicineNameListInDB;
    }

    // Get quantity
    public List<String> setListUnitMedicine() {
        List<String> unitMed = new ArrayList<>();
        unitMed = new ArrayList<>();
        unitMed.add("Vien");
        unitMed.add("Vi");
        unitMed.add("Goi");
        unitMed.add("Hop");
        unitMed.add("Chai");
        unitMed.add("Lo");
        unitMed.add("Tui");
        unitMed.add("Chai");
        unitMed.add("Ong");
        unitMed.add("Cai");
        unitMed.add("Chiec");
        return unitMed;
    }

}
