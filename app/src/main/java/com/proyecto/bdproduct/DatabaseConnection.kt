package com.proyecto.bdproduct
import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DatabaseConnection {
    private const val ip = "" // pones tu ip
    private const val port = "1433"
    private const val database = "BDPRODUCT"
    private const val username = ""
    private const val password = ""

    fun getConnection(): Connection? {
        var connection: Connection? = null

        try {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

            val url = "jdbc:jtds:sqlserver://$ip:$port/$database"
            connection = DriverManager.getConnection(url, username, password)

        } catch (e: SQLException) {
            Log.e("DatabaseConnection", "Error al conectar con la base de datos: ${e.message}")
        }

        return connection
    }
}

