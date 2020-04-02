package th.ac.kku.cis.mobileapp.Myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main_addminedittime.*
import kotlinx.android.synthetic.main.activity_main_addminedittime.Text_numCar
import kotlinx.android.synthetic.main.activity_main_addminedittime.btt_successedit
import kotlinx.android.synthetic.main.activity_main_addminedittime.spinnertime
import kotlinx.android.synthetic.main.activity_main_addminedittime.text_numP
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelAddEdit

class Add : AppCompatActivity() {
    val TAG = "AddActivity"
    var numberpe :String? = ""
    var carnumber :String? = ""
    var selecttime :String = ""

    var alltime = arrayOf("เวลา","06.00","07.00","08.00","09.00","10.00","11.00","12.00","13.00","14.00","15.00","16.00","17.00","18.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

         spinnertime.adapter = ArrayAdapter(this@Add,android.R.layout.simple_spinner_item,alltime)

         spinnertime.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        var time = alltime[position]
                        if(time=="==เลือกกิจกรรม=="){
                            selecttime=""
                        }else{
                            selecttime=time
                        }
                        Log.d(TAG,"time select = "+selecttime.toString())
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selecttime=""
                    }
                }
                btt_successedit.setOnClickListener {
                    Toast.makeText(this,selecttime,Toast.LENGTH_LONG).show()
                    var nc = text_namecar.text.toString()
                    val myDatabase = FirebaseDatabase.getInstance().getReference(nc)
                    var sucspintime= selecttime
                    val sucnumP= text_numP.text.toString()
                    val sumnumcar = Text_numCar.text.toString()
                    if(sucspintime==""||sucnumP==""||sumnumcar==""){
                        Toast.makeText(this, "กรอกข้อมูลไม่ครบถ้วน", Toast.LENGTH_SHORT).show()
                    }else{
                        val car = ModelAddEdit(sumnumcar,sucnumP,sucspintime)
                        myDatabase.setValue(car).addOnCompleteListener{
                            Toast.makeText(this,"เพิ่มสำเร็จ",Toast.LENGTH_SHORT).show()
                            var i = Intent(this,Main_tableaddmin::class.java)
                            startActivity(i)
                        }
                    }
                   // btn_login.setOnClickListener {
                    //    startActivity(Intent(this@Main_pattern, LoginActivity::class.java))

                }

    }
}
