package co.edu.unal.reto8_androidsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.edu.unal.reto8_androidsqlite.db.CompanyOperations;
import co.edu.unal.reto8_androidsqlite.model.Company;

public class AddUpdateEmployee extends AppCompatActivity {

    private static final String EXTRA_COMPANY_ID = "co.edu.unal.companyId";
    private static final String EXTRA_ADD_UPDATE = "co.edu.unal.add_update";
    private TextInputEditText tilCompanyName;
    private TextInputEditText tilUrl;
    private EditText etPhoneNumber;
    private EditText etEmail;
    private EditText etProductsServices;
    private Spinner spinnerCompanyClassification;
    private Button saveUpdateButton;
    private Long companyId;
    private CompanyOperations companyData;
    private Company newCompany;
    private Company oldCompany;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_update_employee);

        newCompany = new Company();
        oldCompany = new Company();
        tilCompanyName = (TextInputEditText)findViewById(R.id.tilCompanyName);
        tilUrl = (TextInputEditText)findViewById(R.id.tilCompanyUrl);
        etPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);
        etEmail = (EditText) findViewById(R.id.editTextPhoneNumber);
        etProductsServices = (EditText) findViewById(R.id.editTextProductsServices);

        spinnerCompanyClassification = (Spinner) findViewById(R.id.spinnerCompanyClassification);
        SpinnerActivity spinAct = new SpinnerActivity();
        spinnerCompanyClassification.setOnItemSelectedListener(spinAct);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.companies_classification_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerCompanyClassification.setAdapter(adapter);

        saveUpdateButton = (Button) findViewById(R.id.buttonSaveCompany);
        companyData = new CompanyOperations(this);
        companyData.open();

        mode = getIntent().getStringExtra(EXTRA_ADD_UPDATE);
        if(mode.equals("Update")){

            saveUpdateButton.setText("Actualizar empresa");
            companyId = getIntent().getLongExtra(EXTRA_COMPANY_ID,0);
            initializeCompany(companyId);

        }

        saveUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mode.equals("Add")) {
                    newCompany.setName(tilCompanyName.getText().toString());
                    newCompany.setUrl(tilUrl.getText().toString());
                    newCompany.setTelephone(etPhoneNumber.getText().toString());
                    newCompany.setEmail(etEmail.getText().toString());
                    newCompany.setProductsAndServices(etProductsServices.getText().toString());
                    newCompany.setCompanyClassification(spinnerCompanyClassification.getSelectedItem().toString());
                    companyData.addCompany(newCompany);
                    Toast t = Toast.makeText(AddUpdateEmployee.this, "La empresa "+ newCompany.getName() + " ha sido agregada con éxito", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateEmployee.this,MainActivity.class);
                    startActivity(i);
                }else {
                    oldCompany.setName(tilCompanyName.getText().toString());
                    oldCompany.setUrl(tilUrl.getText().toString());
                    oldCompany.setTelephone(etPhoneNumber.getText().toString());
                    oldCompany.setEmail(etEmail.getText().toString());
                    oldCompany.setProductsAndServices(etProductsServices.getText().toString());
                    oldCompany.setCompanyClassification(spinnerCompanyClassification.getSelectedItem().toString());
                    companyData.updateCompany(oldCompany);
                    Toast t = Toast.makeText(AddUpdateEmployee.this, "La empresa "+ oldCompany.getName() + " ha sido actualizada con éxito", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddUpdateEmployee.this,MainActivity.class);
                    startActivity(i);

                }


            }
        });
    }

    private void initializeCompany(long companyId) {
        oldCompany = companyData.getCompanyById(companyId);
        tilCompanyName.setText(oldCompany.getName());
        tilUrl.setText(oldCompany.getUrl());
        etPhoneNumber.setText(oldCompany.getTelephone());
        etEmail.setText(oldCompany.getEmail());
        etProductsServices.setText(oldCompany.getProductsAndServices());
        if(oldCompany.getCompanyClassification().equals("Consultoría")){
            spinnerCompanyClassification.setSelection(0);
        }else if (oldCompany.getCompanyClassification().equals("Desarrollo a la medida")){
            spinnerCompanyClassification.setSelection(1);
        }else if(oldCompany.getCompanyClassification().equals("Fábrica de software")){
            spinnerCompanyClassification.setSelection(2);
        }

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }
}
