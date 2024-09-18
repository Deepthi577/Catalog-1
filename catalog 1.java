import java.util.HashMap;
import java.util.Map;

public class SSS {
    public static void main(String[] args) {
        // Sample JSON input as a string
        String jsonInput = "{ \"keys\": { \"n\": 4, \"k\": 3 }, " +
                           "\"1\": { \"base\": \"10\", \"value\": \"4\" }, " +
                           "\"2\": { \"base\": \"2\", \"value\": \"111\" }, " +
                           "\"3\": { \"base\": \"10\", \"value\": \"12\" }, " +
                           "\"6\": { \"base\": \"4\", \"value\": \"213\" } }";

        
        Map<Integer, Integer> points = new HashMap<>();
        points.put(1, decodeValue("4", 10));
        points.put(2, decodeValue("111", 2));
        points.put(3, decodeValue("12", 10));
        points.put(6, decodeValue("213", 4));

        
        int c = calculateConstantTerm(points);
        System.out.println("The constant term c is: " + c);
    }

    
    private static int decodeValue(String value, int base) {
        return Integer.parseInt(value, base);
    }

   
    private static int calculateConstantTerm(Map<Integer, Integer> points) {
        int c = 0;
        int n = points.size();

        
        for (Map.Entry<Integer, Integer> entry : points.entrySet()) {
            int x_i = entry.getKey();
            int y_i = entry.getValue();
            double term = y_i;

            
            for (Map.Entry<Integer, Integer> innerEntry : points.entrySet()) {
                if (!innerEntry.getKey().equals(x_i)) {
                    term *= (0 - innerEntry.getKey()) / (double)(x_i - innerEntry.getKey());
                }
            }
            c += term; // Accumulate the result
        }

        return (int) c; 
    }
}