package com.cuwallet.commons.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The default class for creating protocol sockets.  This class just uses the
 * {@link java.net.Socket socket} constructors.
 * 
 * @author Michael Becke
 * 
 * @since 2.0
 */
public class DefaultProtocolSocketFactory implements ProtocolSocketFactory {

    /**
     * The factory singleton.
     */
    private static final DefaultProtocolSocketFactory factory = new DefaultProtocolSocketFactory();
    
    /**
     * Gets an singleton instance of the DefaultProtocolSocketFactory.
     * @return a DefaultProtocolSocketFactory
     */
    static DefaultProtocolSocketFactory getSocketFactory() {
        return factory;
    }
    
    /**
     * Constructor for DefaultProtocolSocketFactory.
     */
    public DefaultProtocolSocketFactory() {
        super();
    }

    /**
     * @see #createSocket(java.lang.String,int,java.net.InetAddress,int)
     */
    public Socket createSocket(
        String host,
        int port,
        InetAddress clientHost,
        int clientPort
    ) throws IOException, UnknownHostException {
        return new Socket(host, port, clientHost, clientPort);
    }

    /**
     * @see ProtocolSocketFactory#createSocket(java.lang.String,int)
     */
    public Socket createSocket(String host, int port)
        throws IOException, UnknownHostException {
        return new Socket(host, port);
    }

    /**
     * All instances of DefaultProtocolSocketFactory are the same.
     */
    public boolean equals(Object obj) {
        return ((obj != null) && obj.getClass().equals(DefaultProtocolSocketFactory.class));
    }

    /**
     * All instances of DefaultProtocolSocketFactory have the same hash code.
     */
    public int hashCode() {
        return DefaultProtocolSocketFactory.class.hashCode();
    }

}