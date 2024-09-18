package Learning;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    private final BufferedImage num1, num2, num3,xSymbolPrinted,oSymbolPrinted,xSymbolShowed,oSymbolShowed;

    public Images(){
        try {
            num1 = ImageIO.read(getClass().getResourceAsStream("/numbers/1.png"));
            num2 = ImageIO.read(getClass().getResourceAsStream("/numbers/2.png"));
            num3 = ImageIO.read(getClass().getResourceAsStream("/numbers/3.png"));
            xSymbolPrinted = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_X_Symbol.png"));
            oSymbolPrinted = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_0_Symbol.png"));
            xSymbolShowed = ImageIO.read(getClass().getResourceAsStream("/symbols/Showed_0_Symbol.png"));
            oSymbolShowed = ImageIO.read(getClass().getResourceAsStream("/symbols/Showed_X_Symbol.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getNum1() {
        return num1;
    }

    public BufferedImage getNum2() {
        return num2;
    }

    public BufferedImage getNum3() {
        return num3;
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
