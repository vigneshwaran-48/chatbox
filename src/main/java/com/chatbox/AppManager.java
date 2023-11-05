package com.chatbox;

public class AppManager {
    
    private static AppManager INSTANCE;

    private AppManager() {}

    public static AppManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new AppManager();
        }
        return INSTANCE;
    }
}
