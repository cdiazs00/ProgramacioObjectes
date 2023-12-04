package com.example.tres_en_ratlla;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class Controller {

    // Iniciem la partida només una vegada.
    private static final Partida partida = new Partida();
    public Button boton_empezar_partida;

    // Fem referència als elements FXML

    // Botó per començar i abandonar la partida.
    @FXML
    Button button_empezar_partida, button_abandonar_partida;

    // Àrees de text on es mostren les victòries/derrotes de tots dos jugadors.
    @FXML
    TextArea vjugador1, vjugador2, djugador1, djugador2;

    // Text que determina de qui és el torn.
    @FXML
    Text tj1, tj2;

    // Botons que són les caselles del tres en ratlla.
    @FXML
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, bc;

    // Array de botons, per poder-hi accedir de manera dinàmica.
    @FXML
    Button[] listabotones = new Button[9];

    /*
     * Mètode Empezar partida, que s'executa en fer clic en aquest botó.
     */
    @FXML
    public void Empezar_Partida(ActionEvent event) {

        /* Obtenim l'id del botó començar partida i assignem a l'array de botons tots els botons clicables. */
        button_empezar_partida = (Button) event.getSource();
        listabotones = new Button[]{b0, b1, b2, b3, b4, b5, b6, b7, b8};

        // Demanem el mode de joc a l'usuari
        partida.setModo(Alerts.Elige_Modo());

        if (partida.getModo() != null) {
            if (partida.getModo().equals("VSHumano")) {
                partida.EmpezarPartida();
                if (partida.getTurno() == 0) {
                    MostrarTurno(tj1);
                } else {
                    MostrarTurno(tj2);
                }
                OcultarBoton(button_empezar_partida);
                MostrarBoton(button_abandonar_partida);
            } else {
                // Demanem la dificultat a l'usuari
                String dificultad = Alerts.Elige_Dificultad();
                if (dificultad != null) {
                    partida.setIa(dificultad);
                    partida.EmpezarPartida();
                    OcultarBoton(button_empezar_partida);
                    MostrarBoton(button_abandonar_partida);
                    if (partida.getTurno() == 0) {
                        MostrarTurno(tj1);
                        if (partida.getModo().equals("ComVSCom")) {
                            TurnoCOM(partida.getCuadricula());
                        }
                    } else {
                        MostrarTurno(tj2);
                        TurnoCOM(partida.getCuadricula());
                    }
                }
            }
        }
    }

    /*
     * Mètode que s'executa en fer clic en el botó d'abandonar la partida.
     */
    @FXML
    public void Abandonar_Partida(ActionEvent event) {

        button_abandonar_partida = (Button) event.getSource();
        Boolean respuesta = Alerts.Abandonar_Partida();

        if (respuesta) {
            Partida.AbandonarPartida();
            Restart();
        }
    }

    /*
     * Mètode que s'executa en clicar sobre una casella de la quadrícula. (Botó)
     */
    @FXML
    public void Marcar(ActionEvent event) {

        // Si hem iniciat la partida, podrem marcar.
        if (partida.getEstado()) {
            bc = (Button) event.getSource();
            String sid = bc.getId().replaceAll("b", "");
            int id = Integer.parseInt(sid);
            char[] cuadricula = partida.getCuadricula();
            partida.PropiedadesTurno();

            // Si la casella NO està marcada, podrem marcar-la.
            if (partida.EstadoCuadricula(cuadricula[id])) {
                bc.styleProperty().setValue("-fx-text-fill: " + partida.getColor() + ";");
                bc.setText(partida.getValue());
                partida.setPosCuadricula(id, partida.getValue().charAt(0));

                // Comprovem si es donen les condicions de victòria.
                if (partida.ComprobarVictoria(partida.getValue().charAt(0))) {
                    Partida.AnunciarGanador(partida.getValue().charAt(0));
                    Restart();
                    partida.FinalizarPartida();
                } else {
                    if (partida.CuadriculaLLena()) {
                        Alerts.Anunciar_Empate();
                        Restart();
                        partida.FinalizarPartida();
                    } else {
                        if (partida.getTurno() == 0) {
                            SetTurno(1, tj1, tj2);
                        } else {
                            SetTurno(0, tj2, tj1);
                        }
                    }
                }
                if (!partida.getModo().equals("VSHumano") && partida.getEstado()) {
                    TurnoCOM(cuadricula);
                }
            }
        } else {
            Alerts.Debes_Empezar_Partida();
        }
    }

    /* Mètode que s'executa quan és el torn de la CPU, establint les propietats del torn,
     * i triant el moviment a realitzar segons la dificultat triada. */
    private void TurnoCOM(char[] cuadricula) {
        partida.PropiedadesTurno();
        if (partida.getIa().equals("Fácil")) {
            IAFacil(cuadricula);
        } else if (partida.getIa().equals("Normal")) {
            // TODO: IANormal(cuadricula);
            IAFacil(cuadricula);
        } else {
            // TODO: IADificil(cuadricula);
            IAFacil(cuadricula);
        }

        if (partida.getEstado()) {
            if (partida.getModo().equals("ComVSCom")) {
                if (partida.getTurno() == 0) {
                    SetTurno(1, tj1, tj2);
                } else {
                    SetTurno(0, tj2, tj1);
                }
                TurnoCOM(cuadricula);
            } else {
                SetTurno(0, tj2, tj1);
            }
        }
    }

    /* Mètode que s'ocupa del comportament de la CPU quan la seva IA és fàcil.
     * L'assignació de la casella és aleatòria, sempre que no estigui ocupada. */
    private void IAFacil(char[] cuadricula) {
        int random;
        do {
            random = (int) (Math.random() * 10 - 1);
        } while (!partida.EstadoCuadricula(cuadricula[random]));
        listabotones[random].styleProperty().setValue("-fx-text-fill: " + partida.getColor() + ";");
        listabotones[random].setText(partida.getValue());
        partida.setPosCuadricula(random, partida.getValue().charAt(0));
        if (partida.ComprobarVictoria(partida.getValue().charAt(0))) {
            Partida.AnunciarGanador(partida.getValue().charAt(0));
            Restart();
            partida.FinalizarPartida();
        } else {
            if (partida.CuadriculaLLena()) {
                Alerts.Anunciar_Empate();
                Restart();
                partida.FinalizarPartida();
            }
        }
    }

    // Mètode que s'encarrega d'amagar el botó indicat.
    private void OcultarBoton(Button button) {
        button.setManaged(false);
        button.setVisible(false);
    }

    private void MostrarBoton(Button button) {
        button.setManaged(true);
        button.setVisible(true);
    }

    // Mètode que s'encarrega de mostrar el Text del torn.
    private void MostrarTurno(Text tj) {
        tj.styleProperty().setValue("Visibility:true");
    }

    // Mètode que s'encarrega d'amagar el text del torn.
    private void OcultarTurno(Text tj) {
        tj.styleProperty().setValue("Visibility: false");
    }

    // Mètode que s'encarrega de refrescar el marcador visual.
    private void RefrescarMarcador(TextArea vj1, TextArea vj2, TextArea dj1, TextArea dj2) {
        vj1.setText(String.valueOf(Controller.partida.getVjugador1()));
        vj2.setText(String.valueOf(Controller.partida.getVjugador2()));
        dj1.setText(String.valueOf(Controller.partida.getDjugador1()));
        dj2.setText(String.valueOf(Controller.partida.getDjugador2()));
    }

    // Mètode que s'encarrega de buidar la quadrícula.
    private void VaciarCuadriculaFX() {
        for (Button button : listabotones) {
            button.textProperty().setValue("");
        }
    }

    // Mètode que s'encarrega de posar el tauler per defecte.
    private void Restart() {
        RefrescarMarcador(vjugador1, vjugador2, djugador1, djugador2);
        OcultarTurno(tj1);
        OcultarTurno(tj2);
        OcultarBoton(button_abandonar_partida);
        MostrarBoton(button_empezar_partida);
        VaciarCuadriculaFX();
    }

    // Mètode que s'encarrega de configurar els torns.
    private void SetTurno(int turno, Text turnoOcultar, Text turnoMostrar) {
        partida.setTurno(turno);
        OcultarTurno(turnoOcultar);
        MostrarTurno(turnoMostrar);
    }
}
