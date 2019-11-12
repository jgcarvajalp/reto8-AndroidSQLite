package co.edu.unal.reto8_androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.reto8_androidsqlite.db.CompanyOperations;
import co.edu.unal.reto8_androidsqlite.model.Company;

public class ViewAllCompanies extends ListActivity{

    private CompanyOperations companyOps;
    List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_companies);

        companyOps = new CompanyOperations(this);
        companyOps.open();
        companies = companyOps.getAllCompanys();
        companyOps.close();
        ArrayAdapter<Company> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, companies);
        setListAdapter(adapter);
    }
}
