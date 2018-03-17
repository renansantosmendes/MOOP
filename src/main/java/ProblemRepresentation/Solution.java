/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author renansantos
 */
public class Solution {

    private List<Double> chromossomes;
    private List<Double> objectiveFunctions;
    private int numberOfChromossomes;
    private double fitness;

    public Solution(List<Double> chromossome, List<Double> objectiveFunction, double fitness) {
        this.chromossomes.addAll(chromossome);
        this.objectiveFunctions.addAll(objectiveFunction);
        this.fitness = fitness;
    }

    public Solution() {
        this.chromossomes = new ArrayList<>();
        this.objectiveFunctions = new ArrayList<>();
        this.fitness = 0;
    }

    public List<Double> getChromossomes() {
        return chromossomes;
    }

    public void setChromossomes(List<Double> chromossomes) {
        this.chromossomes = chromossomes;
    }

    public List<Double> getObjectiveFunctions() {
        return objectiveFunctions;
    }

    public void setObjectiveFunctions(List<Double> objectiveFunctions) {
        this.objectiveFunctions = objectiveFunctions;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Solution setNumberOfChromossomes(int numberOfChromossomes) {
        this.numberOfChromossomes = numberOfChromossomes;
        return this;
    }

    public void setSolution(Solution solution) {
        this.chromossomes.addAll(solution.getChromossomes());
        this.objectiveFunctions.addAll(solution.getObjectiveFunctions());
        this.fitness = solution.getFitness();
    }

    public void evaluateSolution() {
        this.objectiveFunctions.add(chromossomes.get(0) * chromossomes.get(0) + chromossomes.get(1) * chromossomes.get(1));
    }

    public Solution buildRandomSolution() {
        Random rnd = new Random();
        for (int i = 0; i < this.numberOfChromossomes; i++) {
            this.chromossomes.add(rnd.nextDouble());
        }
        Solution solution = new Solution();
        solution.setChromossomes(chromossomes);
        solution.evaluateSolution();
        return solution;
    }

    public Solution buildRandomSolution(int seed) {
        Random rnd = new Random(seed);
        for (int i = 0; i < this.numberOfChromossomes; i++) {
            this.chromossomes.add(rnd.nextDouble());
        }
        Solution solution = new Solution();
        solution.setChromossomes(chromossomes);
        solution.evaluateSolution();
        return solution;
    }
    
    @Override
    public String toString(){
        return this.objectiveFunctions + "\t"+ this.fitness + "\t" + this.chromossomes;
    }

}
