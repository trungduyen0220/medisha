package com.example.model;


import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBContext extends AsyncTask<Void,Void,Void> {
    private static String hostname = "192.168.9.24";
    private static String port = "3306";
    private static String dbname = "baka";
    private static String user = "root";
    private static String password = "123456";

    String records = "";
    String error="";

    @Override
    protected Void doInBackground(Void... voids) {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ hostname + ":" + port + "/" + dbname, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while(resultSet.next()) {
                records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
            }
        }
        catch(Exception e)
        {
            error = e.toString();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
/*Advice: If the instance of the drivers doesn't give any errors but you get an excep
tion with the connection you should try to remove the Target SDK version from your manifest, as for some versions this gives problems.*/