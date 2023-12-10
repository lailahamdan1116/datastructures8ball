import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class ImageLoader {
    private List<Image> images;

    public ImageLoader() {
        images = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            String imageName = "image" + i + ".png";
            Image image = Toolkit.getDefaultToolkit().getImage(imageName);
            images.add(image);
        }
    }

    public Image[] getImages() {
        return images.toArray(new Image[0]);
    }
}
