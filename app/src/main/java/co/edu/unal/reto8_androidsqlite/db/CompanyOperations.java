package co.edu.unal.reto8_androidsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.reto8_androidsqlite.model.Company;

public class CompanyOperations {
    public static final String LOGTAG = "COM_MNGMNT_SYS";
    private static final String[] allColumns = {
            CompanyDBHandler.COLUMN_ID,
            CompanyDBHandler.COLUMN_NAME,
            CompanyDBHandler.COLUMN_URL,
            CompanyDBHandler.COLUMN_TELEPHONE,
            CompanyDBHandler.COLUMN_EMAIL,
            CompanyDBHandler.COLUMN_PRODUCTS_SERVICES,
            CompanyDBHandler.COLUMN_CLASSIFICATION
    };
    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    public CompanyOperations(Context context){
        dbhandler = new CompanyDBHandler(context);
    }

    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }
    public Company addCompany(Company company){
        ContentValues values  = new ContentValues();
        values.put(CompanyDBHandler.COLUMN_NAME, company.getName());
        values.put(CompanyDBHandler.COLUMN_URL, company.getUrl());
        values.put(CompanyDBHandler.COLUMN_TELEPHONE, company.getTelephone());
        values.put(CompanyDBHandler.COLUMN_EMAIL, company.getEmail());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS_SERVICES, company.getProductsAndServices());
        values.put(CompanyDBHandler.COLUMN_CLASSIFICATION, company.getCompanyClassification());

        long insertid = database.insert(CompanyDBHandler.TABLE_COMPANIES,null,values);
        company.setId(insertid);
        return company;

    }

    // Getting single Employee
    public Company getCompanyById(long id) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Company e = new Company(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return Employee
        return e;
    }

    public Company getCompanyByName(String name) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_NAME + "=?",new String[]{name},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Company e = new Company(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return Employee
        return e;
    }

    public Company getCompanyByClassification(String classification) {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,CompanyDBHandler.COLUMN_CLASSIFICATION + "=?",new String[]{classification},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Company e = new Company(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
        // return Employee
        return e;
    }

    public List<Company> getAllCompanys() {

        Cursor cursor = database.query(CompanyDBHandler.TABLE_COMPANIES,allColumns,null,null,null, null, null);

        List<Company> companies = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Company company = new Company();
                company.setId(cursor.getLong(cursor.getColumnIndex(CompanyDBHandler.COLUMN_ID)));
                company.setName(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_NAME)));
                company.setUrl(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_URL)));
                company.setTelephone(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_TELEPHONE)));
                company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_EMAIL)));
                company.setProductsAndServices(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_PRODUCTS_SERVICES)));
                company.setCompanyClassification(cursor.getString(cursor.getColumnIndex(CompanyDBHandler.COLUMN_CLASSIFICATION)));
                companies.add(company);
            }
        }
        // return All Employees
        return companies;
    }

    // Updating Employee
    public int updateCompany(Company company) {

        ContentValues values = new ContentValues();
        values.put(CompanyDBHandler.COLUMN_NAME, company.getName());
        values.put(CompanyDBHandler.COLUMN_URL, company.getUrl());
        values.put(CompanyDBHandler.COLUMN_TELEPHONE, company.getTelephone());
        values.put(CompanyDBHandler.COLUMN_EMAIL, company.getEmail());
        values.put(CompanyDBHandler.COLUMN_PRODUCTS_SERVICES, company.getProductsAndServices());
        values.put(CompanyDBHandler.COLUMN_CLASSIFICATION, company.getCompanyClassification());

        // updating row
        return database.update(CompanyDBHandler.TABLE_COMPANIES, values,
                CompanyDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(company.getId())});
    }

    // Deleting Employee
    public void removeCompany(Company company) {

        database.delete(CompanyDBHandler.TABLE_COMPANIES, CompanyDBHandler.COLUMN_ID + "=" + company.getId(), null);
    }



}
