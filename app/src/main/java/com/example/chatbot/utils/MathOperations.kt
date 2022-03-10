package com.example.chatbot.utils

object MathOperations {

    fun returnResult(equation : String) : String{
        val replacedEquation = equation.replace(" " , "")
        val operands = replacedEquation.split("-","+","/","*")

        return when {
            replacedEquation.contains("+") -> {
                (operands[0].toInt() + operands[1].toInt()).toString()
            }
            replacedEquation.contains("-") -> {
                (operands[0].toInt() - operands[1].toInt()).toString()
            }
            replacedEquation.contains("/") -> {
                if(operands[1].toInt() != 0) {
                    (operands[0].toInt() / operands[1].toInt()).toString()
                }else {
                    return "error"
                }

            }
            replacedEquation.contains("*") -> {
                (operands[0].toInt() * operands[1].toInt()).toString()
            }
            else -> {""}
        }
    }

}
