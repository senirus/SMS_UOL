package com.example.sms_uol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Optional;

import static jdk.internal.agent.Agent.getText;


public class HelloController {
    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtusername;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newUsername;

    @FXML
    private TextField Degree;

    @FXML
    private TextField eMail;

    @FXML
    private TextField fullName;




    @FXML
    private Button newStudent;

    @FXML
    private Button undergraduates;


    @FXML
    private Button ugradLoginHome;

    @FXML
    private Button ugradLoginLogin;


    @FXML
    private Button ugradStatusOk;

    @FXML
    private Button tncBack;

    @FXML
    private Button tncNext;

    @FXML
    private Button formBack;

    @FXML
    private Button formHome;

    @FXML
    private Button formSubmit;

    @FXML
    public Label welcomeTxt;

    @FXML
    private Label welcomeTxt2;




    @FXML
    void newStudentClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) newStudent.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sms_ui4.fxml"));
        primaryStage.setTitle("Terms & Conditions");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void undergraduatesClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) undergraduates.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sms_ui2.fxml"));
        primaryStage.setTitle("Undergraduate Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    @FXML
    void ugradLoginHomeClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) ugradLoginHome.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("SMS - University of London");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    @FXML
    void ugradLoginLoginClick(ActionEvent event) throws IOException, SQLException {

        if (!txtusername.getText().isBlank()) {

            validateLogin();


        } else {
            welcomeTxt.setText("Can't validate your user credentials!");
        }




    }
    public void validateLogin() throws SQLException, IOException {

        Databaseconnection con = new Databaseconnection();
        Connection db = con.getDBconnection();

        String query = "SELECT count(1) FROM currentstudents.currentstudents where username = '" + txtusername.getText() + "' and password = '" + txtpassword.getText() + "' ";
        Statement statement = db.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {

            if (result.getInt(1) == 1) {
                welcomeTxt.setText("Logged in successfully!");
                Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
                newalert.setContentText("Logged in successfully!");
                Optional<ButtonType> result1 = newalert.showAndWait();
                ButtonType button = result1.orElse(ButtonType.CANCEL);
                if (button == ButtonType.OK) {

                    Stage stage = (Stage) ugradLoginLogin.getScene().getWindow();
                    stage.close();
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("sms_ui3.fxml"));
                    primaryStage.setTitle("Undergraduate Status");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.show();

                } else if (button == ButtonType.CANCEL) {
                    System.out.println("It will be cancelled");
                }
            } else {
                welcomeTxt.setText("Username or Password is incorrect");
            }
        }

    }


    @FXML
    void ugradStatusOkClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) ugradStatusOk.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sms_ui2.fxml"));
        primaryStage.setTitle("Undergraduate Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    @FXML
    void tncBackClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) tncBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("SMS - University of London");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void tncNextClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) tncNext.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sms_ui5.fxml"));
        primaryStage.setTitle("Enter Your Details");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void formBackClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) formBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sms_ui4.fxml"));
        primaryStage.setTitle("Terms & Conditions");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    void formHomeClick(ActionEvent event) throws IOException {

        Stage stage = (Stage) formHome.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("SMS - University of London");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    @FXML
    void formSubmitClick(ActionEvent event) throws SQLException, IOException {

        Databaseconnection con = new Databaseconnection();
        Connection db = con.getDBconnection();

        String query = "INSERT INTO `currentstudents`.`currentstudents` (`username`,`password`,`Full Name`,'Degree','Email') VALUES (?,?,?,?,?)";

        PreparedStatement pp = db.prepareStatement(query);
        pp.setString(2,newUsername.getText());
        pp.setString(3,newPassword.getText());
        pp.setString(4,fullName.getText());
        pp.setString(5,Degree.getText());
        pp.setString(5,eMail.getText());
        pp.execute();
        welcomeTxt2.setText("Your details will be recorded");


        Alert newalert = new Alert(Alert.AlertType.CONFIRMATION);
        newalert.setContentText("Thank you for submitting your details. Let's contact you soon!");
        Optional<ButtonType> result = newalert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK) {

            Stage stage = (Stage) formSubmit.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            primaryStage.setTitle("SMS - University of London");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } else if (button == ButtonType.CANCEL) {
            System.out.println("It will be cancelled");
        }

    }




}