import java.util.Random;
import java.util.List;

public class Fortune {
    private List<String> fortunes;
    private Random random;

    public Fortune(List<String> fortunes) {
        this.fortunes = fortunes;
        random = new Random();
    }

    public String getFortune() {
        int index = random.nextInt(fortunes.size());
        return fortunes.get(index);
    }
}