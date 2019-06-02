package javaK;

import java.util.ArrayList;
import java.util.List;

public class Main {

        public static void main(String args[])
        {
            Instance instance = new Instance();
            Solution solution = new Solution();
            instance.setBackpack(20);
            Greedy greedy= new Greedy();
            Rs rs = new Rs();
            Bf bf = new Bf();
            instance.addItem(new Item(1,10,1.2));
            instance.addItem(new Item(2,3,2.3));
            instance.addItem(new Item(3,7,1.7));
            instance.addItem(new Item(4,1,2.9));
            List <Item> list = new ArrayList<>();
           // solution = greedy.solve(instance,0,0,list) ;

           // solution = rs.solve(instance,0,0,list) ;
            solution = bf.solve(instance,0,0,list) ;
            System.out.println(solution.getBestValue());
            System.out.println(solution.getBestWeight());
            System.out.println(solution.getSize());


        }}
