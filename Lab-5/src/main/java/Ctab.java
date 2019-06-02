import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.*;
import org.hibernate.sql.*;

import java.net.URL;
import java.util.List;
import java.io.IOException;
import java.util.ResourceBundle;

public class Ctab implements Initializable {
    Main m =new Main();
    @FXML
    private TableView view;
    private TableColumn id;
    private TableColumn zad1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      TableColumn id =new TableColumn("Id");
      TableColumn zad1= new TableColumn("Zadanie1 ");
      TableColumn zad2= new TableColumn("Zadanie2 ");
      TableColumn zad3= new TableColumn("Zadanie3 ");
      TableColumn zad4= new TableColumn("Zadanie4 ");
      TableColumn zad5= new TableColumn("Zadanie5 ");
      TableColumn zad6= new TableColumn("Zadanie6 ");
        view.getColumns().addAll(id,zad1,zad2,zad3,zad4,zad5,zad6);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        List<StudentLog> log = session.createQuery("from StudentLog ").list();
        ObservableList<StudentLog> students = FXCollections.observableList(log);

        final  ObservableList<StudentLog> test = FXCollections.observableArrayList(
                new StudentLog(1,"0","0","0","0","0","0") ,
                new StudentLog(2,"3","0","0","0","0","0")
        );

       id.setCellValueFactory(new PropertyValueFactory<Osoby, String>("Name"));
       zad1.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad1"));
       zad2.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad2"));
       zad3.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad3"));
       zad4.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad4"));
       zad5.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad5"));
       zad6.setCellValueFactory(new PropertyValueFactory<StudentLog, String>("zad6"));
        view.setItems(students);

        t.commit();

        System.out.println("successfully saved");
        factory.close();


    }

    public void powrot(ActionEvent actionEvent) {

        try {
            m.window();
        } catch (IOException e) {

        }
    }
}