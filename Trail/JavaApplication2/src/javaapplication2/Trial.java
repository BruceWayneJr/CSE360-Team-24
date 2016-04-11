/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.util.*;

/**
 *
 * @author Ashwin Murthy
 */
class Trial 
{
    HashMap<Integer, List<Integer>> hmap = new HashMap<>();
    
    
    void init()
    {
        for( int i = 0;i < 8;i++)
        {
            set_portals();
        }
  
    }
    
    void print()
    {
        for(int key : hmap.keySet())
        {
            System.out.println(key + " : " +hmap.get(key));
        }
    }
    
    void set_portals()
    {
        Random rand = new Random();
        int portal_pos;
        int orientation;
        int moving_spaces = 0;
        int final_pos = 0;
        
        int flag = 0;
        
        portal_pos = rand.nextInt((99 - 2) + 1) + 2;
 
        while(hmap.containsKey(portal_pos))
        {
            portal_pos = rand.nextInt((99 - 2) + 1) + 2;
        }
            
        if(portal_pos < 10 && portal_pos > 2 )
            orientation = 1;
        else if(portal_pos >= 99 || portal_pos <= 90) 
        {
            int temp = rand.nextInt((1 - 0) + 1) + 0;
            if(temp == 0)
                orientation = 1;
            else
                orientation = -1;
        } else
            orientation = -1;
            
        int check = 0;
        while(check == 0)
        {
            int moving = rand.nextInt((30 - 5) + 1) + 5;
            int sum;
            if(orientation == 1)
                sum = portal_pos + moving;
            else
                sum = portal_pos - moving;
            if(sum < 99 && sum > 2)
            {
                moving_spaces = moving;
                final_pos = sum;
                check = 1;
            }
        }
        
        List<Integer> myList = new ArrayList<>();
        myList.add(orientation);
        myList.add(moving_spaces);
        myList.add(final_pos);
        hmap.put(portal_pos,myList);
        
    }
}