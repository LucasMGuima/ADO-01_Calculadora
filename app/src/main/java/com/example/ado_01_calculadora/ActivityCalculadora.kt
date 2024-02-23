package com.example.ado_01_calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ado_01_calculadora.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

class ActivityCalculadora : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG_OPERACAO = "OPERACAO"
    private val TAG_MULTIPLICACAO = "MULT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    //Função que inicia os listeners
    private fun initListener(){
        binding.btnCalcular.setOnClickListener {
            calcular()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calcular(){
        //variavel para amarzenar o resultado
        var result = 0.0f

        //pega os valores para a conta
        val num1 = binding.inpNum1.text.toString().toFloat()
        val num2 = binding.inpNum2.text.toString().toFloat()

        //ve qual operação efetuar
        val operacaoId = binding.radrioGroupOperacoes.checkedRadioButtonId
        Log.i(TAG_OPERACAO, operacaoId.toString())
        when(operacaoId){
            binding.radioButtonSoma.id -> {
                result = soma(num1, num2)
            }
            binding.radioButtonSubtracao.id -> {
                result = subtracao(num1, num2)
            }
            binding.radioButtonMultiplicacao.id -> {
                result = multiplicacao(num1, num2)
            }
            binding.radioButtonDivisao.id -> {
                if (num2 == 0.0f){
                    Toast.makeText(this, "ERRO: Divisão por 0", Toast.LENGTH_SHORT).show()
                    return
                } else result = divisao(num1, num2)
            }
        }

        //Se o valor for inteiro remove o .0
        val result_tratado: Number = if ((result*10)%10 > 0) result else result.toInt()

        Log.i(TAG_OPERACAO, "num1: $num1 num2: $num2 | resultado: $result_tratado")
        binding.txtResultado.text = if (result_tratado is Float) "Resultado: %.2f".format(result_tratado) else "Resultado: $result_tratado"
    }

    //Operacoes
    private fun soma(num1: Float, num2: Float): Float{
        return num1 + num2
    }
    private fun subtracao(num1: Float, num2: Float): Float{
        return  num1 - num2
    }
    private fun multiplicacao(num1: Float, num2: Float): Float{
        return num1 * num2
    }
    private fun divisao(num1: Float, num2: Float): Float{
        return num1/num2
    }
}