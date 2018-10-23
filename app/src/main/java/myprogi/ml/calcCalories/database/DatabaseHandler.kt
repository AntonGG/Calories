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
    private val TAG = "DatabaseHandler"

    companion object {
        val DATABASE_NAME = "db"
        //val DATABASE_VERSION = 1
        val TABLE_PROFILE = "profile"

        val COLUMN_ID = "_id"
        val COLUMN_DATETIME = "date_time"
        val COLUMN_SEX = "sex"

        val COLUMN_YEARS = "years"
        val COLUMN_GROWTH = "growth"
        val COLUMN_WEIGHT = "weight"
        val COLUMN_ACTIVITY = "activity"
        val COLUMN_ZBU_FATS = "zbu_fats"
        val COLUMN_ZBU_PROTEINS = "zbu_proteins"
        val COLUMN_ZBU_CARBS = "zbu_carbs"
    }

    fun add(profile: Profile) {
        val db = this.writableDatabase
        try {
            val values = ContentValues()
            values.put(COLUMN_DATETIME, profile.dateTime)
            values.put(COLUMN_SEX, profile.sex)
            values.put(COLUMN_YEARS, profile.years)
            values.put(COLUMN_GROWTH, profile.growth)
            values.put(COLUMN_WEIGHT, profile.weight)
            values.put(COLUMN_ACTIVITY, profile.activity)
            values.put(COLUMN_ZBU_FATS, profile.zbu_fats)
            values.put(COLUMN_ZBU_PROTEINS, profile.zbu_proteins)
            values.put(COLUMN_ZBU_CARBS, profile.zbu_carbs)
            //если запись на сегодняшнюю дату не найдена то добавим
            if (find(profile.dateTime!!) == null) {
                Log.d(TAG, """${db.isOpen}  ${db.isDbLockedByCurrentThread}""")
                val res = db.insert(TABLE_PROFILE, null, values)
                if (res == -1L)
                    Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
            } else {
                db.update(TABLE_PROFILE, values, """$COLUMN_DATETIME = "$dateNow"""", null)
            }

        } catch (e: Exception) {
            Log.e(TAG, e.toString())
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
            db.delete(TABLE_PROFILE, "$COLUMN_ID = ?", arrayOf(id.toString()))
            cursor.close()
            result = true
        }
        db.close()
        return result
    }

    fun findAll(): List<Profile> {
        val taskList = ArrayList<Profile>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_PROFILE"
        val cursor = db.rawQuery(selectQuery, null)

        while (cursor.moveToNext()) {
            val id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
            val dateTime = cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME))
            val sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX))
            val years = cursor.getInt(cursor.getColumnIndex(COLUMN_YEARS))
            val growth = cursor.getInt(cursor.getColumnIndex(COLUMN_GROWTH))
            val weight = cursor.getInt(cursor.getColumnIndex(COLUMN_WEIGHT))
            val activity = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY))
            val fats = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_FATS))
            val proteins = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_PROTEINS))
            val carbs = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_CARBS))
            taskList.add(Profile(id, dateTime, sex, years, growth, weight, activity,
                    fats, proteins, carbs))
        }

        // cursor.close()
        return taskList
    }

    fun find(date: String): Profile? {
        val query = """SELECT * FROM $TABLE_PROFILE WHERE $COLUMN_DATETIME = "$date""""
        val db = this.writableDatabase
        val cursor = db.rawQuery(query, null)
        var profile: Profile? = null

        if (cursor.moveToFirst()) {
            cursor.moveToFirst()
            val id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
            val date = cursor.getString(cursor.getColumnIndex(COLUMN_DATETIME))
            val sex = cursor.getString(cursor.getColumnIndex(COLUMN_SEX))
            val years = cursor.getInt(cursor.getColumnIndex(COLUMN_YEARS))
            val growth = cursor.getInt(cursor.getColumnIndex(COLUMN_GROWTH))
            val weight = cursor.getInt(cursor.getColumnIndex(COLUMN_WEIGHT))
            val activity = cursor.getString(cursor.getColumnIndex(COLUMN_ACTIVITY))
            val fats = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_FATS))
            val proteins = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_PROTEINS))
            val carbs = cursor.getInt(cursor.getColumnIndex(COLUMN_ZBU_CARBS))

            profile = Profile(id, date, sex, years, growth,
                    weight, activity, fats, proteins, carbs)
        }
        // cursor.close()
        //  db.close()
        //  this.close()
        return profile
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("""CREATE TABLE $TABLE_PROFILE ($COLUMN_ID INTEGER PRIMARY KEY,
$COLUMN_DATETIME TEXT,
$COLUMN_SEX TEXT,
$COLUMN_YEARS INTEGER,
$COLUMN_GROWTH INTEGER,
$COLUMN_WEIGHT INTEGER,
$COLUMN_ACTIVITY TEXT,
$COLUMN_ZBU_FATS INTEGER,
$COLUMN_ZBU_PROTEINS INTEGER,
$COLUMN_ZBU_CARBS INTEGER);""")
        Log.d("DatabaseHandler", """${p0?.version}  ${p0?.isOpen} ${p0?.path}""")
        Log.d("DatabaseHandler", " onCreate()")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("""DROP TABLE IF EXISTS $TABLE_PROFILE;""")
        onCreate(p0)
        Log.d("DatabaseHandler", " onUpgrade()")
    }
}
