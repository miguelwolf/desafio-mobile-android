package br.com.kplay.desafiomobileandroidproject.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class Currency {

    companion object {

        private val DOLAR = DecimalFormatSymbols(Locale.US)

        /**
         * Mascara de dinheiro para Dolar Americano
         */
        val DINHEIRO_DOLAR =
            DecimalFormat("¤ ###,###,##0.00", DOLAR)

        /**
         * Simbolos especificos do Euro
         */
        private val EURO = DecimalFormatSymbols(Locale.GERMANY)

        /**
         * Mascara de dinheiro para Euro
         */
        val DINHEIRO_EURO = DecimalFormat("¤ ###,###,##0.00", EURO)

        /**
         * Locale Brasileiro
         */
        private val BRAZIL = Locale("pt", "BR")

        /**
         * Sï¿½mbolos especificos do Real Brasileiro
         */
        private val REAL = DecimalFormatSymbols(BRAZIL)

        /**
         * Mascara de dinheiro para Real Brasileiro
         */
        val DINHEIRO_REAL = DecimalFormat("¤ ###,###,##0.00", REAL)

        /**
         * Mascara texto com formatacao monetaria
         *
         * @param valor Valor a ser mascarado
         * @param moeda Padrao monetario a ser usado
         * @return Valor mascarado de acordo com o padrao especificado
         */
        fun mascaraDinheiro(valor: Double, moeda: DecimalFormat): String? {
            return moeda.format(valor)
        }

        fun duasCasas(input: Double): BigDecimal? {
            return BigDecimal(input).setScale(2, RoundingMode.HALF_UP)
        }

        /**
         * Método responsável por transformar um valor que estaja no formato de preço para um valor double
         * @param value, preço que deseja ser transoformado para double.
         * @return
         */
        fun toDouble(value: String): Double {
            return value.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".")
                .toDouble()
        }
    }

}