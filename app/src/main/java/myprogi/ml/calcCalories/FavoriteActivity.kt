package myprogi.ml.calcCalories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import myprogi.ml.calcCalories.database.DatabaseHandler

class FavoriteActivity : BaseActivity(0) {
    private val TAG = "FavoriteActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()

        if(DatabaseHandler(this, null, null, dbVersion).find(dateNow)==null){
            startActivity(Intent(this, MyDataActivity::class.java))
        }
    }
}