package com.vaccae.nanomsg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.nanomsg.mvp.view.PipePullActivity
import com.vaccae.nanomsg.mvp.view.REQREPActivity
import com.vaccae.nanomsg.mvvm.view.PipePushActivity
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

        btnPIPELinePush.setOnClickListener {
            intent = Intent(this, PipePushActivity::class.java)
            startActivity(intent)
        }

        btnPIPELinePull.setOnClickListener {
            intent = Intent(this, PipePullActivity::class.java)
            startActivity(intent)
        }

    }


}
