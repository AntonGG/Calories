package myprogi.ml.calcCalories.roomDb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Profile::class), version = 2)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun dao(): ProfileDao
}