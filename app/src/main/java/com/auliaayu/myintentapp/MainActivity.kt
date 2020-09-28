package com.auliaayu.myintentapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity = findViewById<Button>(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnMoveWithObject:Button = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        val btnDialPhone:Button = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)


        val btnMOveForResult:Button =findViewById(R.id.btn_move_for_result)
        btnMOveForResult.setOnClickListener(this)



    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_move_activity_data ->{
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Aulia Ayu Dyah Lestari")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 16)
                startActivity(moveWithDataIntent)
            }
            R.id.btn_move_acitivty_object -> {
                val person = Person(
                    "Aulia Ayu Dyah Lestari",
                    5,
                    "auliaaayu@gmail.com",
                    "Magelang"
                )

                val moveWithObjectIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)

                val btnDialPhone:Button =findViewById(R.id.btn_dial_number)
                btnDialPhone.setOnClickListener(this)
            }
            R.id.btn_dial_number -> {
                val phoneNumber = "081231888226"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }
            R.id.btn_move_for_result -> {
                val moveForResultIntent = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                startActivityForResult(moveForResultIntent, REQUEST_CODE)
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == REQUEST_CODE) {
                if (resultCode == MoveForResultActivity.RESULT_CODE) {
                    val selectedValue = data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
                    tv_result.text = "Hasil : $selectedValue"
                }
            }
        }
    }
}