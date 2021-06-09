package top.wzmyyj.ygocard.card

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import top.wzmyyj.ygocard.R

/**
 * Created on 2021/05/18.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvDesc = findViewById<TextView>(R.id.tv_desc)

    }

}