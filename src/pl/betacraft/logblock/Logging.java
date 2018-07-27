package pl.betacraft.logblock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
 
public class Logging implements Runnable
 {
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
     /*String filePath = "LogBlock/logi.log";
     try {
       BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
       out.write(getDateAndTime() + " [LogBlock] " + msg);
       out.newLine();
       out.close();
     } catch (Exception e) {
       logger.info("[LogBlock] Nie mozna zapisac w pliku \"" + filePath + "\"");
     }*/
   }
   
   public void run()
   {
       // Nope :P
   }
}