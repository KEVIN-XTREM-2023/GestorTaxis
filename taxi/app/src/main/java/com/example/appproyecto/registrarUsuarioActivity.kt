package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class registrarUsuarioActivity : AppCompatActivity() {

    lateinit var ingresoCedula : EditText;
    lateinit var ingresoNombre : EditText;
    lateinit var ingresoApellido : EditText;
    lateinit var ingresoEmail : EditText;
    lateinit var ingresoContrasena : EditText;
    lateinit var ingresoCooperativa : EditText;
    lateinit var textCedula : TextView;
    lateinit var textNombre : TextView;
    lateinit var textApellido : TextView;
    lateinit var textEmail : TextView;
    lateinit var textCooperativa : TextView;
    lateinit var textContrasena : TextView;
    lateinit var boton : Button;

    var URL: String="http://192.168.101.5:8082/taxista/usuario/registrar.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        this.boton = findViewById(R.id.btnEnviarRegistroUsuario)
        this.ingresoCedula = findViewById(R.id.editCedulaRegistro)
        this.ingresoNombre = findViewById(R.id.editNombresRegistro)
        this.ingresoApellido = findViewById(R.id.editApellidoRegistro)
        this.ingresoContrasena = findViewById(R.id.editContrasenaRegistro)
        this.ingresoCooperativa = findViewById(R.id.editCooperativaRegistro)
        this.ingresoEmail = findViewById(R.id.editEmailRegistro)
        this.textCedula = findViewById(R.id.mensajeCedulaRe)
        this.textNombre = findViewById(R.id.mensajeUsuarioRe)
        this.textApellido = findViewById(R.id.mensajeApellidoRe)
        this.textCooperativa = findViewById(R.id.mensajeCooperativaRe)
        this.textEmail   = findViewById(R.id.mensajeEmailRe)
        this.textContrasena = findViewById(R.id.mensajeContraseniaRe)

        this.textNombre.visibility = View.INVISIBLE
        this.textApellido.visibility = View.INVISIBLE
        this.textCedula.visibility = View.INVISIBLE
        this.textCooperativa.visibility = View.INVISIBLE
        this.textEmail.visibility = View.INVISIBLE
        this.textContrasena.visibility = View.INVISIBLE

        registrar()
    }

    fun registrar(){

        this.boton.setOnClickListener {

            enviarValores()
        }
    }

    private fun enviarValores(){
        var cedula = this.ingresoCedula.text.toString().trim()
        var nombre = this.ingresoNombre.text.toString().trim()
        var apellido = this.ingresoApellido.text.toString().trim()
        var email = this.ingresoEmail.text.toString().trim()
        var cooperativa = this.ingresoCooperativa.text.toString().trim()
        var contrase = this.ingresoContrasena.text.toString().trim()
        if(this.controlDeErrores()){
            //siguiente()
            println("Entro1"+email)
            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->
                println("mensaje 1  "+response.trim())
                if(!response.trim().equals("No hay datos")){

                    Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                    //convertirJson(response.trim())
                    val intent = Intent(this,loginActivity::class.java)

                    startActivity(intent)
                }else{

                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("ced",cedula)
                    params.put("nom",nombre)
                    params.put("ape",apellido)
                    params.put("coop",cooperativa)
                    params.put("email",email)
                    params.put("passw",contrase)
                    return params
                }
            }
            requestQueue.add(stringRequest)
        }
        println("Entro3")
    }

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray(jsonString)

        //val list = ArrayList<>
        println(jsonArray)
        var x=0;
        //var globalDatos=datosSesion()
        var nombre:String="";
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            //nombre=JSONObject.getString("nombre").toString()
            x++
        }

        val intent = Intent(this,loginActivity::class.java)

        startActivity(intent)
    }

    fun  controlDeErrores():Boolean{

        if(this.ingresoCedula.text.toString().length<=0){
            Toast.makeText(applicationContext, "Cédula es Obligatorio", Toast.LENGTH_LONG).show()
            this.textCedula.visibility = View.VISIBLE
            return false;
        }else{
            this.textCedula.visibility = View.INVISIBLE
        }
        if(this.ingresoNombre.text.toString().length<=0){
            Toast.makeText(applicationContext, "Nombre es Obligatorio", Toast.LENGTH_LONG).show()
            this.textNombre.visibility = View.VISIBLE
            return false;
        }else{
            this.textNombre.visibility = View.INVISIBLE
        }
        if(this.ingresoApellido.text.toString().length<=0){
            Toast.makeText(applicationContext, "Apellido es Obligatorio", Toast.LENGTH_LONG).show()
            this.textApellido.visibility = View.VISIBLE
            return false;
        }else{
            this.textApellido.visibility = View.INVISIBLE
        }
        if(this.ingresoEmail.text.toString().length<=0){
            Toast.makeText(applicationContext, "Email es Obligatorio", Toast.LENGTH_LONG).show()
            this.textEmail.visibility = View.VISIBLE
            return false;
        }else{
            this.textEmail.visibility = View.INVISIBLE
        }
        if(this.ingresoCooperativa.text.toString().length<=0){
            Toast.makeText(applicationContext, "Cooperativa es Obligatoria", Toast.LENGTH_LONG).show()
            this.textCooperativa.visibility = View.VISIBLE
            return false;
        }else{
            this.textCooperativa.visibility = View.INVISIBLE
        }

        if(this.ingresoContrasena.text.toString().length<=0){
            Toast.makeText(applicationContext, "Contraseña es Obligatorio", Toast.LENGTH_LONG).show()
            this.textContrasena.visibility = View.VISIBLE
            return false;
        }else{
            this.textContrasena.visibility = View.INVISIBLE
        }
        return true
    }
}