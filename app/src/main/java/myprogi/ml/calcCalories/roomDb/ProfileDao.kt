package myprogi.ml.calcCalories.roomDb

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles WHERE id = :id")
    fun getProfileById(id: String): LiveData<Profile>

    @Query("SELECT * FROM profiles WHERE date = :date")
    fun getProfileByDate(date: String):LiveData<Profile>

    @Insert
    fun insert(profile: Profile)
}