package com.example.trabalhopratico.model

data class Cursos(
    val id: Int = 0,
    val nome: String = "",
    val local: String = "",
    val dataArranque: String = "",
    val dataFim: String = "",
    val preco: Double = 0.0,
    val duracao: Int = 0,
    val edicao: Int = 0
)
