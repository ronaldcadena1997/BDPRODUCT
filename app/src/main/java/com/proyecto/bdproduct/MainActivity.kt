package com.proyecto.bdproduct

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import android.util.Log
import java.sql.PreparedStatement

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editTextCodProd = findViewById<EditText>(R.id.editTextCodProd)
        val editTextProducto = findViewById<EditText>(R.id.editTextProducto)
        val editTextPrecio = findViewById<EditText>(R.id.editTextPrecio)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)

        buttonGuardar.setOnClickListener {
            val codProd = editTextCodProd.text.toString()
            val producto = editTextProducto.text.toString()
            val precio = editTextPrecio.text.toString().toDouble() // Asegúrate de manejar la conversión según tus necesidades

            Thread {
                val connection: Connection? = DatabaseConnection.getConnection()
                if (connection != null) {
                    try {

                        val query = "INSERT INTO BDPRODUCT (CODPROD, PRODUCTO, PRECIO) VALUES (?, ?, ?)"
                        val preparedStatement: PreparedStatement = connection.prepareStatement(query)
                        preparedStatement.setString(1, codProd)
                        preparedStatement.setString(2, producto)
                        preparedStatement.setDouble(3, precio)
                        val rowsAffected = preparedStatement.executeUpdate()

                        if (rowsAffected > 0) {
                            Log.d("MainActivity", "Inserción exitosa. Filas afectadas: $rowsAffected")
                        } else {
                            Log.d("MainActivity", "No se pudo insertar el registro.")
                        }

                    } catch (e: SQLException) {
                        Log.e("MainActivity", "Error al ejecutar la inserción: ${e.message}")
                    } finally {
                        connection.close()
                    }
                }
            }.start()
        }

    }
}