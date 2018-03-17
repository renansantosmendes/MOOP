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

    public void run();

    public void storeBestIndividual();

    public void initializePopulation();

    public void selection();

    public void crossOver();

    public void mutation();

    public void insertBestIndividual();

    public void initializeFilesToSaveData();

    public boolean stopCriterionIsNotSatisfied();
    
    public void printInformations();
    
    public void calculateFitness();
    
    public void incrementsCurrentIteration();
    
    public void saveData();

    default void GeneticAlgorithm() {
        initializeFilesToSaveData();
        initializePopulation();
        while (stopCriterionIsNotSatisfied()) {
            printInformations();
            calculateFitness();
            storeBestIndividual();
            selection();
            crossOver();
            mutation();
            insertBestIndividual();
            incrementsCurrentIteration();
            saveData();
        }
    }
}
