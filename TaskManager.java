import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TaskManager {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();


        // LOAD SAVED TASKS FROM FILE
        try {

            File file = new File("tasks.txt");

            if(file.exists()) {

                Scanner fileReader = new Scanner(file);

                while(fileReader.hasNextLine()) {
                    tasks.add(fileReader.nextLine());
                }

                fileReader.close();
            }

        }

        catch(Exception e) {
            System.out.println("Error loading tasks");
        }


        int choice;

        do {

            System.out.println("\n\nTask Manager: Main Menu\n");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1:

                    System.out.print("Enter task: ");
                    String task = sc.nextLine();

                    tasks.add(task);

                    System.out.println("Task Added!");
                    break;


                case 2:

                    for(int i=0;i<tasks.size();i++){

                        System.out.println(
                                (i+1)+". "+tasks.get(i)
                        );

                    }

                    break;


                case 3:

                    System.out.print("Enter task number: ");
                    int completed = sc.nextInt();

                    if(completed>0 && completed<=tasks.size()) {

                        String oldTask =
                                tasks.get(completed-1);

                        if(!oldTask.startsWith("✔ ")) {

                            tasks.set(
                                    completed-1,
                                    "✔ " + oldTask
                            );
                        }

                        System.out.println(
                                "Task marked complete!"
                        );

                    }

                    else{
                        System.out.println(
                                "Invalid task number"
                        );
                    }

                    break;


                case 4:

                    System.out.print(
                            "Enter task number to delete: "
                    );

                    int num=sc.nextInt();

                    if(num>0 && num<=tasks.size()) {

                        tasks.remove(num-1);

                        System.out.println(
                                "Task Deleted!"
                        );

                    }

                    else{
                        System.out.println(
                                "Invalid task number"
                        );
                    }

                    break;


                case 5:

                    // SAVE TASKS BEFORE EXITING
                    try {

                        FileWriter writer =
                                new FileWriter("tasks.txt");

                        for(String t : tasks){

                            writer.write(t+"\n");

                        }

                        writer.close();

                    }

                    catch(Exception e){

                        System.out.println(
                                "Error saving tasks"
                        );

                    }

                    System.out.println(
                            "Exiting... Byee!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice"
                    );
            }

        } while(choice!=5);

        sc.close();
    }
}