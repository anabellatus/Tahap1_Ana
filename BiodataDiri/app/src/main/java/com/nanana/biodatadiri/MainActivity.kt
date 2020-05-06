package com.nanana.biodatadiri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var namaInput: String = ""
    private var emailInput: String = ""
    private var telpInput: String = ""
    private var alamatInput: String = ""
    private var genderInput: String = ""
    private var ageInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDataSpinnerGender()

        btnSave.setOnClickListener {
            validasiInput()
        }
    }

    private fun validasiInput() {
        namaInput = edtName.text.toString()
        emailInput = edtEmail.text.toString()
        telpInput = edtTelp.text.toString()
        alamatInput = edtAddress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()
        ageInput = edtUmur.text.toString()


        if (error()){
            tampilToast("Navigasi ke halaman profil")
            goToProfileActivity()
        }
    }

    private fun error(): Boolean {
        if (namaInput.isEmpty()) {
            edtName.error = "Nama tidak boleh kosong"
            edtName.requestFocus()
        }

        if (genderInput.equals("Pilih Jenis Kelamin", ignoreCase = true)) {
            tampilToast("Jenis Kelamin harus dipilih")
        }

        if (emailInput.isEmpty()) {
            edtEmail.error = "Email tidak boleh kosong"
            edtEmail.requestFocus()
        }

        if (telpInput.isEmpty()){
            edtTelp.error = "Telp tidak boleh kosong"
            edtTelp.requestFocus()
        }

        if (alamatInput.isEmpty()){
            edtAddress.error = "Alamat tidak boleh kosong"
            edtAddress.requestFocus()
        }

        if (ageInput.isEmpty()){
            edtUmur.error = "Umur tidak bolej kosong"
            edtUmur.requestFocus()
        }
        return true
    }

    private fun tampilToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToProfileActivity() {

        val intent = Intent(this, ProfileActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        bundle.putString("umur", ageInput)

        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.jenis_kelamin, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter
    }
}