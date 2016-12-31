package id.sacredgeeks.cobasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by SacredGeeks on 11/28/2016.
 */

public class DbCoba {
    public static class Coba {
        public String nama;
        public String telepon;
    }

    private SQLiteDatabase db;
    private final OpenHelper helper;

    public DbCoba (Context c) {
        helper = new OpenHelper(c);
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertCoba(String nama, String telepon) {
        ContentValues newValues = new ContentValues();
        newValues.put("NAMA",nama);
        newValues.put("TELEPON",telepon);
        return db.insert("coba", null, newValues);
    }

    public Coba getCoba(String nama) {
        Cursor cur = null;
        Coba dbc = new Coba();

        String[] cols = new String [] {"ID","NAMA","TELEPON"};
        String[] param = {nama};

        cur = db.query("coba",cols,"NAMA=?",param,null,null,null);

        if(cur.getCount()>0) {
            cur.moveToFirst();
            dbc.nama = cur.getString(1);
            dbc.telepon = cur.getString(2);
        }
        return dbc;
    }
}
