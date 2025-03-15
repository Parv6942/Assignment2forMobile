package com.example.connect3game  // Change this to your package name

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = RelativeLayout(this).apply {
            setBackgroundColor(Color.WHITE)
        }

        val countdownText = TextView(this).apply {
            textSize = 64f
            setTextColor(Color.BLACK)
            text = "3"  // Static "3"
        }

        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.CENTER_IN_PARENT)
        }

        layout.addView(countdownText, params)
        setContentView(layout)

        Handler().postDelayed({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()  // Close SplashActivity
        }, 3000)
    }
}
