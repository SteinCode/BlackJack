module rokas.games.blackjack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.mockito.junit.jupiter;
    requires org.junit.jupiter.api;

    opens rokas.games.blackjack to javafx.fxml;
    opens rokas.games.blackjack.Controllers to javafx.fxml;
    exports rokas.games.blackjack;
    exports rokas.games.blackjack.Controllers to javafx.fxml;
}