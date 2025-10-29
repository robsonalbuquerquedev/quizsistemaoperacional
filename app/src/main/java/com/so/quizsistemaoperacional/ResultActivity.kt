package com.so.quizsistemaoperacional

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvMessage = findViewById<TextView>(R.id.tvMessage)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val btnRestart = findViewById<Button>(R.id.btnRestart)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val percent = ((score.toFloat() / total.toFloat()) * 100).toInt()

        tvScore.text = "Você acertou $score de $total!"
        progressBar.progress = percent

        val message = when {
            percent == 100 -> "Perfeito! Você gabaritou! 👑"
            percent >= 80 -> "Excelente! Continue assim! 🚀"
            percent >= 60 -> "Bom desempenho! Revise e avance! 💪"
            else -> "Não desanime! Continue estudando! 📘"
        }

        tvMessage.text = message

        // animação suave da barra de progresso
        Handler(mainLooper).postDelayed({
            progressBar.animate().setDuration(800).withEndAction {
                progressBar.progress = percent
            }.start()
        }, 200)

        btnRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
