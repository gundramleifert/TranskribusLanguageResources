/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.transkribus.languageresources.languagemodels;

import eu.transkribus.languageresources.languagemodels.NeuralForecastOneLanguageModel;
import de.unileipzig.asv.neuralnetwork.utils.Utils;
import eu.transkribus.languageresources.exceptions.UnsupportedSequenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author max
 */
public class NeuralForecastOneLanguageModelTest
{
    
    public NeuralForecastOneLanguageModelTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetProbabilityForNextToken()
    {
        try
        {
            NeuralForecastOneLanguageModel nlm = new NeuralForecastOneLanguageModel("nn/lm_bozen_characters.zip");
            
            List<String> sequence = new ArrayList<>();
            sequence.add("+");
            
            Map<String, Integer> types = nlm.getTypes();
            Map<String, Double> probabilitiesForNextToken = nlm.getProbabilitiesForNextToken(sequence);
            double[] givenProbabilities = Utils.loadValues("nn/lm_bozen_characters_forecasted.txt").get("+");
            
            for(String type : types.keySet())
            {
                assertEquals(givenProbabilities[types.get(type)], probabilitiesForNextToken.get(type), 0.001);
            }
        } catch (UnsupportedSequenceException ex)
        {
            Logger.getLogger(NeuralForecastOneLanguageModelTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
