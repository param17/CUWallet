package com.cuwallet.commons.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A factory for creating Sockets.
 * 
 * 
Both {@link java.lang.Object#equals(java.lang.Object) Object.equals()} and 
 * {@link java.lang.Object#hashCode() Object.hashCode()} should be overridden appropriately.  
 * Protocol socket factories are used to uniquely identify Protocols and 
 * HostConfigurations, and equals() and hashCode() are 
 * required for the correct operation of some connection managers.


 * 
 * @see Protocol
 * 
 * @author Michael Becke
 * @author Mike Bowler
 * 
 * @since 2.0
 */
public interface ProtocolSocketFactory {

    /**
     * Gets a new socket connection to the given host.
     * 
     * @param host the host name/IP
     * @param port the port on the host
     * @param clientHost the local host name/IP to bind the socket to
     * @param clientPort the port on the local machine
     * 
     * @return Socket a new socket
     * 
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     */
    Socket createSocket(
        String host, 
        int port, 
        InetAddress clientHost, 
        int clientPort
    ) throws IOException, UnknownHostException;

    /**
     * Gets a new socket connection to the given host.
     *
     * @param host the host name/IP
     * @param port the port on the host
     *
     * @return Socket a new socket
     *
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     */
    Socket createSocket(
        String host, 
        int port
    ) throws IOException, UnknownHostException;

}