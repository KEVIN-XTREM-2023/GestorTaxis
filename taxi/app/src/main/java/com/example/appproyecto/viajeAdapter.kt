package com.example.appproyecto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class viajeAdapter(context: Context, dataArrayList: ArrayList<viajeData?>?):
ArrayAdapter<viajeData?>(context,R.layout.item_via, dataArrayList!!){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val listData = getItem(position)

        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_via, parent, false)
        }

        val listCallP = view?.findViewById<TextView>(R.id.txtCalleP)
        val listCallS = view?.findViewById<TextView>(R.id.txtCalleS)
        val listRef = view?.findViewById<TextView>(R.id.txtReferencia)
        val listSec = view?.findViewById<TextView>(R.id.txtSector)
        val listInf = view?.findViewById<TextView>(R.id.txtInformacion)
        val listFe = view?.findViewById<TextView>(R.id.txtFecha)

        if (listCallP != null) {
            if (listData != null) {
                listCallP.text = listData.call_pri
            }
        }
        if (listCallS != null) {
            if (listData != null) {
                listCallS.text = listData.call_sec
            }
        }

        if (listRef != null) {
            if (listData != null) {
                listRef.text = listData.ref
            }
        }

        if (listSec != null) {
            if (listData != null) {
                listSec.text = listData.sec
            }
        }

        if (listInf != null) {
            if (listData != null) {
                listInf.text = listData.inf
            }
        }

        if (listFe != null) {
            if (listData != null) {
                listFe.text = listData.fec
            }
        }

        return view!!
    }
}