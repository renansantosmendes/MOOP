/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author renansantos
 */
public class Main {

    public static void main(String[] args) {
        Metaheuristics metaheuristic = new Metaheuristics()
                .setCrossOverProbability(0.7)
                .setMutationProbabilty(0.02)
                .setNumberOfExecutions(5)
                .setNumberOfGenerations(100)
                .setPopulationSize(100);
        
        metaheuristic.geneticAlgorithm();
        //metaheuristic.printPopulation();
    }
}
