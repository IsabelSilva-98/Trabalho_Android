package com.example.trabalhopratico.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.trabalhopratico.model.Cursos
import com.example.trabalhopratico.model.UserModel

class DBHelper(context: Context) : SQLiteOpenHelper(context, "database.db", null, 1) {
    val sql = arrayOf(
        "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, password TEXT)",
        "INSERT INTO users (username, password) VALUES ('admin1', 'password')",
        "INSERT INTO users (username, password) VALUES ('admin2', 'pass')",
        "INSERT INTO users (username, password) VALUES ('admin3', 'word')",
        "CREATE TABLE curso (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, local TEXT, dataArranque TEXT, dataFim TEXT, preco DOUBLE, duracao INT, edicao INT, imagemId INT)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Software Developer', 'Braga', '29/03/2023', '06/12/2023', 0, 1000, 1, 1)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Cibersegurança', 'Leça da Palmeira', '12/09/2023', '21/12/2023', 0, 200, 3, 2)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Excel Iniciação', 'Porto', '18/09/2023', '02/10/2023', 165.00, 15, 6, 3)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Análise de Dados', 'Aveiro', '20/09/2023', '23/01/2024', 0, 300, 2, 4)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Design UX/UI', 'Porto', '21/09/2023', '20/11/2023', 0, 75, 1, 5)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('PowerBI', 'Viseu', '02/10/2023', '30/11/2023', 0, 75, 5, 6)",
        "INSERT INTO curso (nome, local, dataArranque, dataFim, preco, duracao, edicao, imagemId) VALUES ('Coordenador da Formação', 'Leça da Palmeira', '18/09/2023', '30/10/2023', 160.00, 50, 1, 7)"
    )

    override fun onCreate(db: SQLiteDatabase) {
        sql.forEach {
            db.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertUser(username:String, password:String): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.insert("users", null, contentValues)
        db.close()
        return res
    }

    //CRUD USERS
    fun updateUser(id:Int, username:String, password:String): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.update("users", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteUser(id:Int): Int{
        val db = this.writableDatabase
        val res = db.delete("users", "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun selectUser(username:String, password:String): UserModel {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?", arrayOf(username, password)
        )

        var userModel = UserModel()

        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val passwordIndex = c.getColumnIndex("password")

            userModel = UserModel(
                id = c.getInt(idIndex),
                username = c.getString(usernameIndex),
                password = c.getString(passwordIndex)

            )
        }
        db.close()
        return userModel
    }

    fun login(username:String, password:String): Boolean {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?", arrayOf(username, password)
        )

        var userModel = UserModel()

        if (c.count == 1) {
            db.close()
            return true
        }
        else {
            db.close()
            return false
        }
    }






    //CRUD CURSOS

    fun insertCurso(nome:String, locaL:String, dataArranque:String, dataFim:String, preco:Double, duracao:Int, edicao:Int, imagemId: Int): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("locaL", locaL)
        contentValues.put("dataArranque", dataArranque)
        contentValues.put("dataFim", dataFim)
        contentValues.put("preco", preco)
        contentValues.put("duracao", duracao)
        contentValues.put("edicao", edicao)
        contentValues.put("imagemId", imagemId)
        val res = db.insert("curso", null, contentValues)
        db.close()
        return res
    }


    fun updateCurso(id:Int, nome:String, local:String, dataArranque:String, dataFim:String, preco:Double, duracao:Int, edicao:Int, imagemId:Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("nome", nome)
        contentValues.put("locaL", local)
        contentValues.put("dataArranque", dataArranque)
        contentValues.put("dataFim", dataFim)
        contentValues.put("preco", preco)
        contentValues.put("duracao", duracao)
        contentValues.put("edicao", edicao)
        contentValues.put("imagemId", imagemId)
        val res = db.update("curso", contentValues, "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteCurso(id:Int): Int{
        val db = this.writableDatabase
        val res = db.delete("curso", "id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun selectCurso(id:Int): Cursos {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM curso WHERE id=?",
            arrayOf(id.toString())
        )

        var curso = Cursos()

        if (c.count == 1) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nomeIndex = c.getColumnIndex("nome")
            val localIndex = c.getColumnIndex("local")
            val dataArranqueIndex = c.getColumnIndex("dataArranque")
            val dataFimIndex = c.getColumnIndex("dataFim")
            val precoIndex = c.getColumnIndex("preco")
            val duracaoIndex = c.getColumnIndex("duracao")
            val edicaoIndex = c.getColumnIndex("edicao")
            val imagemIdIndex = c.getColumnIndex("imagemId")

            curso = Cursos(
                id = c.getInt(idIndex),
                nome = c.getString(nomeIndex),
                local = c.getString(localIndex),
                dataArranque = c.getString(dataArranqueIndex),
                dataFim = c.getString(dataFimIndex),
                preco = c.getDouble(precoIndex),
                duracao = c.getInt(duracaoIndex),
                edicao = c.getInt(edicaoIndex),
                imagemId = c.getInt(imagemIdIndex)
            )
        }
        db.close()
        return curso
    }


    fun selectAllCurso(): ArrayList<Cursos> {
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM curso", null
        )
        var listCurso = ArrayList<Cursos>()

        if (c.count > 0) {

            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nomeIndex = c.getColumnIndex("nome")
            val localIndex = c.getColumnIndex("local")
            val dataArranqueIndex = c.getColumnIndex("dataArranque")
            val dataFimIndex = c.getColumnIndex("dataFim")
            val precoIndex = c.getColumnIndex("preco")
            val duracaoIndex = c.getColumnIndex("duracao")
            val edicaoIndex = c.getColumnIndex("edicao")
            val imagemIdIndex = c.getColumnIndex("imagemId")

            do
            {
                val curso = Cursos(
                    id = c.getInt(idIndex),
                    nome = c.getString(nomeIndex),
                    local = c.getString(localIndex),
                    dataArranque = c.getString(dataArranqueIndex),
                    dataFim = c.getString(dataFimIndex),
                    preco = c.getDouble(precoIndex),
                    duracao = c.getInt(duracaoIndex),
                    edicao = c.getInt(edicaoIndex),
                    imagemId = c.getInt(imagemIdIndex)
                )
                listCurso.add(curso)
            }
            while(c.moveToNext())
        }
        db.close()
        return listCurso
    }
}