package th.ac.kku.cis.mobileapp.Myfinalproject.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelAddmin
import th.ac.kku.cis.mobileapp.Myfinalproject.R

class data_adapteraddmin (var mCtx: Context, var resource:Int, var item:List<ModelAddmin>)
    : ArrayAdapter<ModelAddmin>( mCtx , resource , item) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource,null)
        var tvTopic : TextView = view.findViewById(R.id.txt_TopicAdd)
        var tvSubTopic : TextView = view.findViewById(R.id.txt_subAdd)
        var tvSubTime : TextView = view.findViewById(R.id.txt_timeAdd)
        var show: ModelAddmin = item[position]
        tvSubTime.text = show.time
        tvTopic.text = show.numbercar
        tvSubTopic.text = show.numberpeople

        var btndel : Button= view.findViewById(R.id.btn_del)
        btndel.setOnClickListener {

            val id = show.carname
            val myDatabase = FirebaseDatabase.getInstance().getReference(id)
            val builder = AlertDialog.Builder(context)
            builder.setTitle("ยืนยันการลบ?")
            builder.setMessage("ยืนยันการลบ "+id)
            builder.setPositiveButton("ยืนยัน"){dialog, which ->
                myDatabase.removeValue().addOnSuccessListener {
                    Toast.makeText(context,"ลบ "+id+" เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
                }
            }
            builder.setNegativeButton("กลับ"){dialog,which ->
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        return view
    }
}
