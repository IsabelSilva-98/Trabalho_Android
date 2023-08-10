package com.example.trabalhopratico.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhopratico.R
import com.example.trabalhopratico.adapter.listener.CursoOnClickListener
import com.example.trabalhopratico.adapter.viewholder.CursoViewHolder
import com.example.trabalhopratico.model.Cursos

class CursoListAdapter (
    private val cursoList: List<Cursos>,
    private val cursoOnClickListener: CursoOnClickListener): RecyclerView.Adapter<CursoViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_curso, parent, false)
        return CursoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cursoList.size
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursoList[position]
        holder.textNome.text = curso.nome
        if(curso.imagemId > 0) {
            holder.imagem.setImageResource(curso.imagemId)
        }
        else{
            holder.imagem.setImageResource(R.drawable.adicionar)
        }
        holder.itemView.setOnClickListener {
            cursoOnClickListener.clickListener(curso)
        }
    }
}