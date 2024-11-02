module org.example.regexproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.regexproject to javafx.fxml;
    exports org.example.regexproject;
}