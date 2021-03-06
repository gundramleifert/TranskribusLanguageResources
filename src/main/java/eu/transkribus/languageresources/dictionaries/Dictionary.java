/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.transkribus.languageresources.dictionaries;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author max
 */
public class Dictionary
{

    private final Set<Entry> entries;

    public Dictionary()
    {
        entries = new HashSet<>();
    }
    
    public Dictionary(List<String> tokenizedText)
    {
        this();
        
        for(String token : tokenizedText)
        {
            addEntry(token);
        }
    }

    public void addEntry(String name)
    {
        for (Entry entry : entries)
        {
            if (entry.getKeyEntry().getName().equals(name))
            {
                entry.getKeyEntry().increaseFrequency();
                return;
            }
        }

        entries.add(new Entry(name));
    }

    public void addAdditionalValue(String key, String additionalName)
    {
        for (Entry entry : entries)
        {
            if (entry.getKeyEntry().getName().equals(key))
            {
                entry.addAdditionalValue(additionalName);
                return;
            }
        }

        Entry e = new Entry(key);
        e.addAdditionalValue(additionalName);
    }

    public Set<Entry> getEntries()
    {
        return entries;
    }

    public boolean containsKeyEntry(String name)
    {
        for (Entry e : entries)
        {
            if (e.getKeyEntry().getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }

    public Entry getEntryByKeyName(String name)
    {
        for (Entry e : entries)
        {
            if (e.getKeyEntry().getName().equals(name))
            {
                return e;
            }
        }

        throw new RuntimeException("Could not find entry with given key name: "+name);
    }

    public void addEntry(Entry entry)
    {
        this.entries.add(entry);
    }

    public Entry getEntry(String key) {
        for ( Entry e : entries )
            if ( e.getKeyEntry().getName().equals(key) )
                return e;
        return null;
    }
    
    public double outOfVocabulary(Dictionary smallerDictionary)
    {
        int countTotal = entries.size();
        int countFound = 0;
        
        for(Entry externalEntry : smallerDictionary.entries)
        {
            if(entries.contains(externalEntry))
            {
                countFound++;
            }
        }
        
        return (double) (countTotal - countFound) / (double) countTotal;
    }
}
