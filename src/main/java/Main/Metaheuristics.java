/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import ProblemRepresentation.Solution;
import Algorithms.EvolutionaryAlgorithm;
import InstanceReader.DataOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author renansantos
 */
public class Metaheuristics implements EvolutionaryAlgorithm {

    private List<Solution> population;
    private List<Integer> parents;
    private double mutationProbabilty;
    private double crossOverProbability;
    private long populationSize;
    private long numberOfGenerations;
    private int currentGeneration = 0;
    private int numberOfExecutions = 1;
    private int numberOfChromossomes = 2;
    private Solution bestIndividual = new Solution();
    private DataOutput output;
    DataOutput outputForBestSolutions;

    public Metaheuristics() {
        this.population = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    public Solution getBestIndividual() {
        return this.bestIndividual;
    }

    public List<Solution> getPopulation() {
        return population;
    }

    public double getMutationProbabilty() {
        return mutationProbabilty;
    }

    public double getCrossOverProbability() {
        return crossOverProbability;
    }

    public long getPopulationSize() {
        return populationSize;
    }

    public long getNumberOfGenerations() {
        return numberOfGenerations;
    }

    public List<Integer> getParents() {
        return parents;
    }

    public Metaheuristics setMutationProbabilty(double mutationProbabilty) {
        this.mutationProbabilty = mutationProbabilty;
        return this;
    }

    public Metaheuristics setCrossOverProbability(double crossOverProbability) {
        this.crossOverProbability = crossOverProbability;
        return this;
    }

    public Metaheuristics setPopulationSize(long populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public Metaheuristics setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
        return this;
    }

    public Metaheuristics setNumberOfExecutions(int numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
        return this;
    }

    @Override
    public void storeBestIndividual() {

    }

    @Override
    public void initializePopulation() {
        for (int i = 0; i < this.populationSize; i++) {
            this.population.add(new Solution()
                    .setNumberOfChromossomes(numberOfChromossomes)
                    .buildRandomSolution());
        }
        calculateFitness();
        this.bestIndividual.setSolution(population.get(0));
    }
    
    public void printPopulation() {
        System.out.println();
        this.population.forEach(System.out::println);
    }

    @Override
    public void selection() {
    }

    @Override
    public void crossOver() {
    }

    @Override
    public void mutation() {
    }

    @Override
    public void insertBestIndividual() {
    }

    @Override
    public void initializeFilesToSaveData() {
    }

    @Override
    public boolean stopCriterionIsNotSatisfied() {
        return currentGeneration < numberOfGenerations;
    }

    @Override
    public void printInformations() {
        System.out.println("Current Generation = " + currentGeneration + "\t" + this.bestIndividual);
    }

    @Override
    public void calculateFitness() {
//        double sum = population.stream().mapToDouble(Solution::getObjectiveFunctions).sum();
//        population.forEach(u -> u.setFitness(u.getEvaluationFunction() / sum));
//
//        double max = population.stream().mapToDouble(EvolutionarySolution::getFitness).max().getAsDouble();
//        double min = population.stream().mapToDouble(EvolutionarySolution::getFitness).min().getAsDouble();
//        population.forEach(u -> u.setFitness((max - u.getFitness()) / (max - min)));
//
//        double fitnessSum = population.stream().mapToDouble(EvolutionarySolution::getFitness).sum();
//        population.forEach(u -> u.setFitness(u.getFitness() / fitnessSum));
//        population.sort(Comparator.comparing(EvolutionarySolution::getFitness).reversed());
    }

    @Override
    public void incrementsCurrentIteration() {
        currentGeneration++;
    }

    @Override
    public void saveData() {
    }

}
