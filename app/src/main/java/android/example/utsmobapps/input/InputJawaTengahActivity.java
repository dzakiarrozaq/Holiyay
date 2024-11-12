package android.example.utsmobapps.input;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.utsmobapps.ListWisata.JawaTengahActivity;
import android.example.utsmobapps.R;
import android.example.utsmobapps.RequestHandler;
import android.example.utsmobapps.konfigurasi;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class InputJawaTengahActivity extends AppCompatActivity implements View.OnClickListener {

    // Define Views
    private EditText editTextName;
    private EditText editTextLok;
    private EditText editTextLink;
    private EditText editTextDetail;
    private EditText editTextHigh;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jateng);

        // Initialize Views
        editTextName = findViewById(R.id.NameEditText);
        editTextLok = findViewById(R.id.LokEditText);
        editTextLink = findViewById(R.id.linkEditText);
        editTextDetail = findViewById(R.id.DetailEditText);
        editTextHigh = findViewById(R.id.HighEditText);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonView = findViewById(R.id.buttonView);

        // Set listeners to buttons
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    // Add new entry to the database
    private void addEntry() {
        final String name = editTextName.getText().toString().trim();
        final String lok = editTextLok.getText().toString().trim();
        final String link = editTextLink.getText().toString().trim();
        final String detail = editTextDetail.getText().toString().trim();
        final String high = editTextHigh.getText().toString().trim();

        class AddEntry extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(InputJawaTengahActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(InputJawaTengahActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA, name);
                params.put(konfigurasi.KEY_EMP_LOKASI, lok);
                params.put(konfigurasi.KEY_EMP_LINK, link);
                params.put(konfigurasi.KEY_EMP_HIGH, high);
                params.put(konfigurasi.KEY_EMP_DETAIL, detail);


                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(konfigurasi.URL_ADD_JATENG, params);
            }
        }

        AddEntry ae = new AddEntry();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAdd) {
            addEntry();
            startActivity(new Intent(this, JawaTengahActivity.class));
        }
        if(v==buttonView){

        }
    }


}