package sample.controler;

import Dialogs.DialogsUtils;
import javaK.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import sample.Main;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
public class PanelController {
    public int id = 0;
    public int jez = 1;
    ResourceBundle bundle = ResourceBundle.getBundle("sample.bundles.jezyk");
    public static final ObservableList data = FXCollections.observableArrayList();
    Instance instance = new Instance();
    @FXML
    private Button Badd;
    @FXML
    private TextArea Twaga;
    @FXML
    private TextArea Twartosc;
    @FXML
    private ListView List;
    @FXML
    private ComboBox Calg;
    @FXML
    private DatePicker Adata;
    @FXML
    private TextField Tlp;
    @FXML
    private TextArea Twa;
    @FXML
    private TextArea Tw;
    @FXML
    private TextField Til;
    @FXML
    private VBox root;
    @FXML
    private Label form;
    @FXML
    void initialize() {


        Twaga.setText("0");
        Twartosc.setText("0");
        Calg.getItems().addAll("przegląd losowy", "przegląd zupełny", "algorytm zachłanny");
        Adata.setValue(LocalDate.now());


        Tlp.setText("0");

    }

    @FXML
    public void adItem() {

        try {
            int waga = Integer.parseInt(Twaga.getText());
            double wartosc = Double.parseDouble(Twartosc.getText());
            Item item = new Item(id, waga, wartosc);

            if (waga > 0 && wartosc > 0) {
                instance.addItem(item);
                System.out.println(id);
                id++;
                data.add("Id: " + id + " Waga : " + waga + " Wartosc : " + wartosc);
                List.setItems(data);
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Blad");
                alert.setHeaderText("Information Alert");
                String s = " Podane wartosci sa nieprawidlowe. Podaj liczby od 0 . ";
                alert.setContentText(s);
                alert.show();

            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Blad");
            alert.setHeaderText("Information Alert");
            String s = " Podane wartosci sa nieprawidlowe. Podaj liczby od 0 . ";
            alert.setContentText(s);
            alert.show();
        }
    }

    @FXML
    public void Start() {
        try {
            Solution solution = new Solution();
            int ladownosc = Integer.parseInt(Tlp.getText());
            String a = "";
            if (ladownosc > 0) {
                instance.setBackpack(ladownosc + 1);
                List<Item> list = new ArrayList<>();
                if (Calg.getValue() == "przegląd losowy") {
                    Rs rs = new Rs();
                    solution = rs.solve(instance, 0, 0, list);
                    Twa.setText(Double.toString(solution.getBestValue()));
                    Tw.setText(Double.toString(solution.getBestWeight()));
                    a = Integer.toString(list.size());
                    if (list.size() == 1) {

                        a += " :  " + bundle.getString("przedmiot");
                    }
                    if (list.size() > 1 && list.size() < 5) {
                        a += " :  " + bundle.getString("przedmioty");
                    }
                    if (list.size() >= 5) {
                        a += " : " + bundle.getString("przedmiotow");
                    }
                    Til.setText(a);
                }
                if (Calg.getValue() == "przegląd zupełny") {
                    Bf bf = new Bf();
                    solution = bf.solve(instance, 0, 0, list);
                    Twa.setText(Double.toString(solution.getBestValue()));
                    Tw.setText(Double.toString(solution.getBestWeight()));
                    a = Integer.toString(solution.getSize());

                    if (solution.getSize() == 1) {

                        a += " :  " + bundle.getString("przedmiot");
                    }
                    if (solution.getSize() > 1 && solution.getSize() < 5) {
                        a += " :  " + bundle.getString("przedmioty");
                    }
                    if (solution.getSize() >= 5) {
                        a += " :  " + bundle.getString("przedmiotow");
                    }
                    Til.setText(a);
                }
                if (Calg.getValue() == "algorytm zachłanny") {
                    Greedy greedy = new Greedy();
                    solution = greedy.solve(instance, 0, 0, list);
                    Twa.setText(Double.toString(solution.getBestValue()));
                    Tw.setText(Double.toString(solution.getBestWeight()));
                    a = Integer.toString(list.size());
                    if (list.size() == 1) {

                        a += " :  " + bundle.getString("przedmiot");
                    }
                    if (list.size() > 1 && list.size() < 5) {
                        a += " :  " + bundle.getString("przedmioty");
                    }
                    if (list.size() >= 5) {
                        a += " :  " + bundle.getString("przedmiotow");
                    }
                    Til.setText(a);
                }

            }
        } catch (Exception e) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Blad");
            alert.setHeaderText("Information Alert");
            String s = " Podane wartosci sa nieprawidlowe. Podaj liczbe calkowita  ";
            alert.setContentText(s);
            alert.show();
        }
    }

    @FXML
    public void close() {

        System.exit(0);
    }

    @FXML
    public void about() {
        DialogsUtils.diaogAbout();
    }




    public void setpl(javafx.event.ActionEvent actionEvent) throws IOException
    {

        Main.setLocale(new Locale("pl", "PL"));
        sample.Main reload = new Main();
        reload.reload();

    }

    public void setGb(ActionEvent actionEvent) throws IOException
    {
        String s;
        Format formatter;
        Date date = new Date();

        // 01/09/02
        formatter = new SimpleDateFormat("dd/MM/yy");
        s = formatter.format(date);
        System.out.println(s);

        Main.setLocale(new Locale("en", "GB"));
        sample.Main reload = new Main();
        reload.reload();
        form.setText(s);
    }


    public void setUs(ActionEvent actionEvent)  throws IOException
    {

        Main.setLocale(new Locale("en_US"));
        sample.Main reload = new Main();
        reload.reload();

        String s;
        Format formatter;
        Date date = new Date();

        // 01/09/02
        formatter = new SimpleDateFormat("MM/dd/yy");
        s = formatter.format(date);
        System.out.println(s);



        form.setText(s);
    }
}