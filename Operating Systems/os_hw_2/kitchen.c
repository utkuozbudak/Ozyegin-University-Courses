/** O. UTKU ÖZBUDAK, S012136 **/

#include "meal.h"
#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

#define GLOVE_COUNT 3
#define POT_SIZE 3
#define APPRENTICE_COUNT 3
#define MEALS_TO_PREPARE 4
#define REQUIRED_INGREDIENTS 3

struct meal Menu[4] = {
    {"Menemen", {{"Tomato", 3}, {"Onion", 4}, {"Egg", 1}}, 10},
    {"Chicken Pasta", {{"Pasta", 2}, {"Chicken", 5}, {"Curry", 2}}, 8},
    {"Beef Steak", {{"Beef", 7}, {"Pepper", 3}, {"Garlic", 2}}, 13},
    {"Ali Nazik", {{"Eggplant", 4}, {"Lamb Meat", 4}, {"Yoghurt", 1}}, 10}};

int meal_counter = 0;
int meal_ing_counter = 0;

pthread_mutex_t glove_mutex[3];
pthread_mutex_t ing_mutex;
pthread_mutex_t pot_mutex;

int pot=0;

void put_gloves(int apprentice_id){
    printf("Apprentice %d is picking gloves \n", apprentice_id);
    pthread_mutex_lock(&glove_mutex[apprentice_id]);
    pthread_mutex_lock(&glove_mutex[(apprentice_id+1)%GLOVE_COUNT]);
    printf("Apprentice %d has picked gloves\n", apprentice_id);
}

void remove_gloves(int apprentice_id){
    pthread_mutex_unlock(&glove_mutex[apprentice_id]);
    pthread_mutex_unlock(&glove_mutex[(apprentice_id+1)%GLOVE_COUNT]);
    printf("Apprentice %d has removed gloves\n", apprentice_id);
}

void pick_ingredient(int apprentice_id, int *meal_index, int *ing_index){
    while (meal_ing_counter == POT_SIZE);

    pthread_mutex_lock(&ing_mutex);

    put_gloves(apprentice_id);
    *meal_index = meal_counter;
    *ing_index = meal_ing_counter;
    printf("Apprentice %d has taken ingredient %s\n", apprentice_id, Menu[*meal_index].ingredients[*ing_index].name);
    meal_ing_counter = meal_ing_counter + 1;
    remove_gloves(apprentice_id);

    pthread_mutex_unlock(&ing_mutex);
}

void prepare_ingredient(int apprentice_id, int meal_index, int ing_index){
    printf("Apprentice %d is preparing: %s \n", apprentice_id, Menu[meal_index].ingredients[ing_index].name);
    sleep(Menu[meal_index].ingredients[ing_index].time_to_process);
    printf("Apprentice %d is done preparing %s \n", apprentice_id, Menu[meal_index].ingredients[ing_index].name);
}

void put_ingredient(int id, int meal_index, int ing_index){
    pthread_mutex_lock(&pot_mutex);
    while (1){
        printf("Apprentince %d is trying to put %s into pot\n", id, Menu[meal_index].ingredients[ing_index].name);
	if (pot==POT_SIZE) continue;
        pot++;
        printf("Apprentince %d has put %s into pot\n", id, Menu[meal_index].ingredients[ing_index].name);
        break;
    }
    pthread_mutex_unlock(&pot_mutex);
 // pthread_exit(NULL); ALGORITHM DOESN'T WORK IF I DONT DELETE THIS, SO I COMMENTED IT
}

void help_chef(int apprentice_id){
    int meal_index, meal_ingredient_index;
    pick_ingredient(apprentice_id, &meal_index, &meal_ingredient_index);
    prepare_ingredient(apprentice_id, meal_index, meal_ingredient_index);
    put_ingredient(apprentice_id, meal_index, meal_ingredient_index);
}

void *apprentice(int *apprentice_id){
    printf("Im apprentice %d\n", *apprentice_id);
    while (meal_counter != MEALS_TO_PREPARE){
        help_chef(*apprentice_id);
    }
    pthread_exit(NULL);
}

void *chef(){

    while (1){
        pthread_mutex_lock(&pot_mutex);

        if (pot != REQUIRED_INGREDIENTS){
            pthread_mutex_unlock(&pot_mutex);
            continue;
        }
        pot = 0;
        pthread_mutex_unlock(&pot_mutex);

        printf("Chef is preparing meal %s\n", Menu[meal_counter].name);
        sleep(Menu[meal_counter].time_to_prepare);
        printf("Chef prepared the meal %s\n", Menu[meal_counter].name);
        meal_ing_counter = 0;
        meal_counter = meal_counter + 1;
        sleep(3);
        if (meal_counter == MEALS_TO_PREPARE)
            break;
    }
    pthread_exit(NULL);
}

int main(){

    pthread_t apprentice_threads[APPRENTICE_COUNT];
    pthread_t chef_thread;

    int apprentice_ids[APPRENTICE_COUNT] = {0, 1, 2};

    for (int i = 0; i < GLOVE_COUNT; ++i){
        pthread_mutex_init(&glove_mutex[i], NULL);
    }

    pthread_mutex_init(&ing_mutex, NULL);
    pthread_mutex_init(&pot_mutex, NULL);

    for (int i = 0; i < APPRENTICE_COUNT; ++i){
	pthread_create(&(apprentice_threads[i]), NULL, (void *(*)(void*))apprentice, &apprentice_ids[i]);
    }

    pthread_create(&chef_thread, NULL, chef, NULL);

    for (size_t i = 0; i < APPRENTICE_COUNT; i++)
        pthread_join(apprentice_threads[i], NULL);

    pthread_join(chef_thread, NULL);

    return 0;
}
