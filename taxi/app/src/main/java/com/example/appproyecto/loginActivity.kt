package com.example.appproyecto


import android.annotation.SuppressLint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


import org.w3c.dom.Text

class loginActivity : AppCompatActivity() {


    lateinit var ingresoUsuario : EditText;
    lateinit var ingresoContrasena : EditText;
    lateinit var textusuario : TextView;
    lateinit var textContrasena : TextView;
    lateinit var boton : Button;
    lateinit var botonRegistrar : Button;
    var URL: String="http://192.168.101.5:8082/taxista/loginTaxista.php";

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
            this.boton = findViewById(R.id.btnIngresar)

            this.ingresoUsuario = findViewById(R.id.edittxtUsuario)
            this.ingresoContrasena = findViewById(R.id.edittxtcontrasenia)
            this.textusuario = findViewById(R.id.mensajeUsuario)
            this.textContrasena = findViewById(R.id.mensajeContrasenia)

            this.textusuario.visibility = View.INVISIBLE
            this.textContrasena.visibility = View.INVISIBLE


        //iniciarSesion()
        siguiente()

    }



    fun siguiente(){

        this.boton.setOnClickListener {
            //val intent = Intent(this,opcionesActivity::class.java)
           // startActivity(intent)
            iniciarSesion()
        }
    }

    private fun iniciarSesion(){
        var email = this.ingresoUsuario.text.toString().trim()
        var contrase = this.ingresoContrasena.text.toString().trim()
        if(this.controlDeErrores()){
            //siguiente()

            var requestQueue:RequestQueue=Volley.newRequestQueue(this)
            var stringRequest:StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->
                println("Entro1--> "+response.trim())
                if(response.trim().length>0){

                    Toast.makeText(this,"Correcto",Toast.LENGTH_LONG).show()
                    convertirJson(response.trim())
                }else{

                    Toast.makeText(this,"Los Datos Ingresados son Incorrectos",Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("email_tax",email)
                    params.put("passw_tax",contrase)
                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray("["+jsonString+"]")

        //val list = ArrayList<>

        var x=0;
        //var globalDatos=datosSesion()
        var nombre:String="";
        var id:String="0";
        var jornada:String="0";
        var estado:String="0";
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)
            println("cedula--->"+JSONObject.getString("id_tax").toString())
            nombre=JSONObject.getString("nom_tax").toString()
            id=JSONObject.getString("id_tax").toString()
            jornada=JSONObject.getString("jor_tax").toString()
            estado=JSONObject.getString("est_tax").toString()
            x++
        }
        globales.id=id;
        globales.nombre=nombre;
        globales.jornada=jornada;
        globales.estado=estado;
        val intent = Intent(this,opcionesActivity::class.java)
        startActivity(intent)
    }

    fun  controlDeErrores():Boolean{


        if(this.ingresoUsuario.text.toString().length<=0){
            Toast.makeText(applicationContext, "Usuario es Obligatorio",Toast.LENGTH_LONG).show()
            this.textusuario.visibility = View.VISIBLE
            return false;
        }else{
            this.textusuario.visibility = View.INVISIBLE
        }
        if(this.ingresoContrasena.text.toString().length<=0){
            Toast.makeText(applicationContext, "Co es Obligatorio",Toast.LENGTH_LONG).show()
            this.textContrasena.visibility = View.VISIBLE
            return false;
        }else{
            this.textContrasena.visibility = View.INVISIBLE
        }
        return true
    }

    fun consultarDatos(){





    }

}