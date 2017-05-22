package com.raymondbl.essaychecker;
/** Essay Checker searches and prints out input
 * text for first and second person personal 
 * pronouns and contractions. There is an option  
 * to replace contractions with their extended 
 * form, but it is still advised to check the results.
 * @author Raymond Liu
 * @version 1.05
 * @since 06/13/2014
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

public final class Controller {

    private Highlighter highlighter = new Highlighter();
    @FXML private Text version;
    @FXML private Text count;
    @FXML private CheckBox checkContractions;
    @FXML private CheckBox checkQuotes;
    @FXML private CheckBox replaceContr;
    @FXML private CheckBox checkFirsts;
    @FXML private CheckBox checkSeconds;
    @FXML private Button check;
    @FXML private Button unhighlight;
    @FXML private HTMLEditor editor;

    @FXML
    public void check(ActionEvent ae) {
        editor.setHtmlText(highlighter.check(editor.getHtmlText(), checkContractions.isSelected(),
                checkQuotes.isSelected(), replaceContr.isSelected(),
                checkFirsts.isSelected(), checkSeconds.isSelected()));
        count.setText(highlighter.getCount() + " item(s) found.");
        if(highlighter.getCount() == 0) {
            count.setFill(Color.GREEN);
        }
        else {
            count.setFill(Color.RED);
        }
    }

    @FXML
    public void unhighlight(ActionEvent ae) {
        editor.setHtmlText(highlighter.unHighlight(editor.getHtmlText()));
    }
}
