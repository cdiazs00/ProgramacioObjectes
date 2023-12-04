package com.example.tres_en_ratlla;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class Alerts {

    // Alerta que ens permet triar el mode en què jugarem.
    public static String Elige_Modo() {
        String modo;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Triï un mode de joc");
        alert.setHeaderText("""
                Humà VS Humà: Partida humà contra humà.
                Humà VS CPU: Partida humà contra l'ordinador.
                CPU VS CPU: Partida ordinador vs ordinador""");

        ButtonType btHumanoVsHumano = new ButtonType("VS Humà");
        ButtonType btHumanoVsCom = new ButtonType("VS CPU");
        ButtonType btComVsCom = new ButtonType("CPU VS CPU");

        alert.getButtonTypes().setAll(btHumanoVsHumano, btHumanoVsCom, btComVsCom, ButtonType.CLOSE);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btHumanoVsHumano) {
            modo = "VSHumano";
        } else if (result.get() == btHumanoVsCom) {
            modo = "VSCom";
        } else if (result.get() == btComVsCom) {
            modo = "ComVSCom";
        } else {
            alert.close();
            modo = null;
        }

        return modo;
    }

    // Alerta que ens permet triar la dificultat.
    public static String Elige_Dificultad() {
        String dificultad;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Anem a jugar a tres en ratlla!");
        alert.setHeaderText("Triï una dificultat del joc.");

        ButtonType btFacil = new ButtonType("Fàcil");
        ButtonType btNormal = new ButtonType("Normal");
        ButtonType btDificil = new ButtonType("Difícil");

        alert.getButtonTypes().setAll(btFacil, btNormal, btDificil, ButtonType.CLOSE);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == btFacil) {
            dificultad = "Fàcil";
        } else if (result.get() == btNormal) {
            dificultad = "Normal";
        } else if (result.get() == btDificil) {
            dificultad = "Difícil";
        } else {
            alert.close();
            dificultad = null;
        }
        return dificultad;
    }

    // Alerta mostrada quan indiquem que volem abandonar la partida.
    public static Boolean Abandonar_Partida() {
        boolean respuesta;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Estàs segur que vols abandonar la partida?");
        alert.setHeaderText("(Si abandones la partida, obtindràs una derrota i l'altre jugador sumarà una victòria).");
        alert.getButtonTypes().setAll(ButtonType.CLOSE, ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            respuesta = true;
        } else {
            respuesta = false;
            alert.close();
        }
        return respuesta;
    }

    // Alerta mostrada quan intentem jugar, mentre no ha començat la partida.
    public static void Debes_Empezar_Partida() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("ERROR");
        alert.setHeaderText("No pots jugar a tres en ratlla, sense abans donar-li a \"COMENÇAR PARTIDA\"");
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }

    // Alerta mostrada per anunciar el guanyador.
    public static void Anunciar_Ganador(int ganador) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (ganador == 0) {
            alert.setTitle("¡VICTÒRIA!");
            alert.setHeaderText("Enhorabona jugador 1, has guanyat!");
        } else {
            alert.setTitle("¡DERROTA!");
            alert.setHeaderText("¡Jugador 1, has perdut!");
        }
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }

    // Alerta mostrada en cas d'empat.
    public static void Anunciar_Empate() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("¡EMPAT!");
        alert.setHeaderText("Cap jugador no ha aconseguit guanyar l'altre!");
        alert.getButtonTypes().setAll(ButtonType.CLOSE);
        alert.showAndWait();
    }
}
