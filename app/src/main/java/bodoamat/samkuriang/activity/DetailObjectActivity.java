package bodoamat.samkuriang.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bodoamat.samkuriang.R;
import bodoamat.samkuriang.api.ConfigUtils;
import bodoamat.samkuriang.api.Service;
import bodoamat.samkuriang.helper.GFG;
import bodoamat.samkuriang.models.Garbage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailObjectActivity extends AppCompatActivity {

    TextView nama_sampah,jenis_sampah;
    ImageView image_object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_object);

        // get id from layout
        nama_sampah = findViewById(R.id.nama_sampah);
        jenis_sampah = findViewById(R.id.jenis_sampah);
        image_object = findViewById(R.id.image_object);

        // setter
        nama_sampah.setText(GFG.convert(getIntent().getStringExtra("label_sampah")));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<Garbage> callGarbageDetail = service.getGarbageDetail(
                getIntent().getStringExtra("label_sampah")
        );

        callGarbageDetail.enqueue(new Callback<Garbage>() {
            @Override
            public void onResponse(Call<Garbage> callGarbageDetail, Response<Garbage> response) {
                jenis_sampah.setText(response.body().getType());
                Picasso.get().load(response.body().getBackground_images()).into(image_object);
            }

            @Override
            public void onFailure(Call<Garbage> callGarbageDetail, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
