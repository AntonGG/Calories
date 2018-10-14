package myprogi.ml.calcCalories

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.bottom_navigation_view.*


abstract class BaseActivity(val navNumber: Int) : AppCompatActivity() {
    private val TAG = "BaseActivity"

    fun setupBottomNavigation() {
        navigation_view_ex.setOnNavigationItemSelectedListener {
            val nextActivity = when (it.itemId) {
                R.id.nav_item_favorite -> FavoriteActivity::class.java
                R.id.nav_item_eating -> EatingActivity::class.java
                R.id.nav_item_profile -> ProfileActivity::class.java
                R.id.nav_item_search -> SearchActivity::class.java
                else -> {
                    Log.e(TAG, "unknow nav item clicked $it")
                    null
                }
            }
            if (nextActivity != null) {
                startActivity(Intent(this, nextActivity).also {
                    it.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                })
                overridePendingTransition(0,0)
                true
            } else
                false
        }
        navigation_view_ex.menu.getItem(navNumber).isChecked = true
    }
}