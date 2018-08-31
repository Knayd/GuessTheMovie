import java.util.Scanner;
import java.io.File;

public class GuessTheMovie {

    public static void main(String[] args) throws Exception
    {
        int numberOfLines=0;
        String secretMovie="";
        String[] aMovie; //This wild hold the name of the movie splitted by every char
        int strikes=0;
        String wrongLetters="";
        String letter;


        Scanner read = new Scanner(System.in);

        //First load is to get the amount of lines
        File file = new File("movies");
        Scanner filescanner = new Scanner(file);

        while(filescanner.hasNextLine()) //Gets the number of lines in the file
        {
            String line = filescanner.nextLine(); //This is for the while not to go into an infinite loop
            numberOfLines++;
        }
        int randomPick = (int)(Math.random()* numberOfLines+1); //Selects a random number between 1 and the number of lines in the file


        //Second load is to pick a line inside the file

        File file2 = new File("movies");
        Scanner filescanner2 = new Scanner(file);

        int i=1; //This one is to match with the randomPick

        while(filescanner2.hasNextLine()) //This loop is to look for a match with the random number, and get the name of that movie
        {
            String line = filescanner2.nextLine();
            if(i==randomPick)
            {
                secretMovie = line; //Here's where the variable gets the actual name of the movie
                System.out.println(secretMovie);
            }
            i++;
        }

        //User interface
        System.out.println("==========================================");
        System.out.println("GUESS THE MOVIE");

        aMovie = secretMovie.split(""); //This is the arrays that holds the actual name of the movie



        String[] underMovie=new String[secretMovie.length()]; //This is the array that'll hold all the _'s, those would change if the letter is right
        System.out.println("lenght"+secretMovie.length());

        if(secretMovie.contains(" "))
        {
            for(int j=0; j<(secretMovie.length());j++) //if the name does contain blank spaces, all the array will be filled with _ and will ignore the blank spaces
            {
                if(aMovie[j].equals(" ")) //Here checks for every letter to see if it is a blank space
                {
                    underMovie[j] = " ";
                }
                else
                {
                    underMovie[j] = "_";
                }
            }
        }
        else
        {
            for(int j=0; j<(secretMovie.length());j++) //if the name does not contain any blank spaces, all the array will be filled with _'s
            {
                underMovie[j] = "_";
            }
        }

        while(strikes<=10)
        {

            String check="";


            int count=0; //a flag
            System.out.print("Guess: ");
            for (int j = 0; j < (secretMovie.length()); j++) //This is to print the array with the _'s, and will also contain the letters
            {
                System.out.print(underMovie[j]);
            }

            for(int k=0;k<underMovie.length;k++)
            {
                check +=underMovie[k]; //This will make the array with the _'s an string, this is so I can compare it later
            }
            //Checks for winning
            if(check.equals(secretMovie)) //it will check if the guessed result is the same as in the secretMovie
            {
                System.out.println("\nYOU WIN");
                break;
            }//Checks for losing
            else if(strikes==10)
            {
                System.out.println("\nYOU LOSE, the movie was: "+secretMovie);
                break;
            }
            System.out.println("\nYou have guessed (" + strikes + ") wrong letters:" + wrongLetters);

            letter = read.nextLine();

            for (int a = 0; a < secretMovie.length(); a++) {
                if (letter.equals(aMovie[a])) //compares the letter to the entire array who contains the splitted name
                {
                    underMovie[a] = letter; //if the letter matches, it changes the _ by that letter

                } else {
                    count++; //This is so I can know if the letter didn't match any letter
                }

            }
            if (count == aMovie.length && !wrongLetters.contains(letter)) //if it didnt match any letter, this will be true (also makes sure that you dont lose points by entering the same letter)
            {
                wrongLetters += " " + letter;
                strikes += 1;

            }
        }


    }

}
