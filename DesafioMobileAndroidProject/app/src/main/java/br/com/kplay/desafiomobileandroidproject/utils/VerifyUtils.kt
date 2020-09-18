package br.com.kplay.desafiomobileandroidproject.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.*
import java.nio.channels.AsynchronousCloseException

class VerifyUtils {

    companion object {

        /**
         * Método responsável por tratar as exceções da requisição.
         *
         * @param context,    deve ser passado o contexto atual da aplicação.
         * @param screenName, nome da tela atual.
         * @param t,          exceção ocorrida durante a exceção.
         * @param integracao, integração do usuário.
         */
        fun responseRequest(
            context: Context,
            t: Throwable,
        ): Int {
            if (t.javaClass == HttpRetryException::class.java) {
                return Constants.HTTPRETRYEXCEPTION

            } else if (t.javaClass == UnknownHostException::class.java) {
                val connMgr =
                    context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                val mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                return if (wifi.isConnectedOrConnecting || mobile.isConnectedOrConnecting) {
                    Constants.UNKNOWNHOSTEXCEPTION_DNS
                } else {
                    Constants.UNKNOWNHOSTEXCEPTION_WIFI_3G
                }
            } else if (t.javaClass == UnknownServiceException::class.java) {
                return Constants.UNKNOWNSERVICEEXCEPTION

            } else if (t.javaClass == ConnectTimeoutException::class.java) {
                return Constants.CONNECTTIMEOUTEXCEPTION

            } else if (t.javaClass == InterruptedIOException::class.java) {
                return Constants.INTERRUPTEDIOEXCEPTION

            } else if (t.javaClass == MalformedJsonException::class.java) {
                return Constants.MALFORMEDJSONEXCEPTION

            } else if (t.javaClass == AsynchronousCloseException::class.java) {
                return Constants.ASYNCHRONOUSCLOSEEXCEPTION

            } else if (t.javaClass == ConnectException::class.java) {
                return Constants.CONNECTEXCEPTION

            } else if (t.javaClass == SocketException::class.java) {
                return Constants.SOCKETEXCEPTION

            } else if (t.javaClass == IOException::class.java) {
                return Constants.IOEXCEPTION
            }

            return 0
        }

    }

}