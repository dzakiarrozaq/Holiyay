package android.example.utsmobapps;

import android.example.utsmobapps.ListWisata.JakartaActivity;
import android.example.utsmobapps.ListWisata.JawaBaratActivity;
import android.example.utsmobapps.ListWisata.JawaTengahActivity;
import android.example.utsmobapps.ListWisata.JawaTimurActivity;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.example.utsmobapps.databinding.ActivityMenuBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.example.utsmobapps.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set OnClickListeners for each CircleImageView
        binding.Jakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, JakartaActivity.class);
                startActivity(intent);
            }
        });

        binding.JawaTengah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, JawaTengahActivity.class);
                startActivity(intent);
            }
        });

        binding.JawaTimur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, JawaTimurActivity.class);
                startActivity(intent);
            }
        });

        binding.JawaBarat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, JawaBaratActivity.class);
                startActivity(intent);
            }
        });
    }
}
