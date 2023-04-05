import java.util.Random;

public class Main {
    static Random generator;
    static String[] names = {};
    
    static <T> T pickRandom(T[] array){
        int rnd = generator.nextInt(array.length);
        return array[rnd];
    }

    static class World{
        public int time = 0;
        public int size = 0;
        public Region[][] map = new Region[0][0];

        public static int increaseTime(int time){
            //TODO
            return 0;
        }

        public static void displayMap(){
            //TODO
        }
    }
    static class Region{
        int food = 0;
        int foodMin = 0;
        int foodMax = 10;
        Creature[] residents = {};
        int startingNumbers = 0;

        public static void generateFood(int min, int max){
            //TODO
        }

        public static Creature createCreature(int num){
            //TODO
            return null;
        }

    }

    static class Creature{
        String name = "default";
        int age = 0;
        int longevity = 10;
        boolean alive = true;
        boolean stomach = true;
        int starve = 0;

        public static int increaseAge(int a){
            return a += 1;
        }

        public static void die(){
            //TODO
        }

        public static int eat(int starve){
            //TODO
            return 0;
        }

        public static Creature reproduce(Creature parent){
            //TODO
            return null;
        }

    }

    public static void main(){
        generator = new Random();
    }
}
