/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author renansantos
 */
public interface EvolutionaryAlgorithm {

    public void storeBestIndividual();

    public void initializePopulation();
    
    public void evaluatePopulation();

    public void selection();

    public void crossOver();

    public void mutation();

    public void insertBestIndividual();

    public void initializeFilesToSaveData();

    public boolean stopCriterionIsNotSatisfied();

    public void printInformations();

    public void printPopulation();

    public void calculateFitness();

    public void incrementsCurrentIteration();

    public void saveData();

    default void execute() {
        initializeFilesToSaveData();
        initializePopulation();
        printPopulation();
        while (stopCriterionIsNotSatisfied()) {
//            printPopulation();
            printInformations();
            calculateFitness();
            insertBestIndividual();
            selection();
            crossOver();
            mutation();
            calculateFitness();
            storeBestIndividual();
            incrementsCurrentIteration();
            saveData();
        }
    }
}
