package Clases;

import Clases.EnviarMailComplejo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

class Hilo2 extends Thread
{
  private boolean continuar = true;
  public static  boolean  envioExito= false;
  
  public void detenElHilo()
  {
    this.continuar = false;
  }
  
  public void run()
  {
    while (this.continuar)
    {
      if (EmailInterfaz.adjunto.isEmpty())
      {
        EnviarMail propio = new EnviarMail(Login.user, Login.contra, EmailInterfaz.destinatarios, EmailInterfaz.asunto, EmailInterfaz.mensaje, Login.servidor);
        try
        {
          propio.enviar();
          envioExito=true;
          String notificacion = "Su mensaje fué enviado exitosamente\n a los siguientes contactos:\n";
          for (String value : EmailInterfaz.destinatarios) {
            notificacion = notificacion + "-" + value + " \n";
          }
          EmailInterfaz.notificarEnvio(false);
          JOptionPane.showMessageDialog(null, new Object[] { notificacion }, "Mensaje Enviado", 1, null);
        }
        catch (MessagingException ex)
        {
          JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
          Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
          EmailInterfaz.notificarEnvio(false);
          EmailInterfaz.vaciar(true);
          detenElHilo();
        }
      }
      else
      {
        EnviarMailComplejo propio = new EnviarMailComplejo(Login.user, Login.contra, EmailInterfaz.destinatarios, EmailInterfaz.asunto, EmailInterfaz.mensaje, EmailInterfaz.adjuntos, Login.servidor);
        try
        {

          propio.Enviar();
          envioExito=true;

          String notificacion = "Su mensaje fué enviado exitosamente\n a los siguientes contactos:\n";
          for (String value : EmailInterfaz.destinatarios) {
            notificacion = notificacion + "-" + value + " \n";
          }
          EmailInterfaz.notificarEnvio(false);
          EmailInterfaz.vaciar(true);
          JOptionPane.showMessageDialog(null, new Object[] { notificacion }, "Mensaje Enviado", 1, null);
        }
        catch (MessagingException ex)
        {
          JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
          Logger.getLogger(Hilo2.class.getName()).log(Level.SEVERE, null, ex);
          EmailInterfaz.notificarEnvio(false);
          detenElHilo();
        }
      }
      detenElHilo();
    }
  }
}

