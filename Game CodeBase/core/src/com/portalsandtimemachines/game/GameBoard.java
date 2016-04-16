package com.portalsandtimemachines.game;

import java.util.*;

public class GameBoard {

		// portals
	    HashMap<Integer, List<Integer>> hmap = new HashMap<Integer, List<Integer>>();
	    int[] A = new int[8];
	    int i = 0;
	    
	    /* Function that sets the position of the portals for the 
	    * duration of the game.
	    * @param none
	    */
	    void init()
	    {
	        for( int i = 0;i < 8;i++)           // There will be 8 portals in the Game board.
	        {
	            set_portals();
	        }
	  
	    }
	    
	    /* Function that displays the information regarding all the portals. 
	    *  Used by developers for verification.
	    * @param none
	    */
	    void print()
	    {
	        for(int key : hmap.keySet())
	        {
	            System.out.println(key + " : " +hmap.get(key));
	        }
	    }
	    
	    
	    int[] portal_positions()
	    {
	        return A;
	    }
	    /* Function that computes the position of each portal in the game board and their c
	    *  corresponding attributes. Their attributes correspod to information such as 
	    *  whether the portal will push down(-1) or up(+1), how many squares will the pawn 
	    *  be moved and final square in which the pawn will land.
	    * @param none
	    */
	    void set_portals()
	    {
	        Random rand = new Random();
	        int portal_pos;
	        int orientation;
	        int moving_spaces = 0;
	        int final_pos = 0;
	        
	        portal_pos = rand.nextInt((99 - 2) + 1) + 2;
	 
	        while(hmap.containsKey(portal_pos))         // If the position already exist in the map.
	        {
	            portal_pos = rand.nextInt((99 - 2) + 1) + 2;
	        }
	            
	        if(portal_pos < 10 && portal_pos > 2 )
	            orientation = 1;
	        else if(portal_pos <= 99 && portal_pos >= 90) 
	            orientation = -1;
	        else
	        {
	            int temp = rand.nextInt((1 - 0) + 1) + 0;
	            if(temp == 0)
	                orientation = 1;
	            else
	                orientation = -1;
	        }
	            
	        int check = 0;
	        while(check == 0)                           // Flag to check whether the resulting square is within range of the Game board.
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
	        List<Integer> myList = new ArrayList<Integer>();
	        myList.add(orientation);
	        myList.add(moving_spaces);
	        myList.add(final_pos);
	        hmap.put(portal_pos,myList);
	        A[i] = portal_pos;
	        i++;
	        
	    }
	    
	    
	    /* Function that checks whether the passed square number has a portal 
	    *  associated with it. If it is true then the function returns the 
	    *  square number to which the pawn needs to be moved, else it returns -1.
	    *  
	    * @param position of the square.
	    */
	    int check_portal(int pos)
	    {
	        if(hmap.containsKey(pos))
	        {
	            List<Integer> temp;
	            for(int key : hmap.keySet())
	            {
	                if(key == pos)
	                {
	                    temp = hmap.get(pos);
	                    return temp.get(2);             // Returns the position to which the pwan needs to be moved.
	                }
	            }
	        }
	        return -1;                                  //  If the square does not have a portal associatd with it.
	    }
	    
	    
	    
	    
	    
	    // ******************** Time_Machine****************
	    HashMap<Integer, List<Integer>> hmap1 = new HashMap<Integer, List<Integer>>();
	    int[] B = new int[8];
	    int j = 0;
	    
	    /* Function that sets the position of the time-machine for the 
	    * duration of the game.
	    * @param none
	    */
	    void init_time_machine()
	    {
	        for( int i = 0;i < 3;i++)           // There will be 3 time machine in the Game board.
	        {
	            set_time_machine();
	        }
	    }
	    
	    
	    int[] TM_positions()
	    {
	        return B;
	    }
	    /* Function that computes the position of each portal in the game board and their c
	    *  corresponding attributes. Their attributes correspod to information such as 
	    *  whether the portal will push down(-1) or up(+1), how many squares will the pawn 
	    *  be moved and final square in which the pawn will land.
	    * @param none
	    */
	    void set_time_machine()
	    {
	        Random rand = new Random();
	        int TM_pos;
	        int orientation;
	        int moving_spaces = 0;
	        int final_pos = 0;
	        
	        TM_pos = rand.nextInt((99 - 12) + 1) + 2;
	 
	        while(hmap1.containsKey(TM_pos) || hmap.containsKey(TM_pos))         // If the position already exist in the map.
	        {
	            TM_pos = rand.nextInt((99 - 12) + 1) + 2;
	        }
	            
	        orientation = 1;
	        
	        int check = 0;
	        while(check == 0)                           // Flag to check whether the resulting square is within range of the Game board.
	        {
	            int moving = rand.nextInt(4) + 7;
	            int sum = 0;
	            sum = TM_pos + moving;
	            
	            if(sum < 99 && sum > 2)
	            {
	                moving_spaces = moving;
	                final_pos = sum;
	                check = 1;
	            }
	        }
	        List<Integer> myList = new ArrayList<Integer>();
	        myList.add(orientation);
	        myList.add(moving_spaces);
	        myList.add(final_pos);
	        hmap1.put(TM_pos,myList);
	        B[j] = TM_pos;
	        j++;
	    }
	    
	    /* Function that checks whether the passed square number has a portal 
	    *  associated with it. If it is true then the function returns the 
	    *  square number to which the pawn needs to be moved, else it returns -1.
	    *  
	    * @param position of the square.
	    */
	    int check_TM(int pos)
	    {
	        if(hmap1.containsKey(pos))
	        {
	            List<Integer> temp;
	            for(int key : hmap1.keySet())
	            {
	                if(key == pos)
	                {
	                    temp = hmap1.get(pos);
	                    return temp.get(2);             // Returns the position to which the pwan needs to be moved.
	                }
	            }
	        }
	        return 0;                                  //  If the square does not have a portal associatd with it.
	    }
	
}
