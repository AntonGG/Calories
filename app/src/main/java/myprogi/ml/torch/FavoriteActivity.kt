package myprogi.ml.torch

import android.os.Bundle
import android.util.Log

class FavoriteActivity : BaseActivity(0) {
    private val TAG = "FavoriteActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()
    }
}