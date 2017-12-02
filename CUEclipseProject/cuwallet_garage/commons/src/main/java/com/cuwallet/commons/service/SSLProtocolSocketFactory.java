package com.cuwallet.commons.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

/**
 * A SecureProtocolSocketFactory that uses JSSE to create sockets.
 * 
 * @author Michael Becke
 * @author Mike Bowler
 * 
 * @since 2.0
 */
public class SSLProtocolSocketFactory implements SecureProtocolSocketFactory {

    /**
     * The factory singleton.
     */
    private static final SSLProtocolSocketFactory factory = new SSLProtocolSocketFactory();
    
    /**
     * Gets an singleton instance of the SSLProtocolSocketFactory.
     * @return a SSLProtocolSocketFactory
     */
    static SSLProtocolSocketFactory getSocketFactory() {
        return factory;
    }    
    
    /**
     * Constructor for SSLProtocolSocketFactory.
     */
    public SSLProtocolSocketFactory() {
        super();
    }

    /**
     * @see SecureProtocolSocketFactory#createSocket(java.lang.String,int,java.net.InetAddress,int)
     */
    public Socket createSocket(
        String host,
        int port,
        InetAddress clientHost,
        int clientPort)
        throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(
            host,
            port,
            clientHost,
            clientPort
        );
    }

    /**
     * @see SecureProtocolSocketFactory#createSocket(java.lang.String,int)
     */
    public Socket createSocket(String host, int port)
        throws IOException, UnknownHostException {
        return SSLSocketFactory.getDefault().createSocket(
            host,
            port
        );
    }

    /**
     * @see SecureProtocolSocketFactory#createSocket(java.net.Socket,java.lang.String,int,boolean)
     */
    public Socket createSocket(
        Socket socket,
        String host,
        int port,
        boolean autoClose)
        throws IOException, UnknownHostException {
        return ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(
            socket,
            host,
            port,
            autoClose
        );
    }

    /**
     * All instances of SSLProtocolSocketFactory are the same.
     */
    public boolean equals(Object obj) {
        return ((obj != null) && obj.getClass().equals(SSLProtocolSocketFactory.class));
    }

    /**
     * All instances of SSLProtocolSocketFactory have the same hash code.
     */
    public int hashCode() {
        return SSLProtocolSocketFactory.class.hashCode();
    }    
    
}