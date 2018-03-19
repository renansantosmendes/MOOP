/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ProblemRepresentation.Solution;
import java.util.Comparator;

/**
 *
 * @author renansantos
 */
public class Main {

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm()
                .setCrossOverProbability(0.7)
                .setMutationProbabilty(0.02)
                .setNumberOfExecutions(5)
                .setNumberOfGenerations(100)
                .setExtrapolationParameter(0.1)
                .setPopulationSize(100);

        geneticAlgorithm.execute();
//        metaheuristic.printPopulation();
//        metaheuristic.initializePopulation();
        geneticAlgorithm.printPopulation();
    }
}
