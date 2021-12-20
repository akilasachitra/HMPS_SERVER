package com.hmps.hmps.system;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestReceiveManager {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7771);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // read the list of messages from the socket
        Object readObject = objectInputStream.readObject();
        System.out.println("Received [" + readObject + "] messages from: " + socket);
        // print out the text of every message
        System.out.println("All messages:");
        System.out.println(readObject);

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();

    }

}
