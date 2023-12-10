import java.awt.Image;
import java.util.List;

public class EightBall {
    private Fortune fortune;
    private Image[] images;

    public EightBall() {
        fortune = new Fortune(getFortunes());
        images = new ImageLoader().getImages();
    }

    private List<String> getFortunes() {
        return List.of("It is certain", "Without a doubt", "Yes definitely", "Most likely",
                "Yes", "Outlook good", "Signs point to yes", "Of course!", "You already know the answer.",
                "Reply hazy try again",
                "Better not tell you now", "Ask again later", "Concentrate and ask again",
                "Don't count on it", "Outlook not so good", "Very doubtful", "My reply is no");
    }

    public String getFortune() {
        return fortune.getFortune();
    }

    public Image getImage() {
        int index = (int) (Math.random() * images.length);
        return images[index];
    }
}
