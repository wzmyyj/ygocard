package top.wzmyyj.ygocard

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import top.wzmyyj.ygocard.common.data.CardInfo

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvDesc = findViewById<TextView>(R.id.tv_desc)
        tvDesc.textScaleX = 0.7f
        tvDesc.typeface=cnTf
        tvDesc.text = CardInfo().monsterDesc
        assets
    }

    private val cnTf by lazy { Typeface.createFromAsset(resources.assets, "fonts/cn.ttf") }
}