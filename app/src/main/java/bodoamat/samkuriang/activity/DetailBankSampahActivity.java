package bodoamat.samkuriang.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bodoamat.samkuriang.R;

public class DetailBankSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bank_sampah);

        TextView textView = findViewById(R.id.detailNamaBankSampah);
        textView.setText(getIntent().getStringExtra("param"));
    }
}
