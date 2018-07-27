package pl.betacraft.logblock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
 
public class Logging implements Runnable {
   private static final Logger logger = Logger.getLogger("Minecraft.LogBlock");
   public static LogBlock plugin;
   
   public static void setPlugin(LogBlock ics) {
       plugin = ics;
   }

   public static String getDateAndTime() {
       DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy-HH;mm;ss");

       Date date = new Date();

       return dateFormat.format(date);
   }

   public static void log(String msg) {
	   if (LogBlock.consoleoutput) {
		   logger.info("[LogBlock] " + msg);
	   }
   }
   
   public void run() {}
}