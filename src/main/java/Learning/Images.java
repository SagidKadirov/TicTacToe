package Learning;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    private final BufferedImage xSymbolPrinted,oSymbolPrinted,xSymbolShowed,oSymbolShowed;

    public Images(){
        try {
            xSymbolPrinted = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_X_Symbol.png"));
            oSymbolPrinted = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_0_Symbol.png"));
            xSymbolShowed = ImageIO.read(getClass().getResourceAsStream("/symbols/Showed_X_Symbol.png"));
            oSymbolShowed = ImageIO.read(getClass().getResourceAsStream("/symbols/Showed_0_Symbol.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getoSymbolPrinted() {
        return oSymbolPrinted;
    }

    public BufferedImage getoSymbolShowed() {
        return oSymbolShowed;
    }

    public BufferedImage getxSymbolPrinted() {
        return xSymbolPrinted;
    }

    public BufferedImage getxSymbolShowed() {
        return xSymbolShowed;
    }
}
