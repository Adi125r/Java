import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;
import java.io.IOException;

public class Controller {
    Main m =new Main();
    @FXML
    public TextField name;
    @FXML
    public TextField surname;
    @FXML
    public TextField index;
    @FXML
    public TextField id1;
    @FXML
    public TextField zad11;
    @FXML
    public TextField zad22;
    @FXML
    public TextField zad33;
    @FXML
    public TextField zad44;
    @FXML
    public TextField zad55;
    @FXML
    public TextField zad66;
    @FXML
   private Button addu;
    @FXML
   private Button addv;
    @FXML
   private Button view;

    @FXML
    public void addUser(){

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Osoby e1=new Osoby();
        e1.setId(3);
        e1.setName(name.getText());
        e1.setSurnname(surname.getText());
        e1.setStudentIndex(index.getText());

        StudentLog e2=new StudentLog();

        e2.setId(4);
        e2.setZad1("0");
        e2.setZad2("0");
        e2.setZad3("0");
        e2.setZad4("0");
        e2.setZad5("0");
        e2.setZad6("0");

        session.save(e1);
        session.save(e2);
        t.commit();
        try {
            m.kom();
        } catch (IOException e) {

        }
        System.out.println("successfully saved");
        factory.close();
        //session.close();

    }

   @FXML
   public void addV(){
       try {
           m.loadV();
       } catch (IOException e) {

       }
   }
    @FXML
    public void viewAll(){

        try {
            m.loadW();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void addvalue(ActionEvent actionEvent) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentLog studentLog = (StudentLog)session.get(StudentLog.class,Integer.parseInt(id1.getText()));

        try {
            if (Integer.parseInt(zad11.getText()) > 0)
                studentLog.setZad1(zad11.getText());
        }catch (Exception e){}
          try {
              if (Integer.parseInt(zad22.getText()) > 0)
                  studentLog.setZad2(zad22.getText());
          }catch (Exception e){}
          try {
              if (Integer.parseInt(zad33.getText()) > 0)
                  studentLog.setZad3(zad33.getText());
          }catch (Exception e){}
          try {
              if (Integer.parseInt(zad44.getText()) > 0)
                  studentLog.setZad4(zad44.getText());
          }catch (Exception e){}
          try {
              if (Integer.parseInt(zad55.getText()) > 0)
                  studentLog.setZad5(zad55.getText());
          }catch (Exception e){}
          try{
            if (Integer.parseInt(zad66.getText()) > 0)
               studentLog.setZad6(zad66.getText());
        }catch (Exception e){
            System.out.println("Bledna wartosc");
        }

        session.update(studentLog);
        t.commit();
        session.close();

    }

    public void addU(ActionEvent actionEvent) {
        try {
            m.loadU();
        } catch (IOException e) {

        }
    }

    public void powrot(ActionEvent actionEvent) {

        try {
            m.window();
        } catch (IOException e) {

        }
    }
}
