package com.vaccae.vnanomsg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPAIR.setOnClickListener {
            intent = Intent(this, PAIRActivity::class.java)
            startActivity(intent)
        }

        btnBUS.setOnClickListener {
            intent = Intent(this, BUSActivity::class.java)
            startActivity(intent)
        }

        btnREQREP.setOnClickListener {
            intent = Intent(this, REQREPActivity::class.java)
            startActivity(intent)
        }

        btnPUBSUB.setOnClickListener {
            intent = Intent(this, PUBSUBActivity::class.java)
            startActivity(intent)
        }

        btnSURVEY.setOnClickListener {
            intent = Intent(this, SURVEYActivity::class.java)
            startActivity(intent)
        }

    }


}
