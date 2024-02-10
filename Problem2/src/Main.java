import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.csv";
        readAndWriteCSV(fileName);
    }

    public static void readAndWriteCSV(String fileName) {
        try {
            // Read input CSV file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String[]> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                rows.add(line.split(", "));
            }
            reader.close();

            // Evaluate formulas and update values
            evaluateCSV(rows);

            // Write output CSV file
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"));
            for (String[] row : rows) {
                writer.write(String.join(", ", row));
                writer.newLine();
            }
            writer.close();

            System.out.println("Output CSV file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to evaluate CSV cells
    private static void evaluateCSV(List<String[]> rows) {
        for (int i = 0; i < rows.size(); ++i) {
            for (int j = 0; j < rows.get(i).length; ++j) {
                rows.get(i)[j] = evaluateCell(rows.get(i)[j], rows);
            }
        }
    }

    // Method to evaluate individual cell
    private static String evaluateCell(String cell, List<String[]> rows) {
        if (cell.startsWith("=")) {
            // Formula cell
            String formula = cell.substring(1);
            return evaluateFormula(formula, rows);
        } else {
            // Value cell
            return cell;
        }
    }

    // Method to evaluate formula
    private static String evaluateFormula(String formula, List<String[]> rows) {
        // Split formula into parts
        String[] parts = formula.split("\\+");
        int sum = 0;
        for (String part : parts) {
            if (isNumeric(part)) {
                // If part is a numeric value, add it to sum
                sum += Integer.parseInt(part);
            } else {
                // If part is a cell reference, get its value recursively
                String[] coordinates = part.split("(?<=\\D)(?=\\d)");
                int row = Integer.parseInt(coordinates[1]) - 1;
                int col = coordinates[0].charAt(0) - 'A';
                String value = evaluateCell(rows.get(row)[col], rows);
                sum += Integer.parseInt(value);
            }
        }
        return String.valueOf(sum);
    }

    // Method to check if string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}