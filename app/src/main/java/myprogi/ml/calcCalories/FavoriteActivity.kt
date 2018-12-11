package myprogi.ml.calcCalories

import android.arch.lifecycle.Observer
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

        //раз в день будет открываться окно с данными для их редактирования
        database.getProfileByDate(dateNow).observe(this, Observer {
            if (it == null) {
                startActivity(Intent(this, MyDataActivity::class.java))
            }
        })

    }
}