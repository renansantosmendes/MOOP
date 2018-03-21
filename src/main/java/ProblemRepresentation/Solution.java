/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProblemRepresentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author renansantos
 */
public class Solution implements Cloneable {

    private List<Double> chromossomes;
    private List<Double> objectiveFunctions;
    private int numberOfChromossomes;
    private double fitness;
    private double MAX_VALUE = 1;
    private double MIN_VALUE = 0;
    private List<Solution> dominatedSolutions = new ArrayList<>();
    private List<Solution> solutionsThatDominate = new ArrayList<>();

    public Solution(List<Double> chromossome, List<Double> objectiveFunction, double fitness) {
        this.chromossomes = new ArrayList<>();
        this.objectiveFunctions = new ArrayList<>();
        this.fitness = 0;
        this.chromossomes.addAll(chromossome);
        this.objectiveFunctions.addAll(objectiveFunction);
        this.fitness = fitness;
    }

    public Solution(Solution solution) {
        this.chromossomes = new ArrayList<>();
        this.objectiveFunctions = new ArrayList<>();
        this.fitness = 0;
        this.chromossomes.addAll(solution.getChromossomes());
        this.objectiveFunctions.addAll(solution.getObjectiveFunctions());
        this.fitness = solution.getFitness();
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
        this.chromossomes.clear();
        this.chromossomes.addAll(chromossomes);
    }

    public List<Double> getObjectiveFunctions() {
        return objectiveFunctions;
    }

    public void setObjectiveFunctions(List<Double> objectiveFunctions) {
        this.objectiveFunctions.clear();
        this.objectiveFunctions.addAll(objectiveFunctions);
    }

    public double getFitness() {
        return fitness;
    }

    public double getMAX_VALUE() {
        return MAX_VALUE;
    }

    public double getMIN_VALUE() {
        return MIN_VALUE;
    }

    public double getMonoObjectiveFunction(){
        return this.objectiveFunctions.get(0);
    }
    
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public Solution setNumberOfChromossomes(int numberOfChromossomes) {
        this.numberOfChromossomes = numberOfChromossomes;
        return this;
    }

    public void setSolution(Solution solution) {
        this.chromossomes.clear();
        this.objectiveFunctions.clear();
        this.chromossomes.addAll(solution.getChromossomes());
        this.objectiveFunctions.addAll(solution.getObjectiveFunctions());
        this.fitness = solution.getFitness();
    }

    public void evaluateSolution() {
//        this.objectiveFunctions.add(chromossomes.get(0) * chromossomes.get(0) + chromossomes.get(1) * chromossomes.get(1));

//        Rastringin Function
        this.objectiveFunctions.clear();
        this.objectiveFunctions.add(4 * chromossomes.get(0) * chromossomes.get(0) + 9 * chromossomes.get(1) * chromossomes.get(1)
                - 10 * Math.cos(2 * Math.PI * chromossomes.get(0)) - 10 * Math.cos(2 * Math.PI * chromossomes.get(1)));

    }

    public Solution buildRandomSolution() {
        Random rnd = new Random();
        for (int i = 0; i < this.numberOfChromossomes; i++) {
            this.chromossomes.add(MIN_VALUE + (MAX_VALUE - MIN_VALUE) * rnd.nextDouble());
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

    public void addDominatedSolution(Solution solution){
        this.dominatedSolutions.add(solution);
    }
    
    public void clearDominatedSolutions(){
        this.dominatedSolutions.clear();
    }
    
//    public boolean isNonDominated(){
//        return this
//    }
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.0000000000");
        return df.format(this.objectiveFunctions.get(0)).replace(",", ".") 
                + "\t" +df.format(this.objectiveFunctions.get(1)).replace(",", ".") 
                + "\t" + df.format(this.fitness).replace(",", ".")
                + "\t" + this.chromossomes;
    }

    @Override
    public Solution clone() {
//        List<Double> newChromossomes = new ArrayList<>();
//        List<Double> newObjectiveFunctions = new ArrayList<>();

        return new Solution(this.chromossomes, this.objectiveFunctions, this.fitness);
    }

}
