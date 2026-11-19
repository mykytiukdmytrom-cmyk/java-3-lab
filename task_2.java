import java.util.Arrays;

// Абстрактний базовий клас Container
abstract class Container {
    protected double[] data;   // елементи контейнера (спільне поле для всіх похідних класів)

    public Container(double[] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    // Сортування елементів контейнера
    public abstract void sort();

    // Обробка елементів контейнера
    public abstract double norma();

    public double[] getData() {
        return data;
    }
}

// Похідний клас Bubble: бульбашкове сортування, норма = sqrt(сума елементів)
class Bubble extends Container {
    private int swapsCount;    // власне поле: кількість обмінів під час сортування

    public Bubble(double[] data) {
        super(data);
        this.swapsCount = 0;
    }

    @Override
    public void sort() {
        swapsCount = 0;
        int n = data.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    double tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                    swapsCount++;
                }
            }
        }
    }

    // Квадратний корінь із суми елементів (обхід через foreach)
    @Override
    public double norma() {
        double sum = 0;
        for (double x : data) {
            sum += x;
        }
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        return "Bubble " + Arrays.toString(data) + ", кількість обмінів: " + swapsCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bubble other = (Bubble) obj;
        return Arrays.equals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}

// Похідний клас Choice: сортування вибором максимального, норма = середнє арифметичне
class Choice extends Container {
    private int comparisonsCount;   // власне поле: кількість порівнянь

    public Choice(double[] data) {
        super(data);
        this.comparisonsCount = 0;
    }

    // Сортування за зростанням: максимальний елемент ставимо в кінець невідсортованої частини
    @Override
    public void sort() {
        comparisonsCount = 0;
        for (int end = data.length - 1; end > 0; end--) {
            int maxIdx = 0;
            for (int j = 1; j <= end; j++) {
                comparisonsCount++;
                if (data[j] > data[maxIdx]) {
                    maxIdx = j;
                }
            }
            double tmp = data[maxIdx];
            data[maxIdx] = data[end];
            data[end] = tmp;
        }
    }

    // Середнє арифметичне всіх елементів
    @Override
    public double norma() {
        if (data.length == 0) return 0;
        double sum = 0;
        for (double x : data) {
            sum += x;
        }
        return sum / data.length;
    }

    @Override
    public String toString() {
        return "Choice " + Arrays.toString(data) + ", кількість порівнянь: " + comparisonsCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Choice other = (Choice) obj;
        return Arrays.equals(this.data, other.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }
}

public class task_2 {
    public static void main(String[] args) {
        double[] a = {5, 2, 9, 1, 7, 3};
        double[] b = {4, 8, 1, 6, 2};

        // Масив об'єктів (типу абстрактного базового класу)
        Container[] arr = {
                new Bubble(a),
                new Choice(a),
                new Bubble(b),
                new Choice(b),
                new Bubble(a)
        };

        System.out.println("===== До сортування =====");
        for (Container c : arr) {
            System.out.println(c);
        }

        System.out.println("\n===== Сортування та норма =====");
        for (Container c : arr) {
            c.sort();
            System.out.println(c);
            System.out.println("   norma() = " + c.norma());
        }

        System.out.println("\n===== Перевірка equals =====");
        System.out.println("arr[0].equals(arr[4]) (Bubble, однакові дані): " + arr[0].equals(arr[4]));
        System.out.println("arr[0].equals(arr[2]) (Bubble, різні дані):    " + arr[0].equals(arr[2]));
        System.out.println("arr[0].equals(arr[1]) (Bubble та Choice):      " + arr[0].equals(arr[1]));
        System.out.println("arr[1].equals(arr[3]) (Choice, різні дані):    " + arr[1].equals(arr[3]));
    }
}