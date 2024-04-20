import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    static String num1;
    static String num2;
    static char operation;
    static String[] romanNumerals = {
            "nulla", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };


    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String userInput;

        do {
            System.out.print("Введите выражение (или 'exit' для выхода): ");
            userInput = input.nextLine();
           System.out.println(stringFormatting(userInput));
        } while (!userInput.equals("exit"));
        input.close();
    }

    public static String stringFormatting(String input) throws Exception {
        String resInput = input.replaceAll(" ", "");
        String[] operands = resInput.split("[+\\-*/]");
        if(operands.length > 2){
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        char[] array = resInput.toCharArray();
        boolean hasOperation = false;
        for (char c: array){
            if(c == '+' || c == '-' || c == '/' || c == '*'){
                hasOperation = true;
                break;
            }
        }
        if(!hasOperation){
            throw new Exception("Строка не является математической операцией");
        }
        num1 = resInput.substring(0, getIndex(array));
        num2 = resInput.substring(getIndex(array) + 1);
        operation = resInput.charAt(getIndex(array));
        return getResultOperation(getTypesNums(num1, num2), num1, num2, operation);
    }

    public static int getIndex(char[] string) {//получение индекса арифметической операции
        int index = 0;
        for (int i = 0; i < string.length; i++) {
            if (string[i] == ArithmeticOperations.ADDITION.operations
                    || string[i] == ArithmeticOperations.SUBSTRACTION.operations
                    || string[i] == ArithmeticOperations.DIVISION.operations
                    || string[i] == ArithmeticOperations.MULTIPLICATION.operations) {
                index = i;
            }
        }
        return index;
    }

    public static boolean getTypesNums(String num1, String num2) {
        boolean foundNum1 = false;
        boolean foundNum2 = false;

        for (String numeral : romanNumerals) {
            if (num1.equals(numeral)) {
                foundNum1 = true;
            }
            if (num2.equals(numeral)) {
                foundNum2 = true;
            }
        }

        return foundNum1 && foundNum2;
    }

    public static String getResultOperation(boolean typeNum, String num1, String num2, char operation) throws Exception {
        int resultNum1 = 0;
        int resultNum2 = 0;
        int res = 0;
        String result;


        if (typeNum) {
            for (String numerals : romanNumerals) {
                int index = Arrays.asList(romanNumerals).indexOf(numerals);
                if ((num1.equals(numerals) && (index < 1 || index > 10) ||
                        (num2.equals(numerals) && (index < 1 || index > 10)))) {
                    return "Принимаются значения только от I до X";

                }
            }
            for (int j = 0; j < romanNumerals.length; j++) {
                if (num1.equals(romanNumerals[j])) {
                    num1 = String.valueOf(j);
                }
            }
            for (int k = 0; k < romanNumerals.length; k++) {
                if (num2.equals(romanNumerals[k])) {
                    num2 = String.valueOf(k);
                }
            }
            resultNum1 = Integer.parseInt(num1);
            resultNum2 = Integer.parseInt(num2);


            if (operation == ArithmeticOperations.ADDITION.operations) {
                res = resultNum1 + resultNum2;
            } else if (operation == ArithmeticOperations.SUBSTRACTION.operations) {
                res = resultNum1 - resultNum2;
            } else if (operation == ArithmeticOperations.DIVISION.operations) {
                res = resultNum1 / resultNum2;
            } else if (operation == ArithmeticOperations.MULTIPLICATION.operations) {
                res = resultNum1 * resultNum2;
            }
            if (res >= 0 && res < romanNumerals.length) {
                result = romanNumerals[res];
                return result;
            } else {
                throw new Exception("В римской системе нет отрицательных чисел!");
            }
        }

        if (!typeNum) {
            try {
                resultNum1 = Integer.parseInt(num1);//преобразуем строку в целое число
                resultNum2 = Integer.parseInt(num2);//преобразуем строку в целое число
            }catch (NumberFormatException e){
                throw new Exception("Используются одновременно разные системы счисления!");
            }
            if (resultNum1 < 1 || resultNum1 > 10 || resultNum2 < 1 || resultNum2 > 10) {
                return "Принимаются значения только от 1 до 10";

            }
            resultNum1 = Integer.parseInt(num1);//преобразуем строку в целое число
            resultNum2 = Integer.parseInt(num2);//преобразуем строку в целое число
            


            if (operation == ArithmeticOperations.ADDITION.operations) {//проверка знака (+, -, /, *)
                res = resultNum1 + resultNum2;
            } else if (operation == ArithmeticOperations.SUBSTRACTION.operations) {
                res = resultNum1 - resultNum2;
            } else if (operation == ArithmeticOperations.DIVISION.operations) {
                res = resultNum1 / resultNum2;
            } else if (operation == ArithmeticOperations.MULTIPLICATION.operations) {
                res = resultNum1 * resultNum2;
            }
        }
        return String.valueOf(res);
    }
}

