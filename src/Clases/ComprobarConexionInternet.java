package Clases;

import java.io.PrintStream;
import java.net.Socket;

public class ComprobarConexionInternet
{
  public boolean test()
  {
    String dirWeb = "www.google.com";
    int puerto = 80;
    try
    {
      Socket s = new Socket(dirWeb, puerto);
      if (s.isConnected()) {
        System.out.println("Conexi�n establecida con la direcci�n: " + dirWeb + " a trav�s del puerto: " + puerto);
      }
    }
    catch (Exception e)
    {
      System.err.println("No se pudo establecer conexi�n con: " + dirWeb + " a trav�s del puerto: " + puerto);
      return false;
    }
    return true;
  }
}