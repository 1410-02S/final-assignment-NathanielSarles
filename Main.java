import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Main {
    //TODO Implement time. As in let the world and everything in it age

    static Random generator;
    // Creating all the arrays to pick things randomly latter
    static ArrayList<String> names = new ArrayList<String>();
    static String[] regionTypes = {"Grassland","Desert","Forest","Swamp"};
    static String[] regionAdj = {"Emerald", "Crimson", "Adamantium",
"Thorny", "Damp", "Magic"};
    static String races[] = {"Human", "Elf", "Dwarf"};
    
    /**
     * I eventually want to make Region Type and Race subclasses 
     * of Region and Creature, but I don't really have enough
     * complexity to really justify creating them to myself. 
     * As it stands race doesn't really do much yet.
     * For instance I eventually want to make each region type
     * have a list of creatures that can inhabit it and possibly
     * have a way for creatures to move from region to region.
     * So if a creature wanted to move to a different region it
     * would first check if it could even live there by seeing if
     * it's race was in the list. Stuff like that.
     * */

    static <T> T pickRandom(T[] array){
        int rnd = generator.nextInt(array.length);
        return array[rnd];
    }
    static <T> T pickRandom(ArrayList<T> array){
        int rnd = generator.nextInt(array.size());
        return array.get(rnd);
    }

    static class World{
        public int time = 0;
        public int size = 0;
        public Region[][] map;

        public World(){
            int x = generator.nextInt(4) + 2;
            int y = generator.nextInt(4) + 2;
            size = x * y;
            map = new Region[x][y];
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    map[i][j] = new Region();
                }
            }
        }

        public World(int x, int y){
            map = new Region[x][y];
            size = x*y;
            for(int i = 0; i < x; i++){
                for(int j = 0; j < y; j++){
                    map[i][j] = new Region();
                }
            }
        }

        public World(Region[][] m){
            map = m;
            size = map.length * map[0].length;
        }

        public static int increaseTime(int time, Region[][] m){
            //TODO Increase Age of Residents of all Regions with Time?
            //Maybe do it in some other fashion? Decide later
            return time+=1;
        }

        public void displayMap(Region[][] map){
            for(int i = 0; i < map.length ; i++){
                for(int j = 0; j < map[0].length; j++){
                    System.out.print(" "+ map[i][j].regionSymbol);
                }
                System.out.println();
            }
        }
        public void displayRegion(Region r){
            System.out.println(r.regionName);
            System.out.println("The current number of residents is "
            + r.residents.size());
            System.out.println("Residents:");
            for(int i = 0; i < r.residents.size(); i++){
                System.out.println("    " + r.residents.get(i).name 
                + " the " + r.residents.get(i).race);
            }
        }
    }
    static class Region{
        int food = 0;
        int foodMin = 0;
        int foodMax = 10;
        String regionType = "Default";
        String regionSymbol = "0";
        String regionName = "Default";
        ArrayList<Creature> residents;
        int startingNumbers = 0;

        public Region(){
            regionType = pickRandom(regionTypes);
            regionName = "The "+pickRandom(regionAdj)+ " "+ regionType;
            startingNumbers = generator.nextInt(10);
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            switch (regionType.toLowerCase()){
                case "grassland":
                regionSymbol = "G";
                foodMin = 5;
                foodMax = 15;
                break;
                case "desert":
                regionSymbol = "D";
                foodMin = 0;
                foodMax = 10;
                break;
                case "forest":
                regionSymbol = "F";
                foodMin = 5;
                foodMax = 20;
                break;
                case "swamp":
                regionSymbol = "S";
                foodMin = 5;
                foodMax = 15;
                break;
            }
            food = generateFood(foodMin, foodMax);
        }

        public Region(int start){
            regionType = pickRandom(regionTypes);
            regionName = "The "+pickRandom(regionAdj)+ " "+ regionType;
            startingNumbers = start;
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            switch (regionType.toLowerCase()){
                case "grassland":
                regionSymbol = "G";
                foodMin = 5;
                foodMax = 15;
                break;
                case "desert":
                regionSymbol = "D";
                foodMin = 0;
                foodMax = 10;
                break;
                case "forest":
                regionSymbol = "F";
                foodMin = 5;
                foodMax = 20;
                break;
                case "swamp":
                regionSymbol = "S";
                foodMin = 5;
                foodMax = 15;
                break;
            }
            food = generateFood(foodMin, foodMax);
        }

        public Region(String name, String type, int start){
            regionName = name;
            regionType = type;
            startingNumbers = start;
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            switch (regionType.toLowerCase()){
                case "grassland":
                regionSymbol = "G";
                foodMin = 5;
                foodMax = 15;
                break;
                case "desert":
                regionSymbol = "D";
                foodMin = 0;
                foodMax = 10;
                break;
                case "forest":
                regionSymbol = "F";
                foodMin = 5;
                foodMax = 20;
                break;
                case "swamp":
                regionSymbol = "S";
                foodMin = 5;
                foodMax = 15;
                break;
            }
            food = generateFood(foodMin, foodMax);
        }

        public static int generateFood(int min, int max){
            int newFood = generator.nextInt(max-min+1)+min;
            return newFood;
        }

        public static void createCreature(int num, ArrayList<Creature> c){
            for(int i = 0;  i < num; i++){
                Creature a = new Creature();
                c.add(0,a);
            }
        }

    }

    static class Creature{
        String name = "default";
        String race = "default";
        int age = 0;
        int longevity = 10;
        boolean alive = true;
        boolean stomach = true;
        int starve = 0;

        public Creature(){
            name = pickRandom(names);
            race = pickRandom(races);
            switch(race.toLowerCase()){
                case "human":
                break;
                case "elf": 
                longevity = 20;
                break;
                case "dwarf":
                longevity = 15;

            }
        }

        public Creature(String r){
            name = pickRandom(names);
            race = r;
            switch(race.toLowerCase()){
                case "human":
                break;
                case "elf": 
                longevity = 20;
                break;
                case "dwarf":
                longevity = 15;

            }
        }

        public Creature(String r, String n){
            name = n;
            race = r;
            switch(race.toLowerCase()){
                case "human":
                break;
                case "elf": 
                longevity = 20;
                break;
                case "dwarf":
                longevity = 15;

            }
        }

        public static int increaseAge(int a){
            return a += 1;
        }

        public static boolean die(int longevity, int age, int starve){
            boolean willDie = false;
            if(age == longevity){
                willDie = true;
            }
            if (starve == 3){
                willDie = true;
            }
            return willDie;
        }

        public static int eat(int starve){
            //TODO
            return 0;
        }

        public static Creature reproduce(Creature parent){
            Creature child = new Creature(parent.race);
            return child;
        }

    }

    public static void main(String[] args) throws IOException{
        generator = new Random();
        Scanner in = null;
        try {
            in = new Scanner(new BufferedReader(new FileReader("name.txt")));
            in.useLocale(Locale.US);
            in.useDelimiter("\",\"");
            while(in.hasNext()){
                names.add(in.next());
            }

        } finally{
            in.close();
        }

        World test = new World();
        int temp = 0;
        Region t;
        int x = 0;
        int y = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean cont = true;
        String input = "Q";
        System.out.println("Welcome");
        while(cont){
            System.out.println();
            test.displayMap(test.map);
            System.out.println();
            System.out.println("To look at a specfic region more closely input R."
            + " To quit input Q.");
            input = reader.readLine();
            switch(input.toUpperCase()){
                case "Q":
                System.out.println("Ending World.");
                cont = false;
                break;
                case "R":
                System.out.println("Please input the coordinates "
                + "of the region you want to look at starting in"
                + " format row column.(ie 11 would be the top left"
                + " region)");
                input = reader.readLine();
                for(int i = 0; i < 2; i++){
                    switch(input.charAt(i)){
                        case '1':
                        temp = 0;
                        break;
                        case '2':
                        temp = 1;
                        break;
                        case '3':
                        temp = 2;
                        break;
                        case '4':
                        temp = 3;
                        break;
                        case '5':
                        temp = 4;
                        break;
                        default:
                        temp = -1;
                        System.out.println("Invalid Input.");
                        break;
                    }
                    if (temp == -1){
                        break;
                    }
                    if(i == 0){
                        x = temp;
                    } else {
                        y = temp;
                    }
                }
                if (temp != -1){
                    t = test.map[x][y];
                    test.displayRegion(t);
                    break;
               }
            }
        }
        reader.close();
    }
}

