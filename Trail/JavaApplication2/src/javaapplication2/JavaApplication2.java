/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/** Function simply used to test the functionalities of the Trial class.
 *  Will be removed in the later stages of the project.
 *
 * @author Ashwin Murthy
 */

public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Trial obj = new Trial();
        obj.init();                 // Function called to set the initial position nd attributes of the portals.
        obj.print(); 
        int b[] = obj.lets_see();
        for(int i = 0; i < b.length; i++)
        {
            System.out.println(b[i]);
        }
        // Function called to print out the details regarding the protals.
        // TODO code application logic here
    }
    
}