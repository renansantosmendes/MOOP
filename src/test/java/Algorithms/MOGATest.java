/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import ProblemRepresentation.Solution;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author renansantos
 */
public class MOGATest {

    public MOGATest() {
    }

    @Test
    public void testEvaluatePopulation() {
        MOGA moga = (MOGA) new MOGA()
                .setCrossOverProbability(0.7)
                .setMutationProbabilty(0.02)
                .setExtrapolationParameter(0.1)
                .setNumberOfExecutions(5)
                .setNumberOfGenerations(2)
                .setNumberOfChromossomes(2)
                .setPopulationSize(100)
                .setFileSize(100)
                .setVariableLowerBound(0)
                .setVariableUpperBound(1);

//        moga.initializePopulation();
//        moga.evaluatePopulation();
//        moga.nonDomination();
//        moga.printPopulation();
//        System.out.println("Non dominated set");
//        moga.printNonDominatedSet();
        moga.execute();
//        moga.printNonDominatedSet();
        moga.printFileNonDominatedSet();
    }

}
