import java.util.Scanner;

public class  Calculator {
    static int operand1;
    static int operand2;
    static String operator;
    static String result;
    static boolean searchRoman;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String input = scanner.nextLine();

        System.out.println(operationVariables(input));
    }

    public static String operationVariables(String input) throws Exception {


        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2)
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        operator = operatorSearch(input);

        if (Roman.searchRoman(operands[0]) && Roman.searchRoman(operands[1])) {
            operand1 = Roman.convertToArabian(operands[0]);
            operand2 = Roman.convertToArabian(operands[1]);
            searchRoman = true;
        } else if (!Roman.searchRoman(operands[0]) && !Roman.searchRoman(operands[1])) {
            operand1 = Integer.parseInt(operands[0]);
            operand2 = Integer.parseInt(operands[1]);
            searchRoman = false;
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
        if (operand1 > 10 || operand2 > 10 || operand1 < 1 || operand2 < 1) {
            throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно, не более");
        }

        int arabian = Integer.parseInt(calc(input));
        if (searchRoman) {
            if (arabian <= 0) {
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String operatorSearch(String input) {
        if (input.contains("+")) return "+";
        else if (input.contains("-")) return "-";
        else if (input.contains("*")) return "*";
        else if (input.contains("/")) return "/";
        else return null;
    }

    public static String calc(String input) {

        if (operator.equals("+")) return String.valueOf(operand1 + operand2);
        else if (operator.equals("-")) return String.valueOf(operand1 - operand2);
        else if (operator.equals("*")) return String.valueOf(operand1 * operand2);
        else return String.valueOf(operand1 / operand2);

    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean searchRoman(String num) {
        for (int i = 0; i < romanArray.length; i++) {
            if (num.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String romanNum) {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanNum.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {

        return romanArray[arabian];
    }

}

