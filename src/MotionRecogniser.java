import java.awt.*;
import java.util.*;

/**
 * Created by Andrew on 26.05.2016.
 */
public class MotionRecogniser {
    public static java.util.List<Integer> objectList = new ArrayList<Integer>();
    /**
     * Знаходить початкову і кінцеву точку заданої групи
     * @param array масив
     * @param label мітка групи
     * @return
     */
    public static Rectangle getRectangle(int[][] array, int label, int coef)
    {
        int h = array.length;
        int w = array[0].length;

        Rectangle result = new Rectangle();

        int minimalW = w;
        int minimalH = h;

        int maximalW = 0;
        int maximalH = 0;

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if ( (array[i][j] == label) && (minimalH > i) ) minimalH = i;
                if ( (array[i][j] == label) && (minimalW > j) ) minimalW = j;

                if ( (array[i][j] == label) && (maximalH < i) ) maximalH = i;
                if ( (array[i][j] == label) && (maximalW < j) ) maximalW = j;
            }
        }

        result.x = minimalW  * coef;
        result.y = minimalH * coef;

        result.width = (maximalW * coef - result.x) ;
        result.height = (maximalH * coef - result.y) ;

        return result;
    }

    /**
     * Метод об’єднує групи по значенням
     * @param array масив
     * @return масив з групами значень, де елементи кожної групи мають однакові значення
     */
    public static int[][] mapObjects(int[][] array)
    {
        int h = array.length;
        int w = array[0].length;

        int[][] result = array;

        int kn = 0;
        int km = 0;

        int A = 0;
        int B = 0;
        int C = 0;

        int cur = 0;

        // Цикл по пикселям изображения
        for (int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                kn = j - 1;
                if (kn <= 0){
                    kn = 1;
                    B = 0;
                }
                else{
                    B = result[i][kn];
                }
                km = i - 1;
                if (km <=0){
                    km = 1;
                    C = 0;
                }
                else {
                    C = result[km][j];
                }
                A = result[i][j];
                if (A == 0){
                    // Если в текущем пикселе пусто - то ничего не делаем
                }else if( (B == 0)&&(C == 0) ){
                    cur = cur + 1;
                    result[i][j] = cur;
                    if (objectList.indexOf(cur) < 0 )
                        objectList.add(cur);
                }else if( (B != 0)&&(C == 0) ){
                    result[i][j] = B;
                }else if( (B == 0)&&(C != 0) ){
                    result[i][j] = C;
                }else if( (B != 0)&&(C != 0) ){

                    if (B == C){
                        result[i][j] = B;
                    }else{
                        result[i][j] = B;
                        result = substituteC(result, C, B);
                        //все щобуло С замінити на B
                    }
                }
            }
        }
        return result;
    }

    /**
     * Метод заміняє Всі С в масиві на В
     * @param array масив
     * @param C значення С
     * @param B значення В
     * @return замінений масив
     */
    public static int [][] substituteC(int[][] array, int C, int B)
    {
        int h = array.length;
        int w = array[0].length;

        int[][] result = array;

        if ( objectList.indexOf(C) >= 0 )
            objectList.remove(objectList.indexOf(C));

        if (objectList.indexOf(B) < 0 )
            objectList.add(B);

        //System.out.println("Substitution of " + C + " by " + B);

        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++){
                if ( result[i][j] == C ) result[i][j] = B;
            }
        return result;
    }

    /**
     * Рекомендується подавати інтенсивності бінаризованих зображень
     * @param BaseImage матриця інтенсивностей базового зображення
     * @param DistortedImage матриця інтенсивностей зміненого зображення
     * @return різниця інтенсивностей
     * */
    public static int [][] subtract(int[][] BaseImage, int[][] DistortedImage)
    {
        int[][] result = new int[BaseImage.length][BaseImage[0].length];

        for (int i = 0; i < result.length; i++){
            for (int j=0; j < result[0].length; j++){
                result[i][j]=Math.abs( (DistortedImage[i][j]&0xFFFFFFFF)-(BaseImage[i][j]&0xFFFFFFFF) );
            }
        }

        return result;
    }
}
