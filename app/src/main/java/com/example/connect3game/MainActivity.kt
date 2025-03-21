package com.example.connect3game

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.ui.semantics.text
import androidx.constraintlayout.widget.ConstraintLayout
//import androidx.glance.visibility

class MainActivity : AppCompatActivity() {
    private lateinit var gameGrid: ImageView
    private lateinit var startOverButton: Button
    private lateinit var winnerTextView: TextView

    private var currentPlayer = 1 // 1 for red, 2 for blue
    private var gameBoard = Array(3) { IntArray(3) { 0 } } // 0 for empty, 1 for red, 2 for blue
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameGrid = findViewById(R.id.gameGrid)
        startOverButton = findViewById(R.id.StartOver_Id)
        winnerTextView = findViewById(R.id.textView2)

        gameGrid.setImageResource(R.drawable.grid)

        gameGrid.setOnClickListener {
            if (!gameOver) {
                val column = getColumnFromClick(it.x)
                dropPiece(column)
            }
        }

        startOverButton.setOnClickListener {
            resetGame()
        }
    }

    private fun getColumnFromClick(x: Float): Int {
        // Calculate the column based on the click position
        val gridWidth = gameGrid.width
        val columnWidth = gridWidth / 3
        return (x / columnWidth).toInt()
    }

    private fun dropPiece(column: Int) {
        // Find the next available row in the selected column
        var row = -1
        for (i in 2 downTo 0) {
            if (gameBoard[i][column] == 0) {
                row = i
                break
            }
        }

        if (row != -1) {
            gameBoard[row][column] = currentPlayer
            updateUI(row, column)
            if (checkWin(row, column)) {
                gameOver = true
                winnerTextView.text = "Player ${if (currentPlayer == 1) "Red" else "Blue"} Wins!"
                winnerTextView.visibility = View.VISIBLE
            } else if (checkDraw()) {
                gameOver = true
                winnerTextView.text = "It's a Draw!"
                winnerTextView.visibility = View.VISIBLE
            } else {
                switchPlayer()
            }
        }
    }

    private fun updateUI(row: Int, column: Int) {
        val newPiece = ImageView(this)
        newPiece.setImageResource(if (currentPlayer == 1) R.drawable.redcircle else R.drawable.bluecircle)

        val gridWidth = gameGrid.width
        val gridHeight = gameGrid.height
        val columnWidth = gridWidth / 3
        val rowHeight = gridHeight / 3

        val x = column * columnWidth + columnWidth / 2 - 50 // 50 = half of 100dp
        val y = row * rowHeight + rowHeight / 2 - 50

        val layoutParams = ConstraintLayout.LayoutParams(100, 100)
        newPiece.layoutParams = layoutParams

        // Set position
        newPiece.x = gameGrid.x + x
        newPiece.y = gameGrid.y + y

        val rootLayout = findViewById<ConstraintLayout>(R.id.main)
        rootLayout.addView(newPiece)
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == 1) 2 else 1
    }

    private fun checkWin(row: Int, column: Int): Boolean {
        // Check for a win in all directions
        return checkHorizontal(row) || checkVertical(column) || checkDiagonal(row, column)
    }

    private fun checkHorizontal(row: Int): Boolean {
        var count = 0
        for (i in 0 until 3) {
            if (gameBoard[row][i] == currentPlayer) {
                count++
                if (count == 3) return true
            } else {
                count = 0
            }
        }
        return false
    }

    private fun checkVertical(column: Int): Boolean {
        var count = 0
        for (i in 0 until 3) {
            if (gameBoard[i][column] == currentPlayer) {
                count++
                if (count == 3) return true
            } else {
                count = 0
            }
        }
        return false
    }

    private fun checkDiagonal(row: Int, column: Int): Boolean {
        return checkDiagonalUp(row, column) || checkDiagonalDown(row, column)
    }

    private fun checkDiagonalUp(row: Int, column: Int): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r > 0 && c > 0) {
            r--
            c--
        }
        while (r < 3 && c < 3) {
            if (gameBoard[r][c] == currentPlayer) {
                count++
                if (count == 3) return true
            } else {
                count = 0
            }
            r++
            c++
        }
        return false
    }

    private fun checkDiagonalDown(row: Int, column: Int): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r < 2 && c > 0) {
            r++
            c--
        }
        while (r >= 0 && c < 3) {
            if (gameBoard[r][c] == currentPlayer) {
                count++
                if (count == 3) return true
            } else {
                count = 0
            }
            r--
            c++
        }
        return false
    }

    private fun checkDraw(): Boolean {
        for (row in gameBoard) {
            for (cell in row) {
                if (cell == 0) return false
            }
        }
        return true
    }

    private fun resetGame() {
        // Reset the game state
        currentPlayer = 1
        gameBoard = Array(3) { IntArray(3) { 0 } }
        gameOver = false
        winnerTextView.visibility = View.INVISIBLE

        // Remove all pieces from the board
        val rootLayout = findViewById<ConstraintLayout>(R.id.main)
        for (i in rootLayout.childCount - 1 downTo 0) {
            val child = rootLayout.getChildAt(i)
            if (child is ImageView && child.id != R.id.gameGrid) {
                rootLayout.removeView(child)
            }
        }
    }
}