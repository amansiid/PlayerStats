package playerstat;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import numberlist.IndexRangeException;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * GUI Players sets actions to all buttons
 *
 * @author [Aman Siid]
 * @version 3/19/2020
 *
 */
public class JavaFxUI extends Application {

    //fields
    Players playerList;

    TextField txtName;
    DatePicker txtAge;
    TextField txtPace;
    TextField txtDrib;
    TextField txtPhysical;
    Button save;
    Alert popup;
    VBox playerBox;
    String pattern = "MM/dd/yyyy";
    String selectedPlayer = null;
    BarChart<String, Number> barChartModal;

    @Override
    public void init() {
        // Player list that holds all the players
        playerList = new Players();
        // Player list that holds all teh players for display. Each player is a button
        playerBox = new VBox();
        //Text fields for the player interaction form
        txtName = new TextField();
        txtAge = new DatePicker();
        txtPace = new TextField();
        txtDrib = new TextField();
        txtPhysical = new TextField();
    }

    @Override
    public void start(Stage stage) throws IndexRangeException {
        //pattern
        txtAge.setPromptText(pattern.toLowerCase());
        /*
          Initiate the GridPane. This is the main window.
         */
        GridPane root = new GridPane();
        root.setVgap(10);
        root.setHgap(10);

        /*
          Generate the sub modals
         */
        TitledPane playerWindow = createPlayerInteractionWindow();
        TitledPane sortingWindow = createSortingWindow();
        ScrollPane playerScrollWindow = createPlayerScrollsWindow();
        TitledPane statsWidnow = createGraphWindow();
        save = new Button("Save List");
        save.setOnAction(e -> savePlayers());
        /*
          Initialize the Bar Chart
         */
        generateChart();

        /*
        create background fill
         */
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);

        /*
        create background
         */
        Background background = new Background(background_fill);

        /*
          Append all the sub modals into the main window in a Grid pattern
         */
        root.add(playerWindow, 0, 0, 1, 2);
        root.add(sortingWindow, 1, 0, 1, 1);
        root.add(save, 1, 1, 1, 1);
        root.add(statsWidnow, 0, 2, 2, 1);
        root.add(playerScrollWindow, 2, 0, 1, 4);
        root.setAlignment(Pos.TOP_LEFT);
        root.setBackground(background);
        playerList = Players.loadPlayers();
        /*
          Set up the scene and show the modal
         */
        Scene scene = new Scene(root);
        stage.setTitle("Soccer Players Stats");
        stage.setScene(scene);
        stage.setWidth(1300);
        stage.setHeight(780);
        stage.getIcons().add(new Image("file:coach.png"));

        stage.show();

        //popup
        popup = new Alert(Alert.AlertType.NONE);
    }

    /**
     * Create the sub modal for the player interaction (Add/Delete/Update)
     *
     * @return the sub-modal pane
     */
    private TitledPane createPlayerInteractionWindow() {

        //Create a Titled pane as a sub modal for the player interaction window
        TitledPane playerWindow = new TitledPane();
        playerWindow.setText("Add a player or delete,update");
        GridPane playerModal = new GridPane();
        // Create the buttons
        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction(e -> updatePlayer());
        Button btnAddPlayer = new Button("Add Player");
        btnAddPlayer.setOnAction(e -> addPlayer());
        Button btnDeletePlayer = new Button("Delete Player");
        btnDeletePlayer.setOnAction(e -> deletePlayer());
        //Labels for text boxes
        Label lblName = new Label("Name:");
        Label lblBirthDate = new Label("Birth Date:");
        Label lblPace = new Label("Pace:");
        Label lblDrib = new Label("Dribbling: ");
        Label lblPhysical = new Label("Physical: ");
        // Align all buttons and text boxes into the Grid
        playerModal.add(btnUpdate, 5, 7);
        playerModal.add(btnAddPlayer, 4, 7);
        playerModal.add(btnDeletePlayer, 6, 7);
        playerModal.add(lblName, 0, 0);
        playerModal.add(txtName, 4, 0);
        playerModal.add(lblBirthDate, 0, 1);
        playerModal.add(txtAge, 4, 1);
        playerModal.add(lblPace, 0, 2);
        playerModal.add(txtPace, 4, 2);
        playerModal.add(lblDrib, 0, 3);
        playerModal.add(txtDrib, 4, 3);
        playerModal.add(lblPhysical, 0, 4);
        playerModal.add(txtPhysical, 4, 4);
        playerWindow.setContent(playerModal);

        return playerWindow;
    }

    /**
     * Create the sub modal with the sorting options
     *
     * @return the sub-modal pane
     */
    private TitledPane createSortingWindow() {
        //Create the Titled pane fot the sorted options
        TitledPane sortPaneWindow = new TitledPane();
        GridPane sortPaneModal = new GridPane();
        sortPaneWindow.setText("Sort by: ");

        //Create the radio buttons and the submit button
        final ToggleGroup group = new ToggleGroup();

        RadioButton radPhysicallvl = new RadioButton("Physical Level");
        radPhysicallvl.setToggleGroup(group);
        RadioButton radPace = new RadioButton("Pace");
        radPace.setToggleGroup(group);
        RadioButton radDribbling = new RadioButton("Dribbling");
        radDribbling.setToggleGroup(group);

        Button btnSort = new Button("Sort");
        btnSort.setOnAction(e -> sortPlayers(getSortTermObject(radPace)));

        // Align all buttons into the Grid
        sortPaneModal.add(radPhysicallvl, 0, 1);
        sortPaneModal.add(radPace, 0, 2);
        sortPaneModal.add(radDribbling, 0, 3);
        sortPaneModal.add(btnSort, 4, 7);
        sortPaneWindow.setContent(sortPaneModal);

        return sortPaneWindow;
    }

    /**
     * Create the sub modal with the Players
     *
     * @return the sub-modal pane
     */
    private ScrollPane createPlayerScrollsWindow() {
        //Scroll pane that will group the playerBox variable
        ScrollPane playerScroll = new ScrollPane();
        playerScroll.setContent(playerBox);

        return playerScroll;
    }

    /**
     * Create the sub modal with the Players' statistics
     *
     * @return sub-modal pane
     */
    private TitledPane createGraphWindow() {
        //Titled pane for the stats of the player
        TitledPane statsPane = new TitledPane();
        statsPane.setText("Graphs");
        //Grid pane for the graph
        GridPane gridPane4 = new GridPane();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        barChartModal = new BarChart<String, Number>(xAxis, yAxis);
        gridPane4.add(barChartModal, 4, 4);
        statsPane.setContent(gridPane4);
        barChartModal.setAnimated(false);
        xAxis.setLabel("Player Name");
        yAxis.setLabel("Performance");
        barChartModal.setTitle("Player Summary");

        return statsPane;
    }

    /**
     * Generate the chart with the current players' stats
     */
    public void generateChart() {
        // Clear all data from the Modal
        barChartModal.getData().clear();
        barChartModal.layout();
        // Initialize a new graph
        XYChart.Series chartTotal = new XYChart.Series();
        chartTotal.setName("Total");
        // Clear all data from the Series
        chartTotal.getData().clear();
        // For each player add all stats
        for (int i = 0; i < playerList.numberOfPlayers(); i++) {
            String playerName = playerList.getName(i);
            long total = playerList.getTotal(i);
            chartTotal.getData().add(new XYChart.Data<String, Number>(playerName, total));
        }

        // Display the stats in a Bar Chart
        barChartModal.setData(FXCollections.observableArrayList(chartTotal));
    }

    /**
     * Add a player to the list
     */
    public void addPlayer() {

        try {
            //popup when handling parsing and adding
            if (txtName.getText().equals("")) {
                popup.setAlertType(Alert.AlertType.WARNING);
                popup.setTitle("ERORR!");
                popup.setContentText("Please type a name");
                popup.show();

            } else {
                int position = playerList.addPlayer(
                        txtName.getText(),
                        txtAge.getEditor().getText(),
                        Integer.parseInt(txtPace.getText()),
                        Integer.parseInt(txtDrib.getText()),
                        Integer.parseInt(txtPhysical.getText())
                );
                Button newPlayer;
                newPlayer = new Button(playerList.toString(position));
                // The button for that player holds the index of the player
                newPlayer.setId(txtName.getText());
                playerBox.getChildren().add(newPlayer);
                // this is in case we want to display the stats on click of the player
                newPlayer.setOnAction(e -> {
                    Button b = (Button) e.getSource();
                    // Define the variable of the selected player
                    selectedPlayer = b.getId();
                    int pos = playerList.findPlayer(selectedPlayer);
                    // Populate the text boxes with player's info
                    txtName.setText(selectedPlayer);
                    txtAge.getEditor().setText(String.valueOf(playerList.getBirthDate(pos)));
                    txtPace.setText(String.valueOf(playerList.getPace(pos)));
                    txtDrib.setText(String.valueOf(playerList.getDribbling(pos)));
                    txtPhysical.setText(String.valueOf(playerList.getPhysical(pos)));
                });
            }

        } catch (NumberFormatException nf) {
            popup.setAlertType(Alert.AlertType.WARNING);
            popup.setTitle("ERORR!");
            popup.setContentText("Age, Pace, Dribbling or Physical fields are missing! ");
            popup.show();
        }
        clearFields();
        Players.savePlayers(playerList);
        generateChart();
    }

    public void deletePlayer() {

        if (selectedPlayer != null) {
            int pos = playerList.findPlayer(selectedPlayer);
            playerList.deleteAt(pos);
            playerBox.getChildren().remove(pos);
            clearFields();
            generateChart();
            Players.savePlayers(playerList);

            selectedPlayer = null;
        } else {
            popup.setAlertType(Alert.AlertType.WARNING);
            popup.setTitle("ERROR!");
            popup.setContentText("Select a player to delete");
            popup.show();
        }

    }

    public void updatePlayer() {

        if (selectedPlayer != null) {
            int oldPosition = playerList.findPlayer(selectedPlayer);
            addPlayer();
            playerList.deleteAt(oldPosition);
            playerBox.getChildren().remove(oldPosition);
            clearFields();
            generateChart();
            Players.savePlayers(playerList);

            selectedPlayer = null;
        } else {
            popup.setAlertType(Alert.AlertType.WARNING);
            popup.setTitle("ERROR!");
            popup.setContentText("Select a player to update");
            popup.show();
        }

    }

    private void savePlayers() {
        String name = txtName.getText();
        String birthDate = txtAge.getEditor().getText();
        String pace = txtPace.getText();
        String dribbling = txtDrib.getText();
        String physical = txtPhysical.getText();

        playerList.addPlayer(
                name.substring(name.indexOf(" ")),
                birthDate.substring(birthDate.indexOf(" ")),
                Integer.parseInt(pace.substring(pace.indexOf(" "))),
                Integer.parseInt(dribbling.substring(dribbling.indexOf(" "))),
                Integer.parseInt(physical.substring(physical.indexOf(" "))));
        Players.savePlayers(playerList);
    }

    public void clearFields() {
        txtName.clear();
        txtAge.getEditor().clear();
        txtPace.clear();
        txtDrib.clear();
        txtPhysical.clear();
    }

    /**
     * Sort the players based on a sort type. The sort type is retrieved based
     * on the radio buttons
     *
     * @param sortType: the enum variable from the SortType class
     */
    public void sortPlayers(SortType sortType) {
        // Function<Integer, Long> sortTerm = playerList.getAge(sortType);
        ArrayList<Integer> sortedPlayers = playerList.getSortedByTerm(sortType);
        playerBox.getChildren().clear();
        for (int i : sortedPlayers) {
            Button player = new Button(playerList.toString(i));
            player.setId(playerList.getName(i));
            playerBox.getChildren().add(player);
            // this is in case we want to display the stats on click of the player
            player.setOnAction(e -> {
                //  Object node = e.getSource();
                Button b = (Button) e.getSource();
                // Define the variable of the selected player
                selectedPlayer = b.getId();
                int pos = playerList.findPlayer(selectedPlayer);
                // Populate the text boxes with player's info
                txtName.setText(selectedPlayer);
                txtAge.getEditor().setText(String.valueOf(playerList.getBirthDate(pos)));
                txtPace.setText(String.valueOf(playerList.getPace(pos)));
                txtDrib.setText(String.valueOf(playerList.getDribbling(pos)));
                txtPhysical.setText(String.valueOf(playerList.getPhysical(pos)));
            });
        }
        generateChart();
    }

    /**
     * This returns the appropriate enum SortType based on the radio button
     * selection
     *
     * @param button: ont button that belongs into the group of the buttons
     * @return the sortType one from [Age, Physical, Pace, Dribbling]
     */
    private SortType getSortTermObject(RadioButton button) {

        RadioButton chk = (RadioButton) button.getToggleGroup().getSelectedToggle();
        String selectedButton = chk.getText();

        switch (selectedButton) {
            case "Pace":
                return SortType.Pace;
            case "Physical Level":
                return SortType.Physical;
            case "Dribbling":
                return SortType.Dribbling;
            default:
                return null;
        }

    }
}
