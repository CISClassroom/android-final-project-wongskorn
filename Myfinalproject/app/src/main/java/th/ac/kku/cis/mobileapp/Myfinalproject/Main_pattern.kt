package th.ac.kku.cis.mobileapp.Myfinalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_pattern.*
import th.ac.kku.cis.mobileapp.Myfinalproject.Adapter.data_adapter
import th.ac.kku.cis.mobileapp.Myfinalproject.Model.ModelCar
import kotlin.math.log


class Main_pattern : AppCompatActivity() {
    private val TAG: String = "List Car"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_pattern)
        Firebase.database.reference.addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listView: ListView = findViewById(R.id.list_tabletime)
                val code_list = mutableListOf<ModelCar>()
                for(postSnapshot in dataSnapshot.children){
                    //Log.d(TAG,postSnapshot.toString())
                    code_list.add(ModelCar(
                        postSnapshot.child("numbercar").getValue().toString()
                        ,postSnapshot.child("numberpeople").getValue().toString()
                        ,postSnapshot.child("time").getValue().toString())
                    )
                }
                listView.adapter = data_adapter(
                    this@Main_pattern,
                    R.layout.activity_pattern,
                    code_list
                )
                listView.setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position) as ModelCar
                }
                btn_login.setOnClickListener {
                            startActivity(Intent(this@Main_pattern, LoginActivity::class.java))
                }
            }
        }
        )

    }
}