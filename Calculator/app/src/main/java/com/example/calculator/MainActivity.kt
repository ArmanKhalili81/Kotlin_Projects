package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var isnumberinputtext = false
    var hasdotinputtext = false
    var isoperator = false
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    fun buttonview(view : View)
    {
        var buttonclick = view as Button
        binding.textView.append(buttonclick.text)
        isnumberinputtext = true
    }

    fun ClearClicke(view: View){
        binding.textView.text = ""
        isnumberinputtext = false
        hasdotinputtext = false
        isoperator = false
    }

    fun DotClick(view: View){
        if(isnumberinputtext && !hasdotinputtext){
            binding.textView.append(".")
            hasdotinputtext = true
        }
    }

    fun operator(view: View){
        if(isnumberinputtext && !isoperator)
        {
            binding.textView.append((view as Button).text)
            isoperator = true
            hasdotinputtext = false
            isnumberinputtext = false
        }
    }


    fun NegativePosititve(view: View){
        binding.textView.text = "-"
    } // it's for to be negative first number !!!

    fun Equal(view: View){
        var perfix = ""
        if(isnumberinputtext)
        {
            var inputvalue = binding.textView.text.toString()
            when{
                inputvalue.contains("+") -> {
                    var splitarray = inputvalue.split("+")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    val result = firstnumber.toDouble() + secondnumber.toDouble()

                    binding.textView.text = result.toString()
                }

                inputvalue.contains("/") -> {
                    var splitarray = inputvalue.split("/")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    val result = firstnumber.toDouble() / secondnumber.toDouble()

                    binding.textView.text = result.toString()
                }

                inputvalue.contains("*") -> {
                    var splitarray = inputvalue.split("*")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    val result = firstnumber.toDouble() * secondnumber.toDouble()

                    binding.textView.text = result.toString()
                }

                inputvalue.contains("-") -> {
                    if(inputvalue.startsWith("-"))
                    {
                        perfix = "-"
                        inputvalue = inputvalue.substring(1)
                    }
                    var splitarray = inputvalue.split("-")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    if(!perfix.isEmpty())
                    {
                        firstnumber = perfix + firstnumber
                    }
                    val result = firstnumber.toDouble() - secondnumber.toDouble()

                    binding.textView.text = result.toString()
                }

                inputvalue.contains("%") -> {
                    var splitarray = inputvalue.split("%")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    val result = firstnumber.toDouble() % secondnumber.toDouble()

                    binding.textView.text = result.toString()
                }

                inputvalue.contains("@") -> {
                    var splitarray = inputvalue.split("@")  // split method return array
                    var firstnumber = splitarray[0]
                    var secondnumber = splitarray[1]
                    val result = (firstnumber.toDouble() * secondnumber.toDouble())/100

                    binding.textView.text = result.toString()
                }
            }
            isoperator = false
        }
    }
}