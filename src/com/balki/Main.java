package com.balki;

import java.util.*;

public class Main {
    private static List<String> queueX = new ArrayList<>();
    private static List<String> queueY = new ArrayList<>();

    private static List<Thread> threads = new ArrayList<>();

    static {
       threads.add(new MyFileReader("A"));
       threads.add(new MyFileReader("B"));

        Collections.shuffle(threads);
    }

    public static void main(String[] args) {
        for(Thread t : threads){
            t.start();;
        }
    }

    private static synchronized void readFile(String id, String fileName){
        System.out.println(id + " reads file : " + fileName);
        switch(fileName){
            case "X":{
                queueX.add(id);
                break;
            }
            case "Y":{
                queueY.add(id);
                break;
            }
        }
    }

    static class MyFileReader extends Thread {
        public MyFileReader(String name) {
            super.setName(name);
        }

        @Override
        public void run(){
           while(!queueX.contains(getName())){
               readFile(getName(),"X");
           }

           int myOrder = 0;
           for(int i=0; i<queueX.size(); i++){
               String p = queueX.get(i);
               if(p.equals(getName())){
                   myOrder = i;
                   break;
               }
           }

           while(!queueY.contains(getName())){
               if(queueY.size() == myOrder) {
                   readFile(getName(), "Y");
               }
           }

            System.out.println("Completed my job.");
        }
    }
}
