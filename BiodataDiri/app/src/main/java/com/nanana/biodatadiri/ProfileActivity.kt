package com.nanana.biodatadiri

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    companion object {
        val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        ambilData()

        btnEditName.setOnClickListener {
            navigasiKeEditProfile()
        }

        btnCall.setOnClickListener {
            dialPhoneNumber(txtTelp.text.toString())
        }
    }

    private fun dialPhoneNumber(phoneNumber: String){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    private fun navigasiKeEditProfile(){
        val intent = Intent(this, EditProfileActivity::class.java)

        val namaUser = txtName.text.toString()
        intent.putExtra("nama", namaUser)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                val result = data?.getStringExtra("nama")
                txtName.text = result
            }else{
                Toast.makeText(this, "Edit Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun ambilData() {

        val bundle = intent.extras

        val nama = bundle!!.getString("nama")
        val gender = bundle.getString("gender")
        val email = bundle.getString("email")
        val telp = bundle.getString("telp")
        val alamat = bundle.getString("alamat")

        txtName.text = nama
        txtGender.text = gender
        txtEmail.text = email
        txtTelp.text = telp
        txtAddress.text = alamat
    }
}
