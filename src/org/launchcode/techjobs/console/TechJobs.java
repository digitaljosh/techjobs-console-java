package org.launchcode.techjobs.console;

import java.util.*;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        // Creates 2 local variables, columnChoices & actionChoices

        // Initialize our field map with key/value pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // "while (true)" loops forever and
        // allow user to search until they manually quit

        while (true) {

            // this displays the two actionChoices to user
            // 0 - Search
            // 1 - List
            // how does this group it in the way listed above?
            // the code below calls getUserSelection function, displays actionChoices
            // and stores user selection in actionChoice
            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            // if user selects "1 - List":
            if (actionChoice.equals("list")) {

                // the code below calls getUserSelection function, displays columnChoices
                // and stores user selection in columnChoice
                String columnChoice = getUserSelection("List", columnChoices);
                //   List:
                // 0 - All
                // 1 - Position Type
                // 2 - Employer
                // 3 - Location
                // 4 - Skill

                // user selects "0 - All"
                if (columnChoice.equals("all")) {
                    // printJobs function (below) is called
                    // returns "printJobs is not implemented yet"
                    printJobs(JobData.findAll()); // *** I think this gets allJobs data which is key/value

                } else {
                    // accesses JobData.java file, calls findAll function with "columnChoice" as parameter
                    ArrayList<String> results = JobData.findAll(columnChoice);

                    // Example: Prints out: *** All Location Values ***
                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Prints list of skills, locations, employers, etc under *** All .... Values ***
                    Collections.sort(results);
                    for (String item : results) {


                        System.out.println(item);
                    }
                }

            } else { // if user selection is "search"

                // the code below calls getUserSelection function, displays:
                // Search by:
                // 0 - All
                // 1 - Position Type
                // 2 - Employer
                // 3 - Location
                // 4 - Skill
                String searchField = getUserSelection("Search by:", columnChoices);


                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {
                    printJobs(JobData.findByValue(searchTerm));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
            }
        }
    }
}

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while (!validChoice);

        return choiceKeys[choiceIdx];
    }

    // broken.....Should print a list of jobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        //this needs to iterate over

        // should print:
        // position type: Data Scientist / Business Intelligence
        // name: Sr. IT Analyst (Data/BI)
        // employer: Bull Moose Industries
        // location: Saint Louis
        // core competency: Statistical Analysis

        // if no results, print appropriate message

        // To do this, you'll need to iterate over an ArrayList of jobs.
        // Each job is itself a HashMap. While you can get each of the
        // items out of the HashMap using the known keys
        // ("employer", "location", etc), think instead about creating
        // a nested loop to loop over each HashMap

        // 1. iterate over ArrayList 'allJobs'

        if (someJobs.isEmpty()) {
            System.out.print("\n" + "Error! Search term not found. Please refine search." + "\n");
        }

        for (int i = 0; i < someJobs.size(); i++) {
            for (Map.Entry<String, String> record : someJobs.get(i).entrySet()) {
                String key = record.getKey();
                String value = record.getValue();
                Integer counter = key.length();
                if (counter == 13) {
                    System.out.println("*****");
                    System.out.println(key + ": " + value);
                }
                else if (counter == 15) {
                    System.out.println(key + ": " + value);
                    System.out.println("*****");
                    System.out.println("\n");
                } else {
                    System.out.println(key + ": " + value);
                }
            }
        }
    }
}


        //System.out.println("printJobs is not implemented yet");
    //}
//}
