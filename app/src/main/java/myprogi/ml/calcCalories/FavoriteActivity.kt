package myprogi.ml.calcCalories

import android.content.Intent
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
        App.getAppComponent().inject(this)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()
        /*database.getProfileByDate(dateNow).observe(this, Observer<Profile> {
            it?.let { Log. }
        })*/

        if (database.getProfileByDate(dateNow).value == null) {
            startActivity(Intent(this, MyDataActivity::class.java))
        }
    }
}