/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

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
public class GeneticAlgorithm implements EvolutionaryAlgorithm {

    protected List<Solution> population;
    protected List<Integer> parents;
    protected double mutationProbabilty;
    protected double crossOverProbability;
    protected long populationSize;
    protected long numberOfGenerations;
    protected int currentGeneration = 0;
    protected int numberOfExecutions = 1;
    protected int numberOfChromossomes = 2;
    protected double extrapolationParameter = 0;
    protected Solution bestIndividual = new Solution();
    protected DataOutput output;
    protected DataOutput outputForBestSolutions;
    

    public GeneticAlgorithm() {
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

    public GeneticAlgorithm setMutationProbabilty(double mutationProbabilty) {
        this.mutationProbabilty = mutationProbabilty;
        return this;
    }

    public GeneticAlgorithm setCrossOverProbability(double crossOverProbability) {
        this.crossOverProbability = crossOverProbability;
        return this;
    }

    public GeneticAlgorithm setPopulationSize(long populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public GeneticAlgorithm setNumberOfGenerations(int numberOfGenerations) {
        this.numberOfGenerations = numberOfGenerations;
        return this;
    }

    public GeneticAlgorithm setNumberOfExecutions(int numberOfExecutions) {
        this.numberOfExecutions = numberOfExecutions;
        return this;
    }

    public GeneticAlgorithm setExtrapolationParameter(double csi){
        this.extrapolationParameter = csi;
        return this;
    }
    
    @Override
    public void storeBestIndividual() {
        if (bestIndividual.getObjectiveFunctions().get(0) > this.population.get(0).getObjectiveFunctions().get(0)) {
            this.bestIndividual.setSolution(population.get(0));
        }
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

    @Override
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
            double probability = rnd.nextDouble();
            if (probability < this.crossOverProbability) {
                double randomValue = rnd.nextDouble();
//                double randomValue = 0.5;
                double csi = this.extrapolationParameter;
                double u = -csi + (1 + 2*csi)*randomValue;
                
                Solution parent1 = new Solution(this.population.get(i).clone());
                Solution parent2 = new Solution(this.population.get(i + 1).clone());
                List<Double> newChromossomes1 = new ArrayList<>();
                List<Double> newChromossomes2 = new ArrayList<>();
                for (int j = 0; j < this.numberOfChromossomes; j++) {
                    newChromossomes1.add(u * parent1.getChromossomes().get(j) + (1 - u) * parent2.getChromossomes().get(j));
                    newChromossomes2.add(u * parent2.getChromossomes().get(j) + (1 - u) * parent1.getChromossomes().get(j));
                }
                Solution child1 = new Solution();
                Solution child2 = new Solution();

                child1.setChromossomes(newChromossomes1);
                child2.setChromossomes(newChromossomes2);

                child1.evaluateSolution();
                child2.evaluateSolution();

                offspring.add(child1);
                offspring.add(child2);
            } else {
                offspring.add(this.population.get(i).clone());
                offspring.add(this.population.get(i + 1).clone());
            }

        }

        population.clear();
        population.addAll(offspring);
        calculateFitness();
    }

    @Override
    public void mutation() {
        Random rnd = new Random();
        double delta;
        for (int i = 0; i < this.populationSize; i++) {
            double probability = rnd.nextDouble();
            if (probability < this.mutationProbabilty) {
//                delta = 0.001*(8)*rnd.nextGaussian();
                List<Double> chromossomes = this.population.get(i).getChromossomes();
                List<Double> newChromossomes = new ArrayList<>();
                for (Double chromossome : chromossomes) {
                    delta = 0.01 * (this.population.get(i).getMAX_VALUE() - this.population.get(i).getMIN_VALUE()) * rnd.nextGaussian();
                    newChromossomes.add(chromossome + delta);
                }
                this.population.get(i).setChromossomes(newChromossomes);
                this.population.get(i).evaluateSolution();
            }
        }
    }

    @Override
    public void insertBestIndividual() {
        // if (bestIndividual.getObjectiveFunctions().get(0) < this.population.get(0).getObjectiveFunctions().get(0)) {
        this.population.get(this.population.size() - 1).setSolution(bestIndividual);
        //}
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
        population.sort(Comparator.comparing(Solution::getMonoObjectiveFunction));
        
        double sum = population.stream().mapToDouble(s -> s.getObjectiveFunctions().get(0)).sum();
        population.forEach(s -> s.setFitness(s.getObjectiveFunctions().get(0) / sum));

        double max = population.stream().mapToDouble(Solution::getFitness).max().getAsDouble();
        double min = population.stream().mapToDouble(Solution::getFitness).min().getAsDouble();
        population.forEach(u -> u.setFitness((max - u.getFitness()) / (max - min)));

        double fitnessSum = population.stream().mapToDouble(Solution::getFitness).sum();
        population.forEach(u -> u.setFitness(u.getFitness() / fitnessSum));
        //population.sort(Comparator.comparing(Solution::getFitness).reversed());
    }

    @Override
    public void incrementsCurrentIteration() {
        currentGeneration++;
    }

    @Override
    public void saveData() {
    }

}
