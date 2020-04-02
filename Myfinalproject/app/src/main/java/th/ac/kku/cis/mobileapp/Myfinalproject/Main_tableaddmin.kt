package th.ac.kku.cis.mobileapp.Myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_tableaddmin.*
import th.ac.kku.cis.mobileapp.Myfinalproject.Adapter.data_adapter
import th.ac.kku.cis.mobileapp.Myfinalproject.Adapter.data_adapteraddmin
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelAddmin
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelCar

class Main_tableaddmin : AppCompatActivity() {
    var mAuth:FirebaseAuth? = null


    private val TAG: String = "List Car"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tableaddmin)
        mAuth = FirebaseAuth.getInstance()

        Firebase.database.reference.addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listView: ListView = findViewById(R.id.list_tabletimeAddmin)
                val code_list = mutableListOf<ModelAddmin>()
                for(postSnapshot in dataSnapshot.children){
                    //Log.d(TAG,postSnapshot.toString())
                    code_list.add(ModelAddmin(
                        postSnapshot.child("numbercar").getValue().toString()
                        ,postSnapshot.child("numberpeople").getValue().toString()
                        ,postSnapshot.child("time").getValue().toString()
                        ,postSnapshot.key.toString())
                    )
                }
                listView.adapter = data_adapteraddmin(
                    this@Main_tableaddmin,
                    R.layout.activity_main_pattern_addmin,
                    code_list
                )
                listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as ModelAddmin
                }
                btn_edit.setOnClickListener {
                    startActivity(Intent(this@Main_tableaddmin, Add::class.java))
                }
                btn_logout.setOnClickListener {
                    mAuth!!.signOut()
                    Toast.makeText(this@Main_tableaddmin,"LogOut",Toast.LENGTH_LONG).show()
                    Log.d(TAG, " LogOut")
                    startActivity(Intent(this@Main_tableaddmin, LoginActivity::class.java))
                }


            }
        }
        )

    }
}
