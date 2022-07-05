/*************************
 * Batch Image Inversion
 * 
 * @author Aydin Simsek
 * @date 07/07/2022
*************************/

import edu.duke.*;
import java.io.*;

public class BatchInversion 
{
    public ImageResource makeInversion(ImageResource inputImg)
    {
        // create an empty image(all black) of the same size as the input image
        ImageResource outputImg = new ImageResource(inputImg.getWidth(), inputImg.getHeight());
        // for each pixel in outputImage
        for(Pixel outPix : outputImg.pixels())
        {
            // get the x and y coordinate values 
            int xVal = outPix.getX();
            int yVal = outPix.getY(); 
            // get the corresponding pixel in inputImg
            Pixel inPix = inputImg.getPixel(xVal, yVal); 
            // get the values of the red, green and blue components of the inputImg's corresponding pixel 
            int inRed = inPix.getRed();
            int inGreen = inPix.getGreen();
            int inBlue = inPix.getBlue();
            // take the opposite values(within the 0 to 255 range) of these components' values    
            int outRed = 255 - inRed;
            int outGreen = 255 - inGreen;
            int outBlue = 255 - inBlue;
            // set the output image's pixel values
            outPix.setRed(outRed);
            outPix.setGreen(outGreen);
            outPix.setBlue(outBlue);
        }
        return outputImg;
    }
    
    public void selectInvertSave()
    {
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles())
        {
            ImageResource inImg = new ImageResource(f);
            ImageResource outImg = makeInversion(inImg); 
            // save the inverted image file 
            outImg.setFileName("inverted_" + inImg.getFileName());
            outImg.save();
        }
    }
    
    public static void main(String args[])
    {
        BatchInversion obj = new BatchInversion();
        obj.selectInvertSave();
    }
}
