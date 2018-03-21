/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author renansantos
 */
public class MOGA extends GeneticAlgorithm {

    public void evaluatePopulation() {
        for (int i = 0; i < this.populationSize; i++) {
            List<Double> objectiveFunctions = new ArrayList<>();
            double f1 = population.get(i).getChromossomes().get(0);
            double g = 1 + 9 * population.get(i).getChromossomes().get(1);
            double f2 = g * (1 - Math.sqrt(f1 / g));
            objectiveFunctions.add(f1);
            objectiveFunctions.add(f2);

            this.population.get(i).setObjectiveFunctions(objectiveFunctions);
        }

    }

    public void nonDomination() {
        for (int i = 0; i < this.populationSize; i++) {
            population.get(i).clearDominatedSolutions();
            for (int j = 0; j < this.populationSize; j++) {
                if(i != j){
                    if(population.get(i).getObjectiveFunctions().get(0) < population.get(j).getObjectiveFunctions().get(0) && 
                            population.get(i).getObjectiveFunctions().get(1) < population.get(j).getObjectiveFunctions().get(1)){
                        population.get(i).addDominatedSolution(population.get(j));
                    }
                }
            }
        }
    }
    
    public void printNonDominatedSet(){
        
    }

}
