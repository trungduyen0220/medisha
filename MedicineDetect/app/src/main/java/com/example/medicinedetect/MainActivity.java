package com.example.medicinedetect;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.entities.Medicine;
import com.example.model.MedicineModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.common.VNCharacterUtils.removeAccent;

public class MainActivity extends AppCompatActivity {

    Button chooseImage;
    ImageView image;
    TextView text;

    String textConverted;
    List<String> unitMed;
    ArrayList<String> lineConvertedList;
    List<Medicine> medicineNameListInDB;
    List<Medicine> medicineList;
    MedicineModel medicineModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        medicineModel = new MedicineModel();
        medicineList = new ArrayList<>();
        chooseImage = findViewById(R.id.bt_choose_image);
        image = findViewById(R.id.iv_image);
        text = findViewById(R.id.tv_text);
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChooseImage();
            }
        });
    }

    public void setChooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != selectedImageUri) image.setImageURI(selectedImageUri);
                runTextRecognition(bitmap);
            }
        }
    }

    private void runTextRecognition(Bitmap mSelectedImage) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage);
        FirebaseVisionTextDetector detector = FirebaseVision.getInstance().getVisionTextDetector();
        detector.detectInImage(image).addOnSuccessListener(
                new OnSuccessListener<FirebaseVisionText>() {
                    @Override
                    public void onSuccess(FirebaseVisionText texts) {
                        processTextRecognitionResult(texts);
                        convertDataToEntityMedicine();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.Block> blocks = texts.getBlocks();
        if (blocks.size() == 0) {
            text.setText("No text found!");
            return;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++)
                    sb.append(elements.get(k).getText()).append(" ");
                sb.append("\n");
            }
            sb.append("\n");
        }
        textConverted = sb.toString();
        text.setText(textConverted);
    }

    // Get All Medicine from database
//    private void getAllMedicines() {
//        MedicineModel medicineModel = new MedicineModel();
//        return medicineModel.getMidicines();
//        new DBContext().execute();
//    }

    // Process data converted to entity
    private void convertDataToEntityMedicine() {
        medicineNameListInDB = medicineModel.getMedicines();
        unitMed = medicineModel.setListUnitMedicine();

        List<String> nameList = new ArrayList<>();
        List<Integer> quantityList = new ArrayList<>();
        List<String> unitList = new ArrayList<>();

        // split String to list of string
        lineConvertedList = new ArrayList<String>(Arrays.asList(textConverted.split("\n")));
        lineConvertedList.removeAll(Arrays.asList("", null));


        // set data to list if satisfy
        for (int i = 0; i < lineConvertedList.size(); i++) {
            String line = lineConvertedList.get(i);
            if (isUnitMedicine(line)){
                // get line which are unit in Prescription
                unitList.add(line);
                // get quantity in the line right after unit
                try {
                    quantityList.add(Integer.parseInt(lineConvertedList.get(i+1).trim()));
                }
                catch (NumberFormatException e)
                {

                }
            }
            else
            {
                // compare to check if line is medicine name
                if(isMedicineName(line)){
                    nameList.add(line);
                }
            }
        }

        // set list data to list object
        int minElement = Math.min(unitList.size(), nameList.size());
        minElement = Math.min(minElement, quantityList.size());

        StringBuilder tempText = new StringBuilder();
        for (int index = 0; index < minElement; index++){
            medicineList.add(new Medicine(nameList.get(index), quantityList.get(index), unitList.get(index)));
            tempText.append(medicineList.get(index).getName()
                    + " - " + medicineList.get(index).getQuantity()
                    +" - "+ medicineList.get(index).getUnit());
            tempText.append("\n");
        }

        text.setText(tempText.toString());
    }

    private  boolean isUnitMedicine(String lineConverted){
        lineConverted = removeAccent(lineConverted);
        for (String unitM : unitMed) {
            if (unitM.equalsIgnoreCase(lineConverted.trim())) {
                return true;
            }
        }
        return false;
    }

    private boolean isMedicineName(String name){
        String medicineName = getNameMedicineInLine(name);
        if (medicineName.equals("")){
            return false;
        }
        for (Medicine medicine : medicineNameListInDB) {
            if (medicine.getName().contains(medicineName)) {
                return true;
            }
        }
        return false;
    }

    private String getNameMedicineInLine(String name){
        int indexStart = 0;
        int indexEnd = 0;
        // get index of first char in String

        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(name);
        if (m.find()) {
            indexStart = m.start();
        }

        // get first index of character after space
        if (indexStart > -1){
            indexEnd = name.substring(indexStart).indexOf(' ');
            if (indexEnd > (indexStart + 1)){
                return name.substring(indexStart, indexEnd);
            }
        }
        return "";
    }
}