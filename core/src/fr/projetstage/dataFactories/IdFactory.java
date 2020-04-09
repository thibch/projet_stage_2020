package fr.projetstage.dataFactories;

public class IdFactory {


    private static IdFactory instance;
    private int id;


    private IdFactory(){
        instance = this;
        id = 2;
    }

    public static IdFactory getInstance() {
        if (instance == null) {
            instance = new IdFactory();
        }
        return instance;
    }

    public int getId(){
        return id++;
    }

}
