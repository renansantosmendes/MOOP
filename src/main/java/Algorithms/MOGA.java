/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import ProblemRepresentation.Solution;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author renansantos
 */
public class MOGA extends GeneticAlgorithm {

    public void evaluatePopulation() {
        for (int i = 0; i < this.populationSize; i++) {
            List<Double> objectiveFunctions = new ArrayList<>();
            double f1 = population.get(i).getChromossomes().get(0);
            double g = 1 + 9 * (population.get(i).getChromossomes().stream().mapToDouble(u -> u).sum() - f1)
                    / (population.get(i).getChromossomes().size() - 1);
            double f2 = g * (1 - Math.sqrt((f1 / g)));

            objectiveFunctions.add(f1);
            objectiveFunctions.add(f2);

            this.population.get(i).setObjectiveFunctions(objectiveFunctions);
        }

        this.population.sort(Comparator.comparing(s -> s.getObjectiveFunctions().get(0)));

    }

    public void nonDomination() {
        clearDomination();
        for (int i = 0; i < this.populationSize; i++) {
            //population.get(i).clearDominatedSolutions();
            for (int j = 0; j < this.populationSize; j++) {
                //population.get(j).clearSolutionsThatDominate();
                if (i != j) {
                    if (population.get(i).getObjectiveFunctions().get(0) < population.get(j).getObjectiveFunctions().get(0)
                            && population.get(i).getObjectiveFunctions().get(1) < population.get(j).getObjectiveFunctions().get(1)) {
                        population.get(i).addDominatedSolution(population.get(j));
                        population.get(j).addDominatedBySolution(population.get(i));
                    }
                }
            }
        }
    }

    public void clearDomination(){
        for (int i = 0; i < this.populationSize; i++) {
            population.get(i).clearDominatedSolutions();
            population.get(i).clearSolutionsThatDominate();
        }
        
    }
    public void printNonDominatedSet() {
        population.stream().filter(Solution::isNonDominated).forEach(System.out::println);
    }

}
