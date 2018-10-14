package myprogi.ml.calcCalories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : BaseActivity(3),
        SeekBar.OnSeekBarChangeListener {
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

        myRation.text = dateNow
        profile_btn.setOnClickListener {
            startActivity(Intent(this, MyDataActivity::class.java))
        }
    }

    //загрузить данные за сегодня
/*    fun loadTodayProfile(){
        val dbHandler = DatabaseHandler(this, null, null, 1)

        val date = DateTimeFormatter,
        val formatter = DateTimeFormatter.ofPattern("yyyy MM dd")
        val text = date.format(formatter)
        val parsedDate = LocalDate.parse(text, formatter)

        val profile = dbHandler.find()
    }*/

    fun drink() {
        TODO()
        // R.string.waterDay += seekBarView!!.progress
    }

    //показать диалог добавления воды
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