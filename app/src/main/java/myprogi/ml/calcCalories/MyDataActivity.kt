package myprogi.ml.calcCalories

import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_mydata.*
import myprogi.ml.calcCalories.database.DatabaseHandler
import myprogi.ml.calcCalories.database.Profile

class MyDataActivity : BaseActivity(1) {
    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mydata)
        Log.d(TAG, "onCreate")
    }

    fun saveData(view: View) {
        val dbHandler = DatabaseHandler(this, null, null, 13)
        val sex = (if (radioMan.isChecked) radioMan.text else radioWoman.text).toString()
        val profile = Profile(dateNow, sex)

        dbHandler.add(profile)
        this.onBackPressed()
    }
}