/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strings;

/**
 *
 * @author WillianWozniak
 */
public class Strings
{
    
    public static String getOn(String value, String start, String end)
    {
        if (!value.contains(start)) 
            return null;
        
        value = value.substring(value.indexOf(start) + start.length());
        value = value.substring(0, value.indexOf(end)).trim();
        
        return value;
    }
    
}
