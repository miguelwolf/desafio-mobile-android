package br.com.kplay.desafiomobileandroidproject.utils

class Constants {

    companion object {

        val BUNDLE = "Bundle"
        val IV_TRANSITION = "iv_transition"
        val URL_BASE = "https://raw.githubusercontent.com/myfreecomm/desafio-mobile-android/master/api/"

        val HTTPRETRYEXCEPTION = 10 /** Thrown to indicate that a HTTP request needs to be retried but cannot be retried automatically, due to streaming mode being enabled.  */
        val UNKNOWNHOSTEXCEPTION_WIFI_3G = 21 /** java.net.UnknownHostException: Unable to resolve host "asiodfhiasdhfoihasodfhoasdhf.com": No address associated with hostname  */
        val UNKNOWNHOSTEXCEPTION_DNS = 22 /** java.net.UnknownHostException: Unable to resolve host "asiodfhiasdhfoihasodfhoasdhf.com": No address associated with hostname  */
        val UNKNOWNSERVICEEXCEPTION = 30
        val CONNECTTIMEOUTEXCEPTION = 40 /** A timeout while connecting to an HTTP server or waiting for an available connection from an HttpConnectionManager.  */
        val INTERRUPTEDIOEXCEPTION = 50 /** Signals that an I/O operation has been interrupted. An InterruptedIOException is thrown to indicate that an input or output transfer has been terminated because the thread performing it was interrupted. The field bytesTransferred indicates how many bytes were successfully transferred before the interruption occurred.  */
        val MALFORMEDJSONEXCEPTION = 60 /** Thrown when a reader encounters malformed JSON. Some syntax errors can be ignored by calling  */
        val ASYNCHRONOUSCLOSEEXCEPTION = 70 /** Checked exception received by a thread when another thread closes the channel or the part of the channel upon which it is blocked in an I/O operation.  */
        val CONNECTEXCEPTION = 80 /** Signals that an error occurred while attempting to connect a socket to a remote address and port. Typically, the connection was refused remotely (e.g., no process is listening on the remote address/port). **/
        val SOCKETEXCEPTION = 90
        val IOEXCEPTION = 100
        
    }

}