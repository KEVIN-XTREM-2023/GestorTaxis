package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class misdatosActivity : AppCompatActivity() {

    var URL: String="http://192.168.101.5:8082/taxista/listarDatosTaxistas.php";
    lateinit var ingresoCedula : EditText;
    lateinit var ingresoNombre : EditText;
    lateinit var ingresoApellido : EditText;
    lateinit var ingresoEmail : EditText;
    lateinit var ingresoCooperativa : EditText;
    lateinit var boton : Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_misdatos)
        this.boton = findViewById(R.id.btnEnviarMisDatosUsuario)
        this.ingresoCedula = findViewById(R.id.editCedulaMisDatos)
        this.ingresoNombre = findViewById(R.id.editNombresMisDatos)
        this.ingresoApellido = findViewById(R.id.editApellidoMisDatos)
        this.ingresoCooperativa = findViewById(R.id.editCooperativaMisDatos)
        this.ingresoEmail = findViewById(R.id.editEmailMisDatos)
        regresar();
        traerDatos();
    }

    fun regresar(){

        this.boton.setOnClickListener {

            val intent = Intent(this,opcionesActivity::class.java)

            startActivity(intent)
        }
    }

    private fun traerDatos(){
        var id = globales.id

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->

            println("Entro1--> "+response.trim())
            if(response.trim().length>0){

                //Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                convertirJson(response.trim())
            }else{

                Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String, String>()
                params.put("id_tax",id.toString())
                return params
            }
        }
        requestQueue.add(stringRequest)

        println("Entro3")
    }

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray("["+jsonString+"]")

        //val list = ArrayList<>

        var x=0;
        //var globalDatos=datosSesion()
        var nombre:String="";
        var apellido:String="";
        var coop:String="";
        var cedula:String="";
        var email:String="";
        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            nombre=JSONObject.getString("nom_tax").toString()
            apellido=JSONObject.getString("ape_tax").toString()
            cedula=JSONObject.getString("ced_tax").toString()
            coop=JSONObject.getString("ape_tax").toString()
            email=JSONObject.getString("email_tax").toString()

            x++
        }
        this.ingresoCedula.setText(cedula)
        this.ingresoNombre.setText(nombre)
        this.ingresoApellido.setText(apellido)
        this.ingresoCooperativa.setText(coop)
        this.ingresoEmail.setText(email)

    }
}