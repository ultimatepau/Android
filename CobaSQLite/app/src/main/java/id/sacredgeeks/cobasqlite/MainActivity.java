package id.sacredgeeks.cobasqlite;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbCoba db = new DbCoba(getApplicationContext());
        db.open();
        db.insertCoba("Zaldi","082240990828");

        DbCoba.Coba c = db.getCoba("Zaldi");
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setMessage("Nama = "+c.nama+" Telepon = "+c.telepon);
        ad.show();
    }
}
