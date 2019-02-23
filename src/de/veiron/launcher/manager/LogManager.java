package de.veiron.launcher.manager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {

    static Logger logger;

    private Logger getLogger(){
        if(logger == null){
            new LogManager();
        }
        return logger;
    }

    public void log(Level level, String msg){
        getLogger().log(level, msg);
        System.out.println(msg);
    }

}
