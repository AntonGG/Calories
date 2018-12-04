package myprogi.ml.calcCalories

import android.os.Bundle
import android.util.Log
import myprogi.ml.calcCalories.roomDb.ProfileDao
import javax.inject.Inject

class FavoriteActivity : BaseActivity(0) {
    private val TAG = "FavoriteActivity"

    @Inject
    lateinit var database: ProfileDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()

        /*if (database.getProfileByDate(dateNow) == null) {
            startActivity(Intent(this, MyDataActivity::class.java))
        }*/
    }
}