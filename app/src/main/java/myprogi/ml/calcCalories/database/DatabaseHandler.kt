package myprogi.ml.calcCalories.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import myprogi.ml.calcCalories.dateNow

class DatabaseHandler(val context: Context,
                      name: String?,
                      factory: SQLiteDatabase.CursorFactory?,
                      version: Int) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, version) {

    companion object {
        val DATABASE_NAME = "db"
        //val DATABASE_VERSION = 1
        val TABLE_PROFILE = "profile"

        val COLUMN_ID = "_id"
        val COLUMN_DATETIME = "date_time"
        val COLUMN_SEX = "sex"
    }

    fun add(profile: Profile) {
        val db = this.writableDatabase
        try {
            val values = ContentValues()
            values.put(COLUMN_DATETIME, profile.dateTime)
            values.put(COLUMN_SEX, profile.sex)
            Log.d("DDDDDDDDatabaseHandler", """${db.isOpen}  ${db.isDbLockedByCurrentThread}""")
            if (find(profile.dateTime!!) == null) {
                Log.d("DDDDDDDDDatabaseHandler", """${db.isOpen}  ${db.isDbLockedByCurrentThread}""")
                val res = db.insert(TABLE_PROFILE, null, values)
                if (res == -1L)
                    Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
            } else {
                db.update(TABLE_PROFILE, values, """$COLUMN_DATETIME = "$dateNow"""", null)
            }

        } finally {
          //  db.close()
        }
    }

    fun delete(date: String): Boolean {
        var result = false
        val query = """SELECT * FROM $TABLE_PROFILE WHERE $COLUMN_DATETIME = "$date;""""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_PROFILE, COLUMN_ID + " = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    fun find(date: String): Profile? {
        val query = """SELECT * FROM $TABLE_PROFILE WHERE $COLUMN_DATETIME = "$date""""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var profile: Profile? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(0))
            val date = cursor.getString(1)
            val sex = cursor.getString(2)
            profile = Profile(id, date, sex)
        }
       // cursor.close()
      //  db.close()
      //  this.close()
        return profile
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("""CREATE TABLE $TABLE_PROFILE ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_DATETIME TEXT, $COLUMN_SEX TEXT);""")
        Log.d("DatabaseHandler", """${p0?.version}  ${p0?.isOpen} ${p0?.path}""")
        Log.d("DatabaseHandler", " onCreate()")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("""DROP TABLE IF EXISTS $TABLE_PROFILE;""")
        onCreate(p0)
        Log.d("DatabaseHandler", " onUpgrade()")
    }
}
