package th.ac.kku.cis.mobileapp.Myfinalproject.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelCar
import th.ac.kku.cis.mobileapp.Myfinalproject.R

class data_adapter(var mCtx: Context, var resource:Int, var item:List<ModelCar>)
    : ArrayAdapter<ModelCar>( mCtx , resource , item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource,null)
        var tvTopic : TextView = view.findViewById(R.id.txt_Topic)
        var tvSubTopic : TextView = view.findViewById(R.id.txt_sub)
        var tvSubTime : TextView = view.findViewById(R.id.txt_time)
        var show: ModelCar = item[position]
        tvSubTime.text = show.time
        tvTopic.text = show.numbercar
        tvSubTopic.text = show.numberpeople
        return view
    }
}
