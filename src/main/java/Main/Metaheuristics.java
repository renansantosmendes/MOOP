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
import java.util.Random;

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
        Random rnd = new Random();
        double cursor;
        double currentSum;
        this.parents.clear();
        for (int i = 0; i < this.populationSize; i++) {
            currentSum = 0;
            cursor = rnd.nextDouble() / 2;
            findCursorPosition(currentSum, cursor, rnd);
        }
    }

    private void findCursorPosition(double currentSum, double cursor, Random rnd) {
        int position = -1;
        for (int j = 0; j < population.size(); j++) {
            currentSum += population.get(j).getFitness();
            if (cursor <= currentSum) {
                position = j;
                this.parents.add(position);
                break;
            }
        }
        if (position == -1) {
            position = rnd.nextInt(population.size());
            this.parents.add(position);
        }
    }

    @Override
    public void crossOver() {
        Random rnd = new Random();
        List<Solution> offspring = new ArrayList<>();
        for (int i = 0; i < this.population.size(); i = i + 2) {
            double delta = rnd.nextDouble();
            Solution parent1 = new Solution(this.population.get(i).clone());
            Solution parent2 = new Solution(this.population.get(i + 1).clone());
            List<Double> newChromossomes1 = new ArrayList<>();
            List<Double> newChromossomes2 = new ArrayList<>();
            for (int j = 0; j < this.numberOfChromossomes; j++) {
                newChromossomes1.add(delta * parent1.getChromossomes().get(j) + (1 - delta) * parent2.getChromossomes().get(j));
                newChromossomes2.add(delta * parent2.getChromossomes().get(j) + (1 - delta) * parent1.getChromossomes().get(j));
            }
            Solution child1 = new Solution();
            Solution child2 = new Solution();
            
            child1.setChromossomes(newChromossomes1);
            child2.setChromossomes(newChromossomes2);
            
            child1.evaluateSolution();
            child2.evaluateSolution();
            
            offspring.add(child1);
            offspring.add(child2);
        }

        population.clear();
        population.addAll(offspring);
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
        double sum = population.stream().mapToDouble(s -> s.getObjectiveFunctions().get(0)).sum();
        population.forEach(s -> s.setFitness(s.getObjectiveFunctions().get(0) / sum));

        double max = population.stream().mapToDouble(Solution::getFitness).max().getAsDouble();
        double min = population.stream().mapToDouble(Solution::getFitness).min().getAsDouble();
        population.forEach(u -> u.setFitness((max - u.getFitness()) / (max - min)));

        double fitnessSum = population.stream().mapToDouble(Solution::getFitness).sum();
        population.forEach(u -> u.setFitness(u.getFitness() / fitnessSum));
        population.sort(Comparator.comparing(Solution::getFitness).reversed());
    }

    @Override
    public void incrementsCurrentIteration() {
        currentGeneration++;
    }

    @Override
    public void saveData() {
    }

}
