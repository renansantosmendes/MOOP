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

    protected List<Solution> file = new ArrayList<>();

    @Override
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

        //this.population.sort(Comparator.comparing(s -> s.getObjectiveFunctions().get(0)));

    }

    @Override
    public void calculateFitness() {
        evaluatePopulation();
        nonDomination();

        double sum = population.stream().mapToDouble(Solution::getClassification).sum();
        population.forEach(u -> u.setFitness(u.getClassification() / sum));
    }

    public void nonDomination() {
        clearDomination();
        for (int i = 0; i < this.populationSize; i++) {
            for (int j = 0; j < this.populationSize; j++) {
                if (i != j) {
                    if (population.get(i).getObjectiveFunctions().get(0) < population.get(j).getObjectiveFunctions().get(0)
                            && population.get(i).getObjectiveFunctions().get(1) < population.get(j).getObjectiveFunctions().get(1)) {
                        population.get(i).addDominatedSolution(population.get(j));
                        population.get(j).addDominatedBySolution(population.get(i));
                    }
                }
            }
        }
        classifyPopulation();
    }

    public void fileNonDomination() {
        clearDomination();
        for (int i = 0; i < this.populationSize; i++) {
            for (int j = 0; j < this.populationSize; j++) {
                if (i != j) {
                    if (file.get(i).getObjectiveFunctions().get(0) < file.get(j).getObjectiveFunctions().get(0)
                            && file.get(i).getObjectiveFunctions().get(1) < file.get(j).getObjectiveFunctions().get(1)) {
                        file.get(i).addDominatedSolution(file.get(j));
                        file.get(j).addDominatedBySolution(file.get(i));
                    }
                }
            }
        }
        classifyPopulation();
    }

    public void fileClearDomination() {
        for (int i = 0; i < this.populationSize; i++) {
            file.get(i).clearDominatedSolutions();
            file.get(i).clearSolutionsThatDominate();
        }
    }

    public void fileClassifyPopulation() {
        file.forEach(u -> {
            double r = 1 + u.getSolutionsThatDominate().size();
            u.setClassification(r);
        });
        normalizeClassification();
    }

    public void clearDomination() {
        for (int i = 0; i < this.populationSize; i++) {
            population.get(i).clearDominatedSolutions();
            population.get(i).clearSolutionsThatDominate();
        }
    }

    public void classifyPopulation() {
        population.forEach(u -> {
            double r = 1 + u.getSolutionsThatDominate().size();
            u.setClassification(r);
        });
        normalizeClassification();
    }

    public void normalizeClassification() {
        double max = population.stream().mapToDouble(Solution::getClassification).max().getAsDouble();
        double min = population.stream().mapToDouble(Solution::getClassification).min().getAsDouble();
        population.forEach(u -> u.setClassification((max - u.getClassification()) / (max - min)));
    }

    public void fileNormalizeClassification() {
        double max = file.stream().mapToDouble(Solution::getClassification).max().getAsDouble();
        double min = file.stream().mapToDouble(Solution::getClassification).min().getAsDouble();
        file.forEach(u -> u.setClassification((max - u.getClassification()) / (max - min)));
    }

    public void printNonDominatedSet() {
        population.stream().filter(Solution::isNonDominated).forEach(System.out::println);
    }
    
    public void printFileNonDominatedSet() {
        file.stream().filter(Solution::isNonDominated).forEach(System.out::println);
    }

    @Override
    public void storeBestIndividual() {
        actualizeFile();
    }
    
     @Override
    public void insertBestIndividual() {
       // this.population.get(this.population.size() - 1).setSolution(bestIndividual);
    }

    public void actualizeFile() {
        this.file.addAll(this.population);
        fileNonDomination();
        List<Solution> nonDominatedSolutions = new ArrayList<>();
        nonDominatedSolutions.addAll(file.stream()
                .filter(Solution::isNonDominated)
                .collect(Collectors.toCollection(ArrayList::new)));
//        System.out.println("non-dominated file = ");
//        nonDominatedSolutions.forEach(System.out::println);
    }
}
