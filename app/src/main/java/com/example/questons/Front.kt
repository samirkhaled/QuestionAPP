package com.example.questons

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.front.*
import java.lang.Exception

class Front : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.front)

        btn_questions.setOnClickListener {
            var i = Intent(this, Second::class.java)
            startActivity(i)


        }
        btn_rate.setOnClickListener {
          try {
              var uri= Uri.parse("market://details?id${packageName}")
              var market=Intent(Intent.ACTION_VIEW)
              market.data=uri
              startActivity(market)
          }catch (ex:Exception)
          {
              var uri= Uri.parse("https://play.google.com/store/apps/details?id${packageName}")
              var market=Intent(Intent.ACTION_VIEW)
              market.data=uri
              startActivity(market)
          }
        }
        }

    }



