package myprogi.ml.torch


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class AboutActivity : AppCompatActivity() {
    private val TAG = "AboutActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

    }
}