package com.example.kalkulator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        
        val backspace: Button = binding.backspace
        val Button_1: Button = binding.tombol1
        val Button_2: Button = binding.tombol2
        val Button_3: Button = binding.tombol3
        val Button_4: Button = binding.tombol4
        val Button_5: Button = binding.tombol5
        val Button_6: Button = binding.tombol6
        val Button_7: Button = binding.tombol7
        val Button_8: Button = binding.tombol8
        val Button_9: Button = binding.tombol9
        val Button_0: Button = binding.tombol0
        val Button_plus: Button = binding.tombolTambah
        val Button_minus: Button = binding.tombolKurang
        val Button_kali: Button = binding.tombolKali
        val Button_bagi: Button = binding.tombolBagi
        val Button_clear: Button = binding.tombolHapus
        val Button_equals: Button = binding.tombolSamaDengan
        val mathOperator = arrayOf("+", "-", "*", "/")

        Button_0.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value != "0"){
                binding.operasi.text = value + "0"
            }
        }
        Button_1.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "1"
                return@setOnClickListener
            }
            binding.operasi.text = value + "1"
        }
        Button_2.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "2"
                return@setOnClickListener
            }
            binding.operasi.text = value + "2"
        }
        Button_3.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "3"
                return@setOnClickListener
            }
            binding.operasi.text = value + "3"
        }
        Button_4.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "4"
                return@setOnClickListener
            }
            binding.operasi.text = value + "4"
        }
        Button_5.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "5"
                return@setOnClickListener
            }
            binding.operasi.text = value + "5"
        }
        Button_6.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "6"
                return@setOnClickListener
            }
            binding.operasi.text = value + "6"
        }
        Button_7.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "7"
                return@setOnClickListener
            }
            binding.operasi.text = value + "7"
        }
        Button_8.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "8"
                return@setOnClickListener
            }
            binding.operasi.text = value + "8"
        }
        Button_9.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (value == "0"){
                binding.operasi.text = "9"
                return@setOnClickListener
            }
            binding.operasi.text = value + "9"
        }
        Button_clear.setOnClickListener {
            binding.operasi.text = "0"
        }
        Button_bagi.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (!mathOperator.contains(value.last().toString())){
                binding.operasi.text = value + "/"
                return@setOnClickListener
            }
            binding.operasi.text = value.dropLast(1) + "/"
        }
        Button_kali.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (!mathOperator.contains(value.last().toString())){
                binding.operasi.text = value + "*"
                return@setOnClickListener
            }
            binding.operasi.text = value.dropLast(1) + "*"
        }
        Button_minus.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (!mathOperator.contains(value.last().toString())){
                binding.operasi.text = value + "-"
                return@setOnClickListener
            }
            binding.operasi.text = value.dropLast(1) + "-"
        }
        Button_plus.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (!mathOperator.contains(value.last().toString())){
                binding.operasi.text = value + "+"
                return@setOnClickListener
            }
            binding.operasi.text = value.dropLast(1) + "+"
        }

        backspace.setOnClickListener(){
            if (binding.operasi.text.toString().length > 1){
                binding.operasi.text = binding.operasi.text.dropLast(1)
                return@setOnClickListener
            }
            if (binding.operasi.text.toString().length == 1 && binding.operasi.text.toString() != "0"){
                binding.operasi.text = "0"
                return@setOnClickListener
            }
        }


        Button_equals.setOnClickListener(){
            var value = binding.operasi.text.toString()
            if (mathOperator.contains(value.last().toString())){
                value = value.dropLast(1)
            }
            value = cal(value)
            binding.hasil.text = value
        }
    }

    fun cal(values: String): String{
//        value = "1+2*3"
        val hasil:Float
        val mathOperator = arrayOf("+", "-", "*", "/")
        val stack = ArrayDeque<String>()
        var temp: String = ""

        for (value in values){
            if (value.isDigit()){
                temp += value
                continue
            }
            if (mathOperator.contains(value.toString())){
                stack.addLast(temp)
                stack.addLast(value.toString())
                temp = ""
            }
        }

        if (temp != ""){
            stack.addLast(temp)
        }
//        stack = [1, +, 2, *, 3]
        val infix: ArrayDeque<String> = infixToPostfix(stack) // infix = [1, 2, 3, *, +]

        hasil = hitungInfix(infix) // hasil = 7

        return  hasil.toString()
    }

    fun hitungInfix(infix: ArrayDeque<String>): Float{
        var hasil:Float
        val mathOperator = arrayOf("+", "-", "*", "/")
        val stack = ArrayDeque<String>()

        for (value in infix){
            if (!mathOperator.contains(value.toString())){
                stack.addLast(value)
                continue
            }

            val bilangan1 = stack.removeLast().toFloat()
            val bilangan2 = stack.removeLast().toFloat()

            if (value == "+"){
                hasil = bilangan2 + bilangan1
                stack.addLast(hasil.toString())
                continue
            }
            if (value == "-"){
                hasil = bilangan2 - bilangan1
                stack.addLast(hasil.toString())
                continue
            }
            if (value == "*"){
                hasil = bilangan2 * bilangan1
                stack.addLast(hasil.toString())
                continue
            }
            if (value == "/"){
                hasil = bilangan2 / bilangan1
                stack.addLast(hasil.toString())
                continue
            }
        }

        hasil = stack.removeLast().toFloat()
        return hasil

    }

    fun infixToPostfix(values: ArrayDeque<String>): ArrayDeque<String> {

        val hasil = ArrayDeque<String>()
        val stack = ArrayDeque<String>()
        val mathOperator = arrayOf("+", "-", "*", "/")

        for (value in values){
            if (!mathOperator.contains(value.toString())){
                hasil.addLast(value)
                continue
            }
            if (stack.isEmpty()){
                stack.addLast(value)
                continue
            }
            if (tingkatOperator(value) > tingkatOperator(stack.last())){
                stack.addLast(value)
                continue
            }
            while (stack.isNotEmpty() && tingkatOperator(value) <= tingkatOperator(stack.last())){
                hasil.addLast(stack.removeLast())
            }
            stack.addLast(value)
            continue
        }
        while (stack.isNotEmpty()){
            hasil.addLast(stack.removeLast())
        }
        return hasil
    }

    fun tingkatOperator(operator: String): Int{
        if (operator == "+" || operator == "-"){
            return 1
        }
        if (operator == "*" || operator == "/"){
            return 2
        }
        return 0
    }

}
