package th.ac.kku.cis.mobileapp.Myfinalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    private val  TAG: String = "Login Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (mAuth!!.currentUser != null){
            startActivity(Intent(this@LoginActivity, Main_tableaddmin::class.java))
            finish()
        }

        btt_signin.setOnClickListener {
            val email = EmailText.text.toString().trim{ it <= ' '}
            val password = PasswordText.text.toString().trim{ it <= ' '}

            if (email.isEmpty()){
                Toast.makeText(this@LoginActivity,"กรุณาใส่ รหัสผ่านให้ดูดต้อง",Toast.LENGTH_LONG).show()
                Log.d(TAG, "E-mail!!!")
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this@LoginActivity,"กรุณาใส่ รหัสผ่านให้ดูดต้อง",Toast.LENGTH_LONG).show()
                Log.d(TAG, "Password!!!")
                return@setOnClickListener
            }
            mAuth!!.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                if (!task.isSuccessful){
                    if (password.length < 10){
                        PasswordText.error = "กรุณาใส่ Password ให้ถูกต้อง"
                        Log.d(TAG,"กรุณาใส่ Password มากกว่า 10ตัว")
                    }else{
                        Toast.makeText(this@LoginActivity,"ล้มเหลว",Toast.LENGTH_LONG).show()
                        Log.d(TAG, "ล้มเหลว: " + task.exception)
                    }
                }else{
                    Toast.makeText(this@LoginActivity,"Login สำเร๋จ",Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Login เสร็จสิ้น")
                    startActivity(Intent(this@LoginActivity, Main_tableaddmin::class.java))
                    finish()
                }
            }
        }


            btn_home.setOnClickListener {
            startActivity(Intent(this@LoginActivity, Main_pattern::class.java))



    }
}
}

