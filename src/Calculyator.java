import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Calculyator {
    public static void main(String[] input) throws IOException, NumberFormatException, ArrayIndexOutOfBoundsException {

        System.out.println("Введите выражение для вычисления: ");
        cutInput str = new cutInput();
        if (str.cutStrInOper().length > 2){
            throw new IOException("Не более двух операндов!");
        }
        else {
            try {
                if (str.check()) {
                    Roman roman = new Roman();
                    int el1 = roman.toArabic(str.cutStrInOper()[0]);
                    int el2 = roman.toArabic(str.cutStrInOper()[1]);
                    switch (str.findOperation()) {
                        case "+":
                            System.out.println(roman.CountPlus(el1, el2));
                            break;
                        case "-":
                            System.out.println(roman.CountMinus(el1, el2));
                            break;
                        case "*":
                            System.out.println(roman.CountMult(el1, el2));
                            break;
                        case "/":
                            System.out.println(roman.CountDel(el1, el2));
                            break;
                    }
                } else {
                    Arab arab = new Arab();
                    int el1 = Integer.parseInt(str.cutStrInOper()[0]);
                    int el2 = Integer.parseInt(str.cutStrInOper()[1]);
                    if (el1 < 0 || el1 > 10 || el2 < 0 || el2 > 10){
                        throw new IOException ("Введите числа от 0 до 10");
                    }
                    else {
                        switch (str.findOperation()) {
                            case "+":
                                System.out.println(arab.CountPlus(el1, el2));
                                break;
                            case "-":
                                System.out.println(arab.CountMinus(el1, el2));
                                break;
                            case "*":
                                System.out.println(arab.CountMult(el1, el2));
                                break;
                            case "/":
                                System.out.println(arab.CountDel(el1, el2));
                                break;
                        }
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Проверьте введеные данные!");
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Cтрока не является математической операцией ");
            }
        }

    }
}

    class Arab {
        int CountPlus(int a, int b) {
            int res = 0;
            res = a + b;
            return res;
        }
        int CountMinus(int a, int b) {
            int res = 0;
            res = a - b;
            return res;
        }
        int CountDel(int a, int b) {
            int res = 0;
            res = a / b;
            return  (int)Math.floor(res);
        }
        int CountMult(int a, int b) {
            int res = 0;
            res = a * b;
            return res;
        }
    }
    class Roman {
        static int numbers[]  =     {   1,   4,   5,    9,  10,  50, 100};
        static String letters[]  =  { "I", "IV", "V", "IX", "X", "L", "C"};
        public int toArabic(String value){//перевод из римских в арабские
            if(value.equals("I")) return 1;
            if(value.equals("II")) return 2;
            if(value.equals("III")) return 3;
            if(value.equals("IV")) return 4;
            if(value.equals("V")) return 5;
            if(value.equals("VI")) return 6;
            if(value.equals("VII")) return 7;
            if(value.equals("VIII")) return 8;
            if(value.equals("IX")) return 9;
            if(value.equals("X")) return 10;
            return 0;
        }
        public String toRome(int value){//перевод из римских в арабские
            var keys = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            var vals = new int[] { 100, 90, 50, 40, 10, 9, 5, 4, 1 };

            StringBuilder ret = new StringBuilder();
            int ind = 0;

            while(ind < keys.length)
            {
                while(value >= vals[ind])
                {
                    var d = value / vals[ind];
                    value = value % vals[ind];
                    for(int i=0; i<d; i++)
                        ret.append(keys[ind]);
                }
                ind++;
            }

            return ret.toString();
        }

        String CountPlus(int a, int b) {
            int res = 0;
            res = a + b;
            return toRome(res);
        }
        String CountMinus(int a, int b) {
            int res = 0;
            res = a - b;
            if (res<0){
                System.out.println("В римской системе исчисления нет отрицательных чисел, ответ не может быть посчитан!");
            }
            return toRome(res);
        }
        String CountDel(int a, int b) {
            int res = 0;
            res = (int)Math.floor(a / b);
            return toRome(res);
        }
        String CountMult(int a, int b) {
            int res = 0;
            res = a * b ;
            return toRome(res);
        }
    }
    class cutInput {
        Scanner inputStr = new Scanner(System.in);
        String a = inputStr.nextLine().trim();// получаем введеную строку
        String[] oper = {"+", "-", "*", "/"};
        String[] arr1 = {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String[] operArr = a.split("");//массив для нахождения знака действия
        String findOperation() {//находим знак действия
            for (int i = 0; i < operArr.length; i++) {
                String e = operArr[i];
                for (int j = 0; j < oper.length; j++) {
                    String f = oper[j];
                    if (e.equals(f)) {
                        return e;
                    }
                }
            }
            return "0";
        }
        String[] cutStrInOper(){
            switch (this.findOperation()) {
                case "+":
                    String[] arr1 = this.a.split("\\+");
                    return arr1;
                case "*":
                    String[] arr2 = this.a.split("\\*");
                    return arr2;
            }
            String[] arr = this.a.split(this.findOperation());
            return arr;
        }
        boolean check() {//проверка на римские или арабские цифры
            if (Arrays.asList(this.arr1).containsAll(Arrays.asList(this.cutStrInOper())))
            {return true;}
            else {
                return false;
            }
        }


    }








