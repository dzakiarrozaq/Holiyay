package android.example.utsmobapps.ListWisata;

import android.content.Intent;
import android.example.utsmobapps.input.InputJawaTengahActivity;
import android.example.utsmobapps.ListWisataAdapter;
import android.example.utsmobapps.RequestHandler;
import android.example.utsmobapps.Wisata;
import android.example.utsmobapps.input.InputJawaTimurActivity;
import android.example.utsmobapps.konfigurasi;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.example.utsmobapps.R;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JawaTimurActivity extends AppCompatActivity {

    private RecyclerView rvWisata;
    private ArrayList<Wisata> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jawa_timur);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int redColor = ResourcesCompat.getColor(getResources(), R.color.blue, null);
            actionBar.setBackgroundDrawable(new ColorDrawable(redColor));
            actionBar.setTitle("Tempat Wisata Jawa Timur");
        }

        rvWisata = findViewById(R.id.rv_wisata);
        rvWisata.setHasFixedSize(true);

        FloatingActionButton fabAdd = findViewById(R.id.favorite_btn_fill);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to start InputJawaTengahActivity
                Intent intent = new Intent(JawaTimurActivity.this, InputJawaTimurActivity.class);
                startActivity(intent);
            }
        });

        // Fetch data from the server
        new JawaTimurActivity.FetchDataTask().execute();

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
            String s = requestHandler.sendGetRequest(konfigurasi.URL_GET_ALL_WISATAJATIM);
            return s;
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
                Log.e("JawaTimurActivity", "Error parsing JSON data.", e);
            }
        }

    }

}