package com.example.interviewassignment

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.interviewassignment.API.ApiInterface
import com.example.interviewassignment.API.ApiUtilities
import com.example.interviewassignment.Repository.MyRepository
import com.example.interviewassignment.ViewModel.MyViewModel
import com.example.interviewassignment.ViewModel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val myRepository = MyRepository(apiInterface)

        myViewModel = ViewModelProvider(this, MyViewModelFactory(myRepository)).get(MyViewModel::class.java)

        myViewModel.data.observe(this, {

            val fact:TextView = findViewById(R.id.fact)
            val length:TextView = findViewById(R.id.length)

            fact.setText(it.fact)
            length.setText(it.length.toString())

        })


        onBackPressedDispatcher.addCallback(this /* lifecycle owner */, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this@MainActivity)

                // set message of alert dialog
                dialogBuilder.setMessage("Do you want to close this application ?")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Yes", DialogInterface.OnClickListener {
                            dialog, id -> finish()
                    })
                    // negative button text and action
                    .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Exit")
                // show alert dialog
                alert.show()

            }
        })
    }
}