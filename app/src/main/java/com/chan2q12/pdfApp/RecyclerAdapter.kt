package com.chan2q12.pdfApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val langtextList: List<LangDetails>) :
    RecyclerView.Adapter<RecyclerAdapter.LangViewHolder>() {
    class LangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val langname: TextView = view.findViewById(R.id.lang_name_recy)
        val langtext: TextView = view.findViewById(R.id.lang_text_recy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_lang, parent, false)
        return LangViewHolder(view)
    }

    override fun onBindViewHolder(holder: LangViewHolder, position: Int) {
        holder.langname.text = langtextList[position].langName
        holder.langtext.text = langtextList[position].langText
    }

    override fun getItemCount(): Int {
        return langtextList.size
    }
}