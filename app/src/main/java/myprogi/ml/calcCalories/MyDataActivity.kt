package myprogi.ml.calcCalories

import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mydata.*
import myprogi.ml.calcCalories.roomDb.Profile
import myprogi.ml.calcCalories.roomDb.ProfileDao
import javax.inject.Inject

class MyDataActivity : BaseActivity(1) {
    private val TAG = "MyDataActivity"

    @Inject
    lateinit var database: ProfileDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mydata)
        Log.d(TAG, "onCreate")
        App.getAppComponent().inject(this)
        buttonSave.setOnClickListener { Observable.just(saveData()).subscribeOn(Schedulers.io()) }

        /* DatabaseHandler(this, null, null, dbVersion).findAll().lastOrNull()?.let {
             if (it.sex == "Мужчина") {
                 radioMan.isChecked = true
             } else {
                 radioWoman.isChecked = true
             }

             edit_age.setText(it.years.toString())
             edit_growth.setText(it.growth.toString())
             edit_weight.setText(it.weight.toString())
             when (it.activity) {
                 "low" -> low_activ.isChecked = true
                 "light" -> light_activ.isChecked = true
                 "middle" -> middle_activ.isChecked = true
                 "hard" -> hard_activ.isChecked = true
             }

             when (it.zbu_carbs) {
                 50 -> standart_zbu.isChecked = true
                 40 -> pohudenie_zbu.isChecked = true
                 30 -> sushka_zbu.isChecked = true
             }
         }*/
        rootView.clearFocus()
    }

    private fun saveData() {
        val sex = (if (radioMan.isChecked) radioMan.text else radioWoman.text).toString()
        val years = edit_age.text.toString().toInt()
        val growth = edit_growth.text.toString().toInt()
        val weight = edit_weight.text.toString().toInt()
        val activity: String = when {
            low_activ.isChecked -> "low"
            light_activ.isChecked -> "light"
            middle_activ.isChecked -> "middle"
            else -> "hard"
        }
        //эти значения в процентах
        var zbu_fats: Int = 0
        var zbu_proteins: Int = 0
        var zbu_carbs: Int = 0
        when {
            standart_zbu.isChecked -> {
                zbu_carbs = 50
                zbu_proteins = 30
                zbu_fats = 20
            }
            pohudenie_zbu.isChecked -> {
                zbu_carbs = 40
                zbu_proteins = 30
                zbu_fats = 30
            }
            sushka_zbu.isChecked -> {
                zbu_carbs = 30
                zbu_proteins = 50
                zbu_fats = 20
            }
        }

        val profile = Profile(0, dateNow, edit_name.text.toString(), sex, years, growth,
                weight, activity, zbu_fats, zbu_proteins, zbu_carbs)

        database.insert(profile)
        this.onBackPressed()
    }
}