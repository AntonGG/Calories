package myprogi.ml.torch

import android.os.Bundle
import android.util.Log

class EatingActivity : BaseActivity(2) {
    private val TAG = "EatingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()
    }
}