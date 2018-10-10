package myprogi.ml.torch

import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity(3), SeekBar.OnSeekBarChangeListener {
    private val TAG = "ProfileActivity"

    private var progressView: TextView? = null
    private var seekBarView: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        Log.d(TAG, "onCreate")
        setupBottomNavigation()

        progressView = this.progress
        seekBarView = this.seekBar
        seekBarView!!.setOnSeekBarChangeListener(this)

        drinkButton.setOnClickListener { showDrinkAdd() }
        addDrinkButton.setOnClickListener { showDrinkAdd() }
    }

    fun showDrinkAdd() =
            if (adding_product.visibility == FrameLayout.VISIBLE) {
                adding_product.visibility = FrameLayout.INVISIBLE
            } else
                adding_product.visibility = FrameLayout.VISIBLE

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        progressView!!.text = "$p1 мл."
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

}