package com.chan2q12.pdfApp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import java.io.File
import java.io.IOException

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var btnCreatePDF: Button
    private lateinit var pdfView :PDFView
    private lateinit var btnViewPDF: Button

    private lateinit var eng: TextView
    private lateinit var eng_text: TextView
    private lateinit var hindi: TextView
    private lateinit var hin_text: TextView
    private lateinit var telegu: TextView
    private lateinit var telegu_text: TextView
    private lateinit var bangla: TextView
    private lateinit var bangla_text: TextView
    private lateinit var punjabi: TextView
    private lateinit var punjabi_text: TextView






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnCreatePDF = findViewById(R.id.button)
        btnViewPDF = findViewById(R.id.button1)
        pdfView=findViewById(R.id.pdfView)

        eng = findViewById(R.id.eng)
        eng_text = findViewById(R.id.eng_text)
        hindi = findViewById(R.id.hindi)
        hin_text = findViewById(R.id.hindi_txt)
        telegu = findViewById(R.id.telegu)
        telegu_text = findViewById(R.id.telegu_text)
        bangla = findViewById(R.id.bengali)
        bangla_text = findViewById(R.id.bengali_text)
        punjabi = findViewById(R.id.punjabi)
        punjabi_text = findViewById(R.id.punjabi_text)



        val Hin = hindi.getText().toString()
        val Telegu = telegu.getText().toString()
        val Bangla = bangla.getText().toString()
        val Punjabi = punjabi.getText().toString()
        val Eng = eng.text.toString()

        val Hintext = hin_text.getText().toString()
        val Telegutext = telegu_text.getText().toString()
        val Banglatext = bangla_text.getText().toString()
        val Punjabitext = punjabi_text.getText().toString()
        val Engtext = eng_text.text.toString()






        btnCreatePDF.setOnClickListener {

            btnCreatePDF.visibility = View.GONE
            btnViewPDF.visibility=View.VISIBLE
            val langDetailsLists = listOf(
                LangDetails(Eng, Engtext),
                LangDetails(Hin,Hintext),
                LangDetails(Telegu, Telegutext),
                LangDetails(Bangla, Banglatext),
                LangDetails(Punjabi, Punjabitext)
            )

            langDetailsLists.forEach {
            }
            val pdfDetails = PdfDetails(langDetailsLists)
            val pdfConverter = PDFConverter()
            pdfConverter.createPdf(this, pdfDetails, this)


        }

        btnViewPDF.setOnClickListener {
            val file= File(applicationContext.filesDir,"/nouu.pdf")
            try{
            pdfView.fromFile(file).load()
                Toast.makeText(applicationContext,"PDF View Generated Successfully",Toast.LENGTH_SHORT).show()


            }catch (e:IOException){
                Toast.makeText(applicationContext,"PDF View Failed to Generate Successfully", Toast.LENGTH_SHORT).show()


            }        }
    }
}