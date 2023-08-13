package com.example.trabalhopratico.adapter.listener

import com.example.trabalhopratico.model.Cursos

class CursoOnClickListener(val clickListener: (curso: Cursos) -> Unit) {
    fun onClick(curso: Cursos) = clickListener
}