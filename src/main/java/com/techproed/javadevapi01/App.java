package com.techproed.javadevapi01;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) {
       
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
       
        //For all datas
        HashMap<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        
        //Enter the elements of map 
        for(int i=1; i<=n; i++){
            
            int d = scan.nextInt();
             
            List<Integer> listOfInt = new ArrayList<Integer>();
            
            for(int j=0; j<d; j++ ){
                String s = scan.nextLine();
                
                String a[] = s.split(" ");
                System.out.println("Arrays.toString -> "+Arrays.toString(a));                 
            }
        
        map.put(i,listOfInt);
        }
        System.out.println("map-> "+map);     
    }
}
