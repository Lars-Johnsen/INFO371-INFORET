package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sorter {

    /** 
    
     * Sort a map according to values. 
     
     * @param  < K >  the key of the map. 
     * @param  < V >  the value to sort according to. 
     * @param mapToSort the map to sort. 
     
     * @return a map sorted on the values. 
     
     */    
      
    public static  < K, V extends Comparable < ? super V >  >  Map < K, V >   
    sortMapByValuesAscending(final Map  < K, V >  mapToSort)  
    {  
        List < Map.Entry < K, V >  >  entries =  
            new ArrayList < Map.Entry < K, V >  > (mapToSort.size());    
      
        entries.addAll(mapToSort.entrySet());  
      
        Collections.sort(entries,  
                         new Comparator < Map.Entry < K, V >  > ()  
        {  
            @Override  
            public int compare(  
                   final Map.Entry < K, V >  entry1,  
                   final Map.Entry < K, V >  entry2)  
            {  
                return entry1.getValue().compareTo(entry2.getValue());  
            }  
        });        
      
        Map < K, V >  sortedMap = new LinkedHashMap < K, V > ();        
      
        for (Map.Entry < K, V >  entry : entries)  
        {  
            sortedMap.put(entry.getKey(), entry.getValue());  
        }        
      
        return sortedMap;  
    }  
    
    public static  < K, V extends Comparable < ? super V >  >  Map < K, V >   
    sortMapByValuesDescending(final Map  < K, V >  mapToSort)  
    {  
        List < Map.Entry < K, V >  >  entries =  
            new ArrayList < Map.Entry < K, V >  > (mapToSort.size());    
      
        entries.addAll(mapToSort.entrySet());
      
        Collections.sort(entries,  
                         new Comparator < Map.Entry < K, V >  > ()  
        {  
            @Override  
            public int compare(  
                   final Map.Entry < K, V >  entry1,  
                   final Map.Entry < K, V >  entry2)  
            {  
                return entry2.getValue().compareTo(entry1.getValue());  
            }  
        });        
      
        Map < K, V >  sortedMap = new LinkedHashMap < K, V > ();        
      
        for (Map.Entry < K, V >  entry : entries)  
        {  
            sortedMap.put(entry.getKey(), entry.getValue());  
        }        
      
        return sortedMap;  
    } 
	
}
