/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Random;

/**
 *
 * @author Ashwin Murthy
 */
class Trial {
    void set_portals()
    {
        Random rand = new Random();
        int min = 2;
        int max = 99;
        String orientation = "";
        int moving_spaces = 0;
        int final_pos = 0;
        int portal_pos = 0;
        int flag = 0;
        
        while(flag == 0)
        {
            int portal = rand.nextInt((max - min) + 1) + min;
            portal_pos = portal;
            if(portal < 10 && portal > 2 )
            {
                orientation = "UP";
                flag = 1;
            }
            else if(portal < 99 && portal > 90)
            {
                orientation = "DOWN";
                flag = 1;
            }
            else
            {
                int temp = rand.nextInt((1 - 0) + 1) + 0;
                System.out.println("temp : 1\n ");
                if(temp == 0)
                {
                    orientation = "UP";
                    flag = 1;
                }
                else
                {
                    orientation = "DOWN";
                    flag = 1;
                }
            }
            
            int check = 0;
            while(check == 0)
            {
                int moving = rand.nextInt((30 - 5) + 1) + 5;
                int sum;
                if("UP".equals(orientation))
                {
                    sum = moving + portal;
                }
                else
                {
                    sum = portal - moving;
                }
                
                if(sum < 99 && sum > 2)
                {
                    moving_spaces = moving;
                    final_pos = sum;
                    check = 1;
                    flag = 1;
                }
            }
        }
        System.out.println("Portal position : ");
        System.out.println(portal_pos);
        System.out.println("String Orientation : ");
        System.out.println(orientation);
        System.out.println("Moving Space : ");
        System.out.println(moving_spaces);
        
        System.out.println("Final position : ");
        
        System.out.println(final_pos);
    }
}
