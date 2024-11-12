package android.example.utsmobapps.DetailWisata;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.example.utsmobapps.R;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailWisataJawaTimurActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PHOTO = "extra_photo";
    public static final String EXTRA_DESCRIPTION = "extra_description";
    public static final String EXTRA_HIGHLIGHTS = "extra_highlights";
    public static final String EXTRA_LOCATION = "extra_location";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int redColor = ResourcesCompat.getColor(getResources(), R.color.blue, null);

            // Set the background color of ActionBar to red
            actionBar.setBackgroundDrawable(new ColorDrawable(redColor));
            actionBar.setTitle("Detail");
        }
        setContentView(R.layout.activity_detail_wisata_jawa_timur);

        TextView tvSetName = findViewById(R.id.tv_set_name);
        ImageView imgSetPhoto = findViewById(R.id.img_item_photo);
        TextView tvDescription = findViewById(R.id.tv_set_description);
        TextView tvSetHighlights = findViewById(R.id.tv_set_highlights);
        TextView tvLocation = findViewById(R.id.tv_item_location);

        Intent intent = getIntent();
        String tName = intent.getStringExtra(EXTRA_NAME);
        String tImg = intent.getStringExtra(EXTRA_PHOTO);
        String tDescription = intent.getStringExtra(EXTRA_DESCRIPTION);
        String tHighlights = intent.getStringExtra(EXTRA_HIGHLIGHTS);
        String tLocation = intent.getStringExtra(EXTRA_LOCATION);

        tvSetName.setText(tName);
        Glide.with(this)
                .load(tImg)
                .apply(new RequestOptions()) // You can set a placeholder image if needed
                .into(imgSetPhoto);
        tvSetHighlights.setText(tHighlights);
        tvDescription.setText(tDescription);
        tvLocation.setText(tLocation);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}