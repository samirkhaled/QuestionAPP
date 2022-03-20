package com.example.questons

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.second.*
import java.lang.Exception
import java.util.*

class Second : AppCompatActivity(), View.OnClickListener {
    var questionsArray:Array<String>?=null
    var answersArray:Array<String>?=null
    var counter:Int?=null
    var tToS:TextToSpeech?=null
    var result:Int?=null
    var con:Context?=null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.second)
            counter=0
            questionsArray=resources.getStringArray(R.array.questions)
            answersArray=resources.getStringArray(R.array.answers)
            second_tv_yy.setText((questionsArray!!.size).toString())
            second_tv_question.text="${questionsArray!![counter!!]}"
            second_tv_xx.text="${(counter!!+1).toString()}/"
           // Toast.makeText(this,(questionsArray!!.size).toString(),Toast.LENGTH_SHORT).show()
            second_btn_answer.setOnClickListener(this)
            second_btn_nex.setOnClickListener(this)
            second_btn_back.setOnClickListener(this)
            second_btn_voice.setOnClickListener(this)
            con=this

            //textToSpeech
            tToS= TextToSpeech(this,object:TextToSpeech.OnInitListener{
                override fun onInit(p0: Int) {
                 result= tToS!!.setLanguage(Locale.US)
                }


            })

            second_btn_answer.setOnLongClickListener(object:View.OnLongClickListener{
                override fun onLongClick(p0: View?): Boolean {
                    if (result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
                    {
                        Toast.makeText(con,"error",Toast.LENGTH_SHORT).show()
                    }else
                    {
                        if (second_tv_answer!=null)
                        {
                            tToS!!.speak(second_tv_answer!!.text,TextToSpeech.QUEUE_FLUSH,null,null)

                        }

                    }
                    return true
                }

            })

    }

    override fun onClick(p0: View?) {
        when(p0?.id)
        {
            R.id.second_btn_answer ->
            {
                second_tv_answer.text="${answersArray!![counter!!]}"
            }
            R.id.second_btn_voice ->
            {
                if (result==TextToSpeech.LANG_NOT_SUPPORTED||result==TextToSpeech.LANG_MISSING_DATA)
                {
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                }else
                {
                    tToS!!.speak(second_tv_question.text,TextToSpeech.QUEUE_FLUSH,null,null)
                }

            }

            R.id.second_btn_nex ->
            {
                try {
                    counter=counter!!+1
                    second_tv_question.setText(questionsArray!![counter!!])
                    second_tv_xx.text="${(counter!!+1).toString()}/"
                    second_tv_answer.text="press the answer button to show the answer"

                }catch (ex:Exception)
                {
                    counter=0
                    second_tv_question.text="${questionsArray!![counter!!]}"
                    second_tv_xx.text="${(counter!!+1).toString()}/"
                }
            }

            R.id.second_btn_back ->
            {
                 try {
                     counter=counter!!-1
                     second_tv_question.setText(questionsArray!![counter!!])
                     second_tv_xx.text="${(counter!!+1).toString()}/"
                 }catch (ex:Exception)
                 {
                     counter=questionsArray!!.size-1
                     second_tv_question.setText(questionsArray!![counter!!])
                     second_tv_xx.text="${(counter!!+1).toString()}/"
                 }





            }
        }
    }
}