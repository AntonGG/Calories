package myprogi.ml.calcCalories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_profile.*
import myprogi.ml.calcCalories.database.DatabaseHandler

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

        calcCalories()
    }

    fun calcCalories() {
        DatabaseHandler(this, null, null, dbVersion).findAll().last().let {
            val bmr = if (it.sex == "Мужчина") {
                88.36 + (13.4 * it.weight) + (4.8 * it.growth) - (5.7 * it.years)
            } else {
                447.6 + (9.2 * it.weight) + (3.1 * it.growth) - (4.3 * it.years)
            }

            val activLevel = when (it.activity) {
                "low" -> 1.2
                "light" -> 1.375
                "middle" -> 1.55
                "hard" -> 1.725
                else -> 0.0
            }

            val normCalories = bmr * activLevel
            val normProteins = normCalories * 0.15 / 4
            val normFats = normCalories * 0.30 / 9
            val normCarbs = normCalories * 0.55 / 4

            caloriesNorma.text = normCalories.toInt().toString()
            proteinsNorma.text = normProteins.toInt().toString()
            fatsNorma.text = normFats.toInt().toString()
            carbsNorma.text = normCarbs.toInt().toString()
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