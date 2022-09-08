package com.chan2q12.pdfApp

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PDFConverter {

    private fun createBitmapFromView(
        context: Context,
        view: View,
        pdfDetails: PdfDetails,
        adapter: RecyclerAdapter,
        activity: Activity
    ): Bitmap {
         val recyclerView = view.findViewById<RecyclerView>(R.id.pdf_lang)
          recyclerView.adapter = adapter
        return createBitmap(context, view, activity)
    }

    private fun createBitmap(
        context: Context,
        view: View,
        activity: Activity,
    ): Bitmap {
        val displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display?.getRealMetrics(displayMetrics)
            displayMetrics.densityDpi
        } else {
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.widthPixels, View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.makeMeasureSpec(
                displayMetrics.heightPixels, View.MeasureSpec.EXACTLY
            )
        )
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        val bitmap = Bitmap.createBitmap(
            view.measuredWidth,
            view.measuredHeight, Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return Bitmap.createScaledBitmap(bitmap, 720, 1120, true)
    }

    private fun convertBitmapToPdf(bitmap: Bitmap, context: Context):File {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(bitmap.width, bitmap.height, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        page.canvas.drawBitmap(bitmap, 0F, 0F, null)
        pdfDocument.finishPage(page)

        val filePath = File(context.applicationContext.filesDir, "/nouu.pdf")
        try {
            pdfDocument.writeTo(FileOutputStream(filePath))
            Toast.makeText(context.applicationContext,"PDF Generated Successfully",Toast.LENGTH_SHORT).show()
        }catch(e:IOException){
            Toast.makeText(context.applicationContext,"PDF Failed To Generate Successfully",Toast.LENGTH_SHORT).show()
        }
        pdfDocument.close()
        return filePath
    }

    fun createPdf(
        context: Context,
        pdfDetails: PdfDetails,
        activity: Activity
    ) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_pdf_page, null)

        val adapter = RecyclerAdapter(pdfDetails.langDetailsList)
        val bitmap = createBitmapFromView(context, view, pdfDetails, adapter, activity)
        convertBitmapToPdf(bitmap, activity)
    }



}