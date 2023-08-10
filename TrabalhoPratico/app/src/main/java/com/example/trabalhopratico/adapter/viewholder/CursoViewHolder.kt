package com.example.trabalhopratico.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhopratico.R

class CursoViewHolder (view: View): RecyclerView.ViewHolder(view) {
    val imagem: ImageView = view.findViewById(R.id.image_curso)
    val textNome: TextView = view.findViewById(R.id.text_curso)
}