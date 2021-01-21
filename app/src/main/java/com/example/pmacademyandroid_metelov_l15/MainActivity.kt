package com.example.pmacademyandroid_metelov_l15

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pmacademyandroid_metelov_l15.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupOnClickListeners()
        setupRecyclerView()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupOnClickListeners() {
        binding.btnAddToList.setOnClickListener {
            if (fieldsAreEmpty()) {
                Toast.makeText(
                    this@MainActivity,
                    "All fields are required! ",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (inputIsValid()) {
                val firstName = binding.etFirstName.text.toString()
                val lastName = binding.etLastName.text.toString()
                val fullName = SpannableString("$firstName $lastName")
                val colorSpan = ForegroundColorSpan(Color.RED)

                fullName.apply {
                    setSpan(colorSpan, 0, firstName.length, 0)
                    setSpan(UnderlineSpan(), 0, firstName.length, 0)
                }
                myAdapter.addItem(fullName)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvList.run {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )
        }
    }

    private fun fieldsAreEmpty(): Boolean {
        //if one of the fields is empty
        return (binding.etFirstName.text.toString() == "" ||
                binding.etLastName.text.toString() == ""
                )
    }

    private fun inputIsValid(): Boolean {
        var errorMessage = ""

        val firstNameCharSequence = binding.etFirstName.text.toString().toCharArray()
        val lastNameCharSequence = binding.etLastName.text.toString().toCharArray()

        //check if Name is longer than 1 symbol
        var lengthIsCorrectFlag = false
        if (firstNameCharSequence.size > 1 && lastNameCharSequence.size > 1) {
            lengthIsCorrectFlag = true
        } else errorMessage += "Must be more than 1 symbol in each field! "

        //check if the first symbol is UpperCase
        var upperCaseIsCorrectFlag = false
        if (firstNameCharSequence[0].isUpperCase() && lastNameCharSequence[0].isUpperCase()) {
            upperCaseIsCorrectFlag = true
        } else errorMessage += "First symbol must be UpperCase! "

        return if (lengthIsCorrectFlag && upperCaseIsCorrectFlag) {
            true
        } else {
            Toast.makeText(
                this@MainActivity,
                errorMessage,
                Toast.LENGTH_SHORT
            ).show()
            false
        }

    }

}