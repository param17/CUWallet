package com.cuwallet.commons.service;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A ProtocolSocketFactory that is secure.
 * 
 * @see org.apache.commons.httpclient.protocol.ProtocolSocketFactory
 * 
 * @author Michael Becke
 * @author Mike Bowler
 * @since 2.0
 */
public interface SecureProtocolSocketFactory extends ProtocolSocketFactory {

    /**
     * Returns a socket connected to the given host that is layered over an
     * existing socket.  Used primarily for creating secure sockets through
     * proxies.
     * 
     * @param socket the existing socket 
     * @param host the host name/IP
     * @param port the port on the host
     * @param autoClose a flag for closing the underling socket when the created
     * socket is closed
     * 
     * @return Socket a new socket
     * 
     * @throws IOException if an I/O error occurs while creating the socket
     * @throws UnknownHostException if the IP address of the host cannot be
     * determined
     */
    Socket createSocket(
        Socket socket, 
        String host, 
        int port, 
        boolean autoClose
    ) throws IOException, UnknownHostException;              

}
