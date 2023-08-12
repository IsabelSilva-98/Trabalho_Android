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
        holder.textNome1.text = curso.nome
        holder.textNome2.text = curso.local
        holder.textNome3.text = curso.dataArranque
        if(curso.imagemId == 1) {
            holder.imagem.setImageResource(R.drawable.curso1)
        }else if(curso.imagemId == 2){
            holder.imagem.setImageResource(R.drawable.curso7)
        } else if(curso.imagemId == 3){
            holder.imagem.setImageResource(R.drawable.curso5)
        }else if(curso.imagemId == 4){
            holder.imagem.setImageResource(R.drawable.curso4)
        }else if(curso.imagemId == 5){
            holder.imagem.setImageResource(R.drawable.curso3)
        }else if(curso.imagemId == 6){
            holder.imagem.setImageResource(R.drawable.curso2)
        }else if(curso.imagemId == 7){
            holder.imagem.setImageResource(R.drawable.curso6)
        } else{
            holder.imagem.setImageResource(curso.imagemId)
        }
        holder.itemView.setOnClickListener {
            cursoOnClickListener.clickListener(curso)
        }
    }
}