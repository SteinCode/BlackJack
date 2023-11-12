module rokas.games.blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires junit;
    requires org.mockito;

    opens rokas.games.blackjack to javafx.fxml;
    opens rokas.games.blackjack.Controllers to javafx.fxml;
    exports rokas.games.blackjack;
    exports rokas.games.blackjack.Controllers to javafx.fxml, org.junit.jupiter.engine;
}