import java.util.ArrayList;
import java.util.Random;
import java.io.IOError;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Main {
    static Random generator;
    static String[] names = {};
    static String[] regionAdj = {};
    
    static <T> T pickRandom(T[] array){
        int rnd = generator.nextInt(array.length);
        return array[rnd];
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

        public static int increaseTime(int time){
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
            startingNumbers = generator.nextInt(10);
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            //TODO
        }

        public Region(int start){
            startingNumbers = start;
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            //TODO
        }

        public Region(String name, String type, int start){
            regionName = name;
            regionType = type;
            startingNumbers = start;
            residents = new ArrayList<Creature>();
            createCreature(startingNumbers, residents);
            //TODO
        }

        public static void generateFood(int min, int max){
            //TODO
        }

        public static void createCreature(int num, ArrayList<Creature> c){
            for(int i = 0;  i < num; i++){
                Creature a = new Creature();
                c.add(a);
            }
            //TODO
        }

    }

    static class Creature{
        String name = "default";
        int age = 0;
        int longevity = 10;
        String race = "default";
        boolean alive = true;
        boolean stomach = true;
        int starve = 0;

        public Creature(){
            //TODO
        }

        public Creature(String r){
            race = r;
            //TODO
        }

        public Creature(String r, String n){
            name = n;
            race = r;
            //TODO
        }

        public static int increaseAge(int a){
            return a += 1;
        }

        public static boolean die(){
            boolean willDie = false;
            //TODO
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
        World test = new World();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean cont = true;
        String input = "Q";
        System.out.println("Welcome");
        while(cont){
            test.displayMap(test.map);
            System.out.println("Input Q to quit.");
            input = reader.readLine();
            switch(input.toUpperCase()){
                case "Q":
                System.out.println("Ending World.");
                cont = false;
            }
        }
    }
}
