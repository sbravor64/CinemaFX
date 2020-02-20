package org.cinemaFX;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.cinemaFX.control.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SampleController implements Initializable {
    private final ObservableList<PieChart.Data> dataCharts = FXCollections.observableArrayList();

    ConexionXML conexionXML;
    List<String> images;
    String url = "http://www.gencat.cat/llengua/cinema/";

    List<Film> films;
    List<Sesion> sesions;
    List<Cinema> cinemas;
    List<Cicle> cicles;

    private double x=0, y=0;

    ObservableList<String> listObservableFilms =FXCollections.observableArrayList();
    ObservableList<String> listObservableSesions =FXCollections.observableArrayList();


    @FXML
    private ListView<String> listViewFilms;

    @FXML
    private Text textTitleFilm;

    @FXML
    private ImageView imageFilm;

    @FXML
    private Circle btnCerrar;

    @FXML
    private TabPane tabPane;

    @FXML
    private Text direcctorFilm;

    @FXML
    private Text directorTitle;

    @FXML
    private Text añoFilm;

    @FXML
    private Text añoTitle;

    @FXML
    private PieChart pieChart;

    @FXML
    private Pane pane;

    @FXML
    private Button buttonSesion;

    @FXML
    private AnchorPane  paneSesion;

    @FXML Text sesionTitle;



    @FXML
    private TableColumn<Cinema, String> listViewTitleCinema;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pane.setVisible(false);
            connectedXML();
            loadFilms();
            makeDragable();
            opaqueInfoMovie();
            diagrama();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void diagrama(){
        List<Integer> años = films.stream()
                .map(film -> film.getAny())
                .filter(i -> i > 0 && i < 3000).distinct()
                .sorted(Comparator.comparingInt(integer -> integer))
                .collect(Collectors.toList());

        for (Integer i: años) {
            long numResultat= films.stream()
                    .filter(film1 -> film1.getAny() == i)
                    .count();
            dataCharts.add(new PieChart.Data(i.toString(), numResultat));
        }

        pieChart.setData(dataCharts);
        pieChart.setLegendSide(Side.LEFT);

        final Label label = new Label();
        pane.getChildren().add(label);
        label.setFont(Font.font("SanSerif", FontWeight.BLACK, 20));

        pieChart.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e->{
                int intValue = (int) data.getPieValue();
                pane.setVisible(true);
                if(intValue==1){
                    label.setText(intValue + " pelicula");
                }else {
                    label.setText(intValue + " peliculas");
                }
            });
        });
    }

    private void connectedXML() throws JAXBException, IOException {
        conexionXML = new ConexionXML();
        conexionXML.connectedFilms();
        conexionXML.connectedSessions();
        conexionXML.connectedCinema();
        conexionXML.connectedCicles();

        sesions = conexionXML.getSesions();
        cinemas = conexionXML.getCinemas();
        films = conexionXML.getFilms();
        cicles = conexionXML.getCicles();
    }

    private void loadFilms() throws JAXBException, IOException {
        conexionXML = new ConexionXML();
        conexionXML.connectedFilms();

        films = conexionXML.getFilms();

        List<String> listaTitle = films.stream().map(film -> film.getTitol()).collect(Collectors.toList());
        images = films.stream().map(film -> film.getImage()).collect(Collectors.toList());

        listObservableFilms.addAll(listaTitle);
        listViewFilms.getItems().addAll(listObservableFilms);
    }

    public void displaySelected(MouseEvent mouseEvent) {
        String filmTitle = listViewFilms.getSelectionModel().getSelectedItem();

        if(filmTitle==null|| filmTitle.isEmpty()){
            textTitleFilm.setText("No has seleccionado ninguna pelicula");
        } else {
            visibleInfoMovie();
            textTitleFilm.setText(filmTitle);
            for (Film f: films) {
                if(f.getTitol().equals(filmTitle)){

                    String urlImage=url+f.getImage();
                    Image imageMovie = new Image(urlImage);

                    imageFilm.setImage(imageMovie);
                    direcctorFilm.setText(f.getDireccio());
                    añoFilm.setText(String.valueOf(f.getAny()));

                    List<String> listaCines = sesions.stream().filter(sesion -> f.getIdFilm() == sesion.getIdFilm()).map(sesion -> sesion.getNomCine()).collect(Collectors.toList());
//                    listaCines.forEach(System.out::println);
//                    System.out.println(".");
                    listObservableSesions.addAll(listaCines);
                    sesionTitle.setText(f.getTitol());
                }
            }
        }
    }

    public void handlerMouseEvent(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == btnCerrar){
//            System.exit(0);
            Stage stage = (Stage) btnCerrar.getScene().getWindow();
            stage.close();
        }
    }

    public void makeDragable(){
        tabPane.setOnMousePressed((event -> {
            x=event.getSceneX();
            y=event.getSceneY();
        }));

        tabPane.setOnMouseDragged((event -> {
            Stage stage = (Stage) btnCerrar.getScene().getWindow();
            stage.setX(event.getScreenX()-x);
            stage.setY(event.getScreenY()-y);
        }));
    }

    public void visibleInfoMovie(){
        textTitleFilm.setVisible(true);
        direcctorFilm.setVisible(true);
        directorTitle.setVisible(true);
        añoFilm.setVisible(true);
        añoTitle.setVisible(true);
    }

    public void opaqueInfoMovie(){
        textTitleFilm.setVisible(false);
        direcctorFilm.setVisible(false);
        directorTitle.setVisible(false);
        añoFilm.setVisible(false);
        añoTitle.setVisible(false);
    }

    public void clickFilmSesion(MouseEvent mouseEvent) {
        paneSesion.toFront();
    }
}
