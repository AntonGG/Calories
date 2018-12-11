package myprogi.ml.calcCalories.roomDb

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val date: String,
        val name: String,
        val sex: String,
        val years: Int,
        val growth: Int,
        val weight: Float,
        val activity: String,
        val zbu_fats: Int,
        val zbu_proteins: Int,
        val zbu_carbs: Int)