package top.wzmyyj.ygocard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvDesc = findViewById<TextView>(R.id.tv_desc)
        tvDesc.textScaleX = 0.7f
        assets
    }
}