package android.example.utsmobapps.ListWisata;

import android.content.Intent;
import android.example.utsmobapps.MenuActivity; // Import MenuActivity
import android.example.utsmobapps.input.InputJawaTengahActivity;
import android.example.utsmobapps.ListWisataAdapter;
import android.example.utsmobapps.R;
import android.example.utsmobapps.RequestHandler;
import android.example.utsmobapps.Wisata;
import android.example.utsmobapps.konfigurasi;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JawaTengahActivity extends AppCompatActivity {
    private RecyclerView rvWisata;
    private ArrayList<Wisata> list = new ArrayList<>();
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawa_tengah);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Set up navigation icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JawaTengahActivity.this, MenuActivity.class);
                startActivity(intent);
                finish(); // Optionally, finish current activity
            }
        });

        rvWisata = findViewById(R.id.rv_wisata);
        rvWisata.setHasFixedSize(true);

        FloatingActionButton fabAdd = findViewById(R.id.favorite_btn_fill);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JawaTengahActivity.this, InputJawaTengahActivity.class);
                startActivity(intent);
            }
        });

        new FetchDataTask().execute();
    }

    private void showRecyclerList() {
        rvWisata.setLayoutManager(new LinearLayoutManager(this));
        ListWisataAdapter listWisataAdapter = new ListWisataAdapter(list);
        rvWisata.setAdapter(listWisataAdapter);
    }

    private class FetchDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();
            return requestHandler.sendGetRequest(konfigurasi.URL_GET_ALL_WISATAJATENG);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject responseObject = new JSONObject(s);
                JSONArray resultArray = responseObject.getJSONArray("result");

                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject jsonObject = resultArray.getJSONObject(i);
                    Wisata wisata = new Wisata(
                            jsonObject.getString("name"),
                            jsonObject.getString("lok"),
                            jsonObject.getString("link"),
                            jsonObject.getString("high"),
                            jsonObject.getString("detail")
                    );
                    list.add(wisata);
                }
                showRecyclerList();
            } catch (JSONException e) {
                Log.e("JawaTengahActivity", "Error parsing JSON data.", e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(JawaTengahActivity.this, MenuActivity.class);
        startActivity(intent);
        finish(); // Optionally, finish current activity
    }
}
