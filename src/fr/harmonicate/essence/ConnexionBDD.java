package fr.harmonicate.essence;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConnexionBDD extends SQLiteOpenHelper {

	private final String TABLE ="tablePlein";
	
	private final String ID = "id";
	private final String NOM ="nom";
	private final String DATE ="date";
	private final String PRIX  ="prix";
	public ConnexionBDD(Context context) {
		super(context, "plein", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		String CREATE="CREATE TABLE " + TABLE + "(" + ID + 
				" INTEGER PRIMARY KEY AUTOINCREMENT," +
				NOM + " TEXT," + DATE + " TEXT," + PRIX + " TEXT)";
		arg0.execSQL(CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS " + TABLE);
		
		onCreate(arg0);
	}
	
	public void addPlein(Plein plein) {
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(NOM, plein.getNomPersonne());
		values.put(DATE, plein.getDatePlein());
		values.put(PRIX, plein.getPrixLitre());
		db.insert(TABLE, null, values);
		db.close();
	}
	
	public Plein getPlein(int id)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor=db.query(TABLE, new String[]{ID, NOM, DATE, PRIX}, ID+"=?",
				new String[]{String.valueOf(id)},null,null,null,null);
		if (cursor!=null)
			cursor.moveToFirst();
		Plein plein=new Plein(cursor.getString(1),cursor.getString(2),cursor.getString(3));
		return plein;
	}

	public List<Plein> getAllPlein() {
		List<Plein> listePlein = new ArrayList<Plein>();
		String selectQuery = "SELECT * FROM " + TABLE;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst()) {
			do {
				Plein plein = new Plein(cursor.getString(1),cursor.getString(2),cursor.getString(3));
				listePlein.add(plein);
			} while (cursor.moveToNext());
		}
		
		return listePlein;
	}
}
