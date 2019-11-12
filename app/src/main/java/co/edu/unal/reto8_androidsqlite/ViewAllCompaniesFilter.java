package co.edu.unal.reto8_androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import co.edu.unal.reto8_androidsqlite.db.CompanyOperations;
import co.edu.unal.reto8_androidsqlite.model.Company;

public class ViewAllCompaniesFilter extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    private CompanyOperations companyOps;
    List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_companies_filter);

        list = (ListView) findViewById(R.id.listViewCompaniesFilter);

        companyOps = new CompanyOperations(this);
        companyOps.open();
        companies = companyOps.getAllCompanys();
        companyOps.close();

        adapter = new ListViewAdapter(this, companies);
        list.setAdapter(adapter);

        editsearch = (SearchView) findViewById(R.id.searchView);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
