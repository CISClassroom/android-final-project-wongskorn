package th.ac.kku.cis.mobileapp.Myfinalproject

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
import kotlinx.android.synthetic.main.activity_main_addminedittime.*

class Main_addminedittime : AppCompatActivity() {
    val TAG = "Main_addminedittime"
    var numberpe :String? = ""
    var carnumber :String? = ""
    var selecttime :String? = ""
    var alltime = arrayOf("เวลา")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_addminedittime)
        Firebase.database.reference.child("tabletime").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    alltime += postSnapshot.key.toString()

                }
                spinnertime.adapter = ArrayAdapter(this@Main_addminedittime,android.R.layout.simple_spinner_item,alltime)
                spinnertime.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        var activity = alltime[position]
                        if(activity=="==เลือกกิจกรรม=="){
                            selecttime=""
                        }else{
                            selecttime=activity
                        }
                        Log.d(TAG,"time select = "+selecttime.toString())
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        selecttime=""
                    }
                }
                btt_successedit.setOnClickListener {

                }


            }
            override fun onCancelled(p0: DatabaseError) {}
        })
    }
    private fun AddtoFirebase(idStudent:String,nameStudent:String,selectActivity:String){
        val idStudent = idStudent
        val nameStudent= nameStudent
        val nameActivity= selectActivity
        val myDatabase = FirebaseDatabase.getInstance().getReference("act_stu")
        myDatabase.child(nameActivity).child(idStudent).setValue(nameStudent).addOnCompleteListener{
            Toast.makeText(this,"เพิ่มข้อมูลสำเร็จ",Toast.LENGTH_SHORT).show()
            val intent = intent
            finish()
            startActivity(intent)
        }
    }
}