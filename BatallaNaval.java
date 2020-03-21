/*
Ubica los cinco(5) barcos de batalla naval en el tablero al azar.
Portaaviones (5 agujeros)
Destructor (4 agujeros)
Submarino (3 agujeros)
Crucero de Guerra (3 agujeros)
Lancha de Guerra (2 agujeros)
*/

/*
Autor: Johan Astudillo

Por: www.FileTechn.com
*/

import java.util.Random;
import java.util.Scanner;

public class BatallaNaval {
    public static void main(String[] args) {
        int tamX, tamY;
        
        //Lectura por consola del tamaño del tablero
        Scanner consola = new Scanner(System.in);
        do{
            System.out.print("Total filas (mínimo 6): "); 
            tamX = consola.nextInt();
            System.out.print("Total columnas (mínimo 6): "); 
            tamY = consola.nextInt();
        }while(tamX<=5 || tamY<=5);
        
        Random azar = new Random(); //El generador de números aleatorios
        
        //Arreglo bdimensional que será el tablero de batalla naval. Se inicializa con el caracter punto
        char Tablero[][] = new char[tamX][tamY];
        for (int fila=0; fila < Tablero.length; fila++)
            for (int col=0; col < Tablero[fila].length; col++)
                Tablero[fila][col] = '.';
        
        //Ubica cada barco. Parámetros: generador de números aleatorios, tablero, número de agujeros, letra del barco
        CuadraBarco(azar, Tablero, 5, 'P');
        CuadraBarco(azar, Tablero, 4, 'D');
        CuadraBarco(azar, Tablero, 3, 'S');
        CuadraBarco(azar, Tablero, 3, 'C');
        CuadraBarco(azar, Tablero, 2, 'L');
        
        //Muestra el tablero con los barcos ubicados
        for (int fila=0; fila < Tablero.length; fila++){
            System.out.println(" ");
            for (int col=0; col < Tablero[fila].length; col++)
                System.out.print(Tablero[fila][col]);
        }
    }
    
    //Cuadra el barco en el tablero evitando que se cruce con otro
    public static void CuadraBarco(Random azar, char[][] Tablero, int huecos, char barco){
        boolean posicionado; //Controla si el barco fue ubicado correctamente
        int posX, posY; //Coordenadas de ubicación del barco
        do{
            posicionado = true; //Por defecto está bien ubicado
            
            if (azar.nextDouble()<0.5){ //Se ubicará horizontalmente el barco
                posX = azar.nextInt(Tablero.length-huecos);
                posY = azar.nextInt(Tablero[posX].length);
                posicionado = true; 
                
                //Revisa si en esa ubicación solo hay '.', significa que no hay barco allí
                for (int revisa=0; revisa<huecos; revisa++) if (Tablero[posX+revisa][posY] != '.') posicionado = false;
                
                //Si pasa la revisión, ubica el barco
                if (posicionado) for (int revisa=0; revisa<huecos; revisa++) Tablero[posX+revisa][posY]=barco;
            }
            else{ //Se ubicará verticalmente el barco
                posX = azar.nextInt(Tablero.length);
                posY = azar.nextInt(Tablero[posX].length-huecos);
                
                //Revisa si en esa ubicación solo hay '.', significa que no hay barco allí
                for (int revisa=0; revisa<huecos; revisa++) if (Tablero[posX][posY+revisa] != '.') posicionado = false;
                
                //Si pasa la revisión, ubica el barco
                if (posicionado) for (int revisa=0; revisa<huecos; revisa++) Tablero[posX][posY+revisa]=barco;                
            }
        }while(!posicionado); //No sale del ciclo hasta que ubique el barco
    }
}
