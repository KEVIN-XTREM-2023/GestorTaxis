package com.example.appproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.Runnable
import org.json.JSONArray

class opcionesActivity : AppCompatActivity() {

    var jornada: Int=0;
    var estado: Int=0;
    var id: Int=0;
    var nombreUsu: String="";
    var URLJornada: String="http://192.168.101.5:8082/taxista/actJornadaTax.php";
    var URLEstado: String="http://192.168.101.5:8082/taxista/actEstadoTax.php";
    var URLtraerEstado: String="http://192.168.101.5:8082/taxista/listarDatosTaxistas.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        regresar()
        viajes()
        menu()
        estado()
        jornada()
        verEstado()
        this.id = Integer.parseInt(globales.id)
        this.nombreUsu = globales.nombre
        this.jornada = Integer.parseInt(globales.jornada)
        this.estado = Integer.parseInt(globales.estado)
        var boton : ImageButton = findViewById(R.id.imgbtnJornada)
        var botonEstado : ImageButton = findViewById(R.id.imgbtnEstado);
        if (this.jornada == 1) {
            boton.setImageResource(R.drawable.activo);
        }else {
            boton.setImageResource(R.drawable.noactivo);
        }
        if (this.estado == 1) {
            botonEstado.setImageResource(R.drawable.ocupado);
        }else {
            botonEstado.setImageResource(R.drawable.libre);
        }

    }

    private fun traerEstado(){
        var id = globales.id

        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLtraerEstado, Response.Listener { response ->

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
        var estadov:String="";

        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)

            estadov=JSONObject.getString("est_tax").toString()


            x++
        }
        println(this.estado)
        this.estado=Integer.parseInt(estadov)
        println(this.estado)
    }

    fun verEstado(){
        val thread = Thread(Runnable {
            while (true) {
                traerEstado()
                //runOnUiThread { Runnable{

                    var boton : ImageButton = findViewById(R.id.imgbtnEstado)

                        if (this.estado == 1) {
                            println("es uno")
                            this.estado = 0;
                            boton.setImageResource(R.drawable.libre);
                        }else {
                            println("es cero")
                            this.estado = 1;
                            boton.setImageResource(R.drawable.ocupado);
                        }
                Thread.sleep(5000)
        }


        })

        thread.start()
    }

    fun regresar(){
        var boton : Button = findViewById(R.id.btnRegresar)
        boton.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)

            startActivity(intent)
        }
    }
    fun viajes(){
        var boton : ImageButton = findViewById(R.id.imgbtnViajes)
        boton.setOnClickListener {
            val intent = Intent(this,ViajesActivity::class.java)
            startActivity(intent)
        }
    }
    fun menu(){
        var boton : ImageButton = findViewById(R.id.imgbtnMenu)
        boton.setOnClickListener {
            val intent = Intent(this,misdatosActivity::class.java)
            startActivity(intent)
        }
    }

    fun estado(){
        var boton : ImageButton = findViewById(R.id.imgbtnEstado)
        boton.setOnClickListener {
            if (this.estado == 1) {
                this.estado = 0;
                boton.setImageResource(R.drawable.libre);
            }else {

                this.estado = 1;
                boton.setImageResource(R.drawable.ocupado);
            }
            enviarEstado();
        }
    }
    fun jornada(){
        var boton : ImageButton = findViewById(R.id.imgbtnJornada)
        boton.setOnClickListener {
            if (this.jornada == 1) {
                this.jornada = 0;
                boton.setImageResource(R.drawable.noactivo);
            }else {

                this.jornada = 1;
                boton.setImageResource(R.drawable.activo);
            }
            enviarJornada();
        }
    }

    private fun enviarJornada(){

        var valId = this.id
        var valJornada = this.jornada
            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLJornada, Response.Listener { response ->
                if(!response.trim().equals("No hay datos")){
                    Toast.makeText(this,"Jornada Actualizada", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("id_tax",valId.toString())
                    params.put("jor_tax",valJornada.toString())
                    return params
                }
            }
            requestQueue.add(stringRequest)
    }

    private fun enviarEstado(){

        var valId = this.id
        var valEstado = this.estado
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URLEstado, Response.Listener { response ->
            if(!response.trim().equals("No hay datos")){
                Toast.makeText(this,"Estado Actualizado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
            }
        }, Response.ErrorListener { error ->
            Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String>? {
                val params=HashMap<String, String>()
                params.put("id_tax",valId.toString())
                params.put("est_tax",valEstado.toString())
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}