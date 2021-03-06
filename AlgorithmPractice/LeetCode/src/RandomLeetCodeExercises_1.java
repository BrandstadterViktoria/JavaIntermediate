
import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.regex.Pattern;


public class RandomLeetCodeExercises_1 {

    public static void main(String[] args) {
        System.out.println(canConstruct("fffbfg", "effjfggbffjdgbjjhhdegh"));

    }

    public static int singleNumber_LC136(int[] numbers1) {
        HashMap<Integer, Integer> mapOfNumbers1 = new HashMap<>();
        for (int i = 0; i <= numbers1.length - 1; i++) {
            mapOfNumbers1.put(numbers1[i], (mapOfNumbers1.containsKey(numbers1[i]) ? 0 : 1));
        }
        for (int key : mapOfNumbers1.keySet()) {
            int value = mapOfNumbers1.get(key);
            if (value == 1) {
                return key;
            }
        }

        return -1;
    }

    public static List<Integer> findDuplicates_LC442(int[] nums) {
        List<Integer> duplicates = new ArrayList<>();
        Arrays.sort(nums);
        int current;
        int previous;
        if (nums.length < 2) {
            return duplicates;
        }
        for (int i = 0; i < nums.length; i++) {
            previous = nums[i == 0 ? 0 : i - 1];
            current = nums[i == 0 ? i + 1 : i];
            if (previous == current) {
                if (!duplicates.contains(nums[i])) {
                    duplicates.add(nums[i]);
                }
            }
            if (i == nums.length - 1 && (nums[nums.length - 1] == nums[nums.length - 2])) {
                if (!duplicates.contains(nums[i])) {
                    duplicates.add(nums[i]);
                }
            }

        }
        return duplicates;
    }

    public static int[] maxSumOfThreeSubarrays_LC689(int[] nums, int k) {
        /*Input: [1,2,1,2,6,7,5,1], k= 2
        4, 3, 2, 7, 8, 2, 3, 1
          Output: [0, 3, 5]*/
        TreeMap<Integer, Integer> sumsAndIndexnumbers = new TreeMap<>();
        TreeMap<Integer, Integer> duplicates = new TreeMap<>();
        int sumOfSubarrays = 0;
        for (int i = 0, index = 0; i < nums.length; i++, index++) {
            sumOfSubarrays += nums[i];
            if ((i + 1) % k == 0) {
                sumsAndIndexnumbers.put(sumOfSubarrays, (i + 1 - k));
                if (sumsAndIndexnumbers.size() > 3 && sumOfSubarrays == sumsAndIndexnumbers.firstKey()) {
                    sumsAndIndexnumbers.remove(sumsAndIndexnumbers.firstKey());
                }
                sumOfSubarrays = 0;
            }
        }
        int[] indexNumbers = sumsAndIndexnumbers.values().stream().mapToInt(i -> i).toArray();
        Arrays.sort(indexNumbers);
        return indexNumbers;
    }

    public int numJewelsInStones(String J, String S) {
        HashMap<Integer, Character> jewels = new HashMap<>();
        char[] jewelLetters = J.toCharArray();
        char[] stoneLetters = S.toCharArray();
        int counter = 0;
        for (int i = 0; i < jewelLetters.length; i++) {
            jewels.put(i, jewelLetters[i]);
        }
        for (int i = 0; i < stoneLetters.length; i++) {
            if (jewels.containsValue(stoneLetters[i])) {
                counter++;
            }
        }
        return counter;
    }

    public static boolean detectCapitalUse(String word) {
        return word.matches("([A-Z][a-z]*\\s*)") || word.matches("[A-Z ]+") || word.matches("[a-z]+");
    }

    public static int arrayPairSum(int[] nums) {
        int min;
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i += 1)
            if ((i + 1) % 2 == 0) {
                min = Math.min(nums[i], nums[i - 1]);
                sum += min;
            }
        return sum;
    }

    public static String[] findRelativeRanks_LC506(int[] nums) {
        /*Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
        who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".*/

        String[] ranks = new String[nums.length];
        Arrays.sort(nums);
        for (int i = 0, rankPosition = 0; i <= nums.length - 1; i++, rankPosition++) {
            if (i == 0) {
                ranks[rankPosition] = "Gold Medal";
            } else if (i == 1) {
                ranks[rankPosition] = "Silver Medal";
            } else if (i == 2) {
                ranks[rankPosition] = "Bronze Medal";
            } else {
                ranks[rankPosition] = String.valueOf(nums[i]);
            }
        }
        return ranks;
    }

    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    public static int countPrimeSetBits_762LC(int L, int R) {
        int countPrimeSetBits = 0;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 50; i++) {
            int counter = 0;
            for (int j = i; j >= 1; j--) {
                if (i % j == 0) {
                    counter++;
                }
            }
            if (counter == 2) {
                primes.add(i);
            }
        }

        for (int i = L; i <= R; i++) {
            if (primes.contains(Integer.bitCount(i))) {
                countPrimeSetBits++;
            }
        }
        return countPrimeSetBits;
    }

    public static boolean hasAlternatingBits_693LC(int n) {
        if (n == 1) {
            return true;
        }
        boolean hasAlternatingBits = false;
        for (int i = 0; i <= Integer.toBinaryString(n).toCharArray().length - 2; i++) {
            if (Integer.toBinaryString(n).toCharArray()[i + 1] != Integer.toBinaryString(n).toCharArray()[i]) {
                hasAlternatingBits = true;
                continue;

            } else {
                hasAlternatingBits = false;
                break;
            }
        }
        return hasAlternatingBits;
    }

    public static int findComplement(int num) {
        return ~num + (Integer.highestOneBit(num) << 1);
    }

    public static String addBinary(String a, String b) {
        int aBinary = a.length() - 1;
        int bBinary = b.length() - 1;
        int carry = 0;
        int sumOfBits;
        StringBuilder result = new StringBuilder();
        while (aBinary >= 0 || bBinary >= 0 || carry == 1) {
            sumOfBits = (a.charAt(aBinary) - '0') + (b.charAt(bBinary) - '0') + carry;
            if (sumOfBits <= 1) {
                result.append(a.toCharArray()[aBinary] == '0' && b.toCharArray()[bBinary] == '0' && carry == 0 ? '0' : '1');
                carry = 0;
            }
            if (sumOfBits > 1) {
                carry = 0;
                result.append('0');
                carry++;
            }
            aBinary--;
            bBinary--;
        }
        return result.reverse().toString();

    }

    public static String toHexNaturalNumbers(int num) {
        StringBuilder hexaDecimalNumber = new StringBuilder();
        HashMap<Integer, Character> hexaLetters = new HashMap<>();
        for (int i = 10, alphabet = 0; i <= 15; i++, alphabet++) {
            hexaLetters.put(i, ((char) ('a' + alphabet)));
        }
        int remainder = 1;
        while (remainder != 0) {
            remainder = num % 16;
            hexaDecimalNumber = ((remainder >= 10) && (remainder <= 15)) ? (hexaDecimalNumber.append(hexaLetters.get(remainder))) : (hexaDecimalNumber.append(remainder));
            num = num / 16;
            if ((num / 16) < 1) {
                hexaDecimalNumber.append(num);
                break;
            }
        }
        hexaDecimalNumber.reverse();
        return hexaDecimalNumber.toString();
    }

    public static int repeatedStringMatch(String A, String B) {
//        For example, with A = "abcd" and B = "cdabcdab".
        StringBuilder sb = new StringBuilder(A);
        int counter = 1;
        HashMap<String, Integer> mapStringA = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            while (i + B.length() > sb.length()) {
                sb.append(A);
                counter++;
            }

            if (!mapStringA.containsKey(sb.toString().substring(i, i + B.length())))
                mapStringA.put(sb.toString().substring(i, i + B.length()), counter);
        }
        return mapStringA.getOrDefault(B, -1);
    }

    public static List<Integer> selfDividingNumbers_LC728(int left, int right) {
        /*A self-dividing number is a number that is divisible by every digit it contains.
         * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible. */
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int counter = 0;
            for (char digit : ("" + i).toCharArray()) {
                if (digit != '0' && i % Character.getNumericValue(digit) == 0) {
                    counter++;
                }
                if (counter == ("" + i).toCharArray().length) {
                    result.add(i);
                } else if (digit != '0' && i % Character.getNumericValue(digit) != 0) {
                    break;
                }

            }
        }
        return result;

    }

    public static int findMaxConsecutiveOnes_LC485(int[] nums) {
        /*Given a binary array, find the maximum number of consecutive 1s in this array.*/
        int counter = 0;
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                counter++;
            } else if (nums[i] == 0) {
                counter = 0;
            }
            if (temp < counter) {
                temp = counter;
            }

        }
        return temp;

    }

    public static String[] findRestaurant_LC599(String[] list1, String[] list2) {
        /*Find out common interest between list of Strings, with the least list index sum. If there is a choice tie between answers,
         output all of them with no order requirement. You could assume there always exists an answer. */
        List<String> answer = new ArrayList<>();
        int indexSum;
        int smallerIndex = (list1.length - 1) + (list2.length - 1);
        HashMap<String, Integer> firstList = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            firstList.put(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            if (firstList.containsKey(list2[i])) {
                indexSum = firstList.get(list2[i]) + i;
                if (indexSum == smallerIndex) {
                    answer.add(list2[i]);
                } else if (indexSum < smallerIndex) {
                    smallerIndex = indexSum;
                    answer.clear();
                    answer.add(list2[i]);
                }
            }
        }
        String[] stockArr = new String[answer.size()];
        stockArr = answer.toArray(stockArr);
        return stockArr;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> threeSums = new ArrayList<>();
        Stack<Integer> stackTriplet = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        stackTriplet.push(nums[i]);
                        stackTriplet.push(nums[j]);
                        stackTriplet.push(nums[k]);


                    }
                }

            }

        }
        return threeSums;
    }

    public static int[][] transposeLC867(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int i = 0; i <= A.length - 1; i++) {
            for (int j = 0; j <= A[0].length - 1; j++) {
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    public static int firstUniqChar(String s) {
        if (s.length() == 1) {
            return 0;
        }
        HashMap<Character, Integer> scoreboard = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (scoreboard.containsKey(c)) {
                scoreboard.put(c, scoreboard.get(c) + 1);
            } else {
                scoreboard.put(c, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (scoreboard.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int[] singleNumber(int[] nums) {
        int[] rs = new int[2];
        if (nums.length == 2) {
            return nums;
        }
        int index = -1;
        HashMap<Integer, Integer> scores = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!scores.containsKey(nums[i])) {
                scores.put(nums[i], 1);
            } else {
                scores.put(nums[i], 2);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (scores.get(nums[i]) == 1) {
                rs[++index] = nums[i];
            }
        }
        return rs;

    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        char[] a = ransomNote.toCharArray();
        List<Character> magList = new ArrayList<>();
        for (int i = 0; i < magazine.toCharArray().length; i++) {
            magList.add(magazine.toCharArray()[i]);
        }
        for (int i = 0; i < a.length; i++) {
            if (magList.contains(a[i])) {
               magList.remove(magList.indexOf(a[i]));
            } else {
                return false;
            }
        }
        return true;

    }
}



























