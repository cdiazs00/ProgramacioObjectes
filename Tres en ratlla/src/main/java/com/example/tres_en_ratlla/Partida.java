package com.example.tres_en_ratlla;

import java.util.Arrays;

public class Partida {

    private static String ia;
    private static String modo;
    private static int vjugador1;
    private static int vjugador2;
    private static int djugador1;
    private static int djugador2;
    private int turno;
    private final char[] cuadricula = new char[9];
    private static boolean estado = false;
    private String value;
    private String color;

    /* Getters i setters utilitzats. */

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public char[] getCuadricula() {
        return cuadricula;
    }

    public void setModo(String modo) {
        Partida.modo = modo;
    }

    public String getModo() {
        return modo;
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String dificultad) {
        ia = dificultad;
    }

    public int getVjugador1() {
        return vjugador1;
    }

    public int getVjugador2() {
        return vjugador2;
    }

    public int getDjugador1() {
        return djugador1;
    }

    public int getDjugador2() {
        return djugador2;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getTurno() {
        return turno;
    }

    // Mètode que s'encarrega d'elegir el primer torn. (Qui començarà)
    public int Eleccion_Primer_Turno() {
        return 0;
    }

    // Mètode que s'encarrega de comprovar si es compleixen les condicions de victòria.
    public boolean ComprobarVictoria(char value) {
        boolean victoria = false;
        if (cuadricula[0] == value && cuadricula[1] == value && cuadricula[2] == value) {
            victoria = true;
        } else if (cuadricula[3] == value && cuadricula[4] == value && cuadricula[5] == value) {
            victoria = true;
        } else if (cuadricula[6] == value && cuadricula[7] == value && cuadricula[8] == value) {
            victoria = true;
        } else if (cuadricula[0] == value && cuadricula[3] == value && cuadricula[6] == value) {
            victoria = true;
        } else if (cuadricula[1] == value && cuadricula[4] == value && cuadricula[7] == value) {
            victoria = true;
        } else if (cuadricula[2] == value && cuadricula[5] == value && cuadricula[8] == value) {
            victoria = true;
        } else if (cuadricula[0] == value && cuadricula[4] == value && cuadricula[8] == value) {
            victoria = true;
        } else if (cuadricula[2] == value && cuadricula[4] == value && cuadricula[6] == value) {
            victoria = true;
        }
        return victoria;
    }

    // Mètode que anuncia el guanyador i actualitza els marcadors.
    public static void AnunciarGanador(char value) {
        if (value == 'X') {
            Alerts.Anunciar_Ganador(0);
            vjugador1 = vjugador1 + 1;
            djugador2 = djugador2 + 1;
        } else {
            Alerts.Anunciar_Ganador(1);
            vjugador2 = vjugador2 + 1;
            djugador1 = djugador1 + 1;
        }
    }

    // Mètode que s'encarrega d'inicialitzar la partida.
    public void EmpezarPartida() {
        estado = true;
        if (Eleccion_Primer_Turno() < 5) {
            turno = 0;
        } else {
            turno = 1;
        }
    }

    // Mètode que s'encarrega de finalitzar la partida.
    public void FinalizarPartida() {
        estado = false;
        VaciarCuadricula();
    }

    // Mètode que s'executa en finalitzar la partida.
    public static void AbandonarPartida() {
        djugador1 = djugador1 + 1;
        vjugador2 = vjugador2 + 1;
        estado = false;
    }

    // Mètode que comprova si la quadrícula està plena.
    public boolean CuadriculaLLena() {
        boolean resultado = true;
        for (char c : cuadricula) {
            if (c != 'X' && c != 'O') {
                resultado = false;
                break;
            }
        }
        return resultado;
    }

    // Mètode que estableix les propietats del torn. (color de fitxa, i tipus)
    public void PropiedadesTurno() {
        if (turno == 0) {
            value = "X";
            color = "blue";
        } else {
            value = "O";
            color = "red";
        }
    }

    // Mètode que estableix la fitxa a la quadrícula.
    public void setPosCuadricula(int index, char value) {
        cuadricula[index] = value;
    }

    // Mètode que buida la quadrícula.
    public void VaciarCuadricula() {
        Arrays.fill(cuadricula, ' ');
    }

    // Mètode que ens retorna l'estat de la quadrícula. (Per saber si està ocupada o no)
    public boolean EstadoCuadricula(char value) {
        boolean result;
        result = value != 'X' && value != 'O';
        return result;
    }
}