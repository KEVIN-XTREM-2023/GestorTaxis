package com.example.appproyecto

import android.content.Context
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import android.widget.ArrayAdapter
import android.widget.CursorAdapter
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import com.example.appproyecto.databinding.ActivityViajesBinding

class ViajesActivity : AppCompatActivity() {

    lateinit var lista : ListView;
    lateinit var arayLista : ArrayList<HashMap<String,String>>;

    var URL: String="http://192.168.101.5:8082/taxista/listViaAsigTax.php";

    private lateinit var binding: ActivityViajesBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: viajeData
    var dataArrayList = ArrayList<viajeData?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viajes)


        lista = findViewById<ListView>(R.id.listViajes)

            traerDatos();

    }

    private fun traerDatos(){
        var id = globales.id

            var requestQueue: RequestQueue = Volley.newRequestQueue(this)
            var stringRequest: StringRequest = object: StringRequest(Request.Method.POST,URL, Response.Listener { response ->
                println("id--> "+id)
                println("Entro1--> "+response.trim())
                if(!response.trim().equals("0")){

                   //Toast.makeText(this,"Correcto", Toast.LENGTH_LONG).show()
                    convertirJson(response.trim())
                }else{

                    Toast.makeText(this,"No existe Asignaciones", Toast.LENGTH_LONG).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String>? {
                    val params=HashMap<String, String>()
                    params.put("id_tax_per",id.toString())
                    return params
                }
            }
            requestQueue.add(stringRequest)

        println("Entro3")
    }

    private fun convertirJson(jsonString: String?){
        val jsonArray = JSONArray(""+jsonString+"")


        var x=0;

        while(x<jsonArray.length()){
            val JSONObject = jsonArray.getJSONObject(x)
            println("cedula--->"+JSONObject.getString("id_tax_per").toString())
            var cp = "Calle Principal: "+JSONObject.getString("call_pri_via").toString()
            var id = JSONObject.getString("id_via").toString()
            var idt = JSONObject.getString("id_tax_per").toString()

            var cs = "Calle Secundaria: "+JSONObject.getString("call_sec_via").toString()
            var re = "Referencia: "+JSONObject.getString("ref_via").toString()
            var sec = "Sector: "+JSONObject.getString("sect_via").toString()
            var inf = "Información: "+JSONObject.getString("inf_via").toString()
            var fec = "Fecha: "+JSONObject.getString("fec_asig_via").toString()
            this.listData = viajeData(id,idt,cp,cs,re,sec,inf,fec)
            this.dataArrayList.add(this.listData)
            x++
        }

        this.listAdapter = viajeAdapter(this@ViajesActivity,this.dataArrayList)
        this.lista.adapter = this.listAdapter
        this.lista.isClickable = true




    }


}