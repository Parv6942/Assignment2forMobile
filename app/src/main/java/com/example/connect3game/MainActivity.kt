package com.example.connect3game  // Change to your package name

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var redCircle: ImageView
    private lateinit var blueCircle: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // The name box variable is Name_Id, and the start over variable name is StartOver_Id, look through the layout files to really understand it and try starting the app to see how it works, delete this once you do

        val gameGrid = findViewById<ImageView>(R.id.gameGrid)
        redCircle = findViewById(R.id.redCircle)
        blueCircle = findViewById(R.id.blueCircle)

        gameGrid.setImageResource(R.drawable.grid)
    }
}
