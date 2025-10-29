package com.so.quizsistemaoperacional

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val imgLogo = findViewById<ImageView>(R.id.imgLogo)
        val tvAppName = findViewById<TextView>(R.id.tvAppName)
        val tvSubtitle = findViewById<TextView>(R.id.tvSubtitle)

        // ✅ Garante que os elementos comecem invisíveis, mas visíveis para animação
        imgLogo.alpha = 0f
        tvAppName.alpha = 0f
        tvSubtitle.alpha = 0f

        // 🔹 Efeitos de animação suave
        val fadeInLogo = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }

        val fadeInText = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }

        // 🔹 Executa animações com atraso para efeito sequencial
        Handler(Looper.getMainLooper()).postDelayed({
            imgLogo.startAnimation(fadeInLogo)
            imgLogo.alpha = 1f // garante visibilidade após animação
        }, 200)

        Handler(Looper.getMainLooper()).postDelayed({
            tvAppName.startAnimation(fadeInText)
            tvAppName.alpha = 1f
        }, 700)

        Handler(Looper.getMainLooper()).postDelayed({
            tvSubtitle.startAnimation(fadeInText)
            tvSubtitle.alpha = 1f
        }, 1200)

        // 🔹 Depois de 3 segundos, abre a tela principal
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}
