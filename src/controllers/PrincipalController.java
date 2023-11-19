package controllers;

import java.util.ArrayList;

import aplication.Aplicacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Actividad;
import model.Proceso;

public class PrincipalController {
	
	private Aplicacion aplicacion;
	
	ModelFactoryController modelFactoryController;
	
	private String usuarioAdmin;
	
	//---------TAB PROCESO------------
	
    @FXML
    private TextField txtNombreProceso;

    @FXML
    private TextField txtIdProcesos;

    @FXML
    private TextField txtDescripcionProcesos;

    @FXML
    private TableView<Proceso> tableProcesos;

    @FXML
    private TableColumn<Proceso, String> columnProceso;

    @FXML
    private Button btnCrearProceso;

    @FXML
    private Label labelNombreP;

    @FXML
    private Label labelIdP;

    @FXML
    private Label labelDescripcionP;

    @FXML
    private Label labelDuracionMinP;

    @FXML
    private Label labelDuracionMaxP;

    @FXML
    private ImageView flechaRegresar;
    
    Proceso procesoSeleccionado;
    
    ObservableList<Proceso> listaProcesosData = FXCollections.observableArrayList();
    
    private ObservableList<Proceso> getListaProcesosData(){
		listaProcesosData.addAll(modelFactoryController.obtenerProcesos());
		return listaProcesosData;
	}

    @FXML
    void crearProcesoEvent(ActionEvent event) {
    	crearProcesoAction();
    }

    @FXML
    void flechaRegresarEvent(MouseEvent event) {
    	flechaRegresar.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent ->{
    		aplicacion.mostrarVentanaIniciar();
    	});
    }
    
    private void crearProcesoAction(){
    	String nombreP = txtNombreProceso.getText();
    	String idP = txtIdProcesos.getText();
    	String descripcionP = txtDescripcionProcesos.getText();
    	
    	if(datosValidosP(nombreP, idP, descripcionP)){
    		if(modelFactoryController.crearProceso(nombreP, idP, descripcionP)){
    			mostrarMensaje("Notificacion creación", "Proceso creado", "Se ha creado con exito el proceso", AlertType.INFORMATION);
    			listaProcesosData.setAll(modelFactoryController.obtenerProcesos());
    			listaProcesosAData.setAll(modelFactoryController.obtenerProcesosA());
                tableProcesos.refresh();
    			txtNombreProceso.setText("");
    			txtIdProcesos.setText("");
    			txtDescripcionProcesos.setText("");
    		}else{
    			mostrarMensaje("Notificacion creación", "Proceso no creado", "Ya existe un proceso con el id "+idP+" No se puede crear", AlertType.INFORMATION);
                tableProcesos.refresh();
    			txtNombreProceso.setText("");
    			txtIdProcesos.setText("");
    			txtDescripcionProcesos.setText("");
    		}
    	}else {
			mostrarMensaje("Notificación creación", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
    }
    
    private boolean datosValidosP(String nombre, String id, String descripcion) {
		if (nombre.equals("")) {
			return false;
		}
		if (id.equals("")) {
			return false;
		}
		if (descripcion.equals("")) {
			return false;
		}
		return true;

	}
    
    //-----------------------------
    
    //--------------TAB ACTIVIDADES--------------------------
    
    @FXML
    private ComboBox<String> comboBoxProcesosA;

    @FXML
    private ComboBox<String> comboBoxActividades;

    @FXML
    private TableView<String> tableActividades;

    @FXML
    private TableColumn<Actividad, String> columnActividad;

    @FXML
    private TextField txtNombreA;

    @FXML
    private TextField txtDescripcionA;

    @FXML
    private RadioButton rBtnSi;

    @FXML
    private RadioButton rBtnNo;

    @FXML
    private Button btnCrearActividad;
    
    ObservableList<String> listaProcesosAData = FXCollections.observableArrayList();
    
    private ToggleGroup grupoOpciones = new ToggleGroup();

    private ObservableList<String> getListaProcesosAData(){
		listaProcesosAData.addAll(modelFactoryController.obtenerProcesosA());
		return listaProcesosAData;
	}
    
    ObservableList<String> listActividadesCBData = FXCollections.observableArrayList();

    private ObservableList<String> getListaActividadesCBData(String proceso){
    	 ArrayList<String> actividades = modelFactoryController.obtenerActividadesCB(proceso);
    	    if (actividades != null) {
    	        listActividadesCBData.addAll(actividades);
    	    } else {
    	        System.out.println("error");
    	    }
    	    return listActividadesCBData;
	}
    
    @FXML
    void crearActividadEvent(ActionEvent event) {
    	crearActividadAction();
    }
    
    private void crearActividadAction(){
    	String nombreA = txtNombreA.getText();
    	String descripcion = txtDescripcionA.getText();
    	String proceso = comboBoxProcesosA.getSelectionModel().getSelectedItem();
    	String preceder = comboBoxActividades.getSelectionModel().getSelectedItem();
    	RadioButton radioButtonSeleccionado = (RadioButton) grupoOpciones.getSelectedToggle();
		String seleccion = radioButtonSeleccionado.getText();
    	
    	
    	if(datosValidosA(nombreA, descripcion, proceso,seleccion)){
    		if(preceder== null){
    			modelFactoryController.crearActividad(nombreA, descripcion, proceso, preceder, seleccion);
    			mostrarMensaje("Notificacion creación", "Actividad creada", "Se ha creado con exito la actividad", AlertType.INFORMATION);
    		}else{
    			if(modelFactoryController.crearActividad(nombreA, descripcion, proceso, preceder, seleccion)){
        			mostrarMensaje("Notificacion creación", "Actividad creada", "Se ha creado con exito la actividad", AlertType.INFORMATION);
        			tableActividades.setItems(getListaActividadesCBData(proceso));
                    tableActividades.refresh();
        			txtNombreA.setText("");
        			txtDescripcionA.setText("");
        			comboBoxProcesosA.setValue(null);
        			comboBoxActividades.setValue(null);
        			
        		}else{
        			mostrarMensaje("Notificacion creación", "Actividad no creada", "Ya existe una actividad con el nombre "+nombreA+" No se puede crear", AlertType.INFORMATION);
        			tableActividades.refresh();
        			txtNombreA.setText("");
        			txtDescripcionA.setText("");
        			comboBoxProcesosA.setValue(null);
        			comboBoxActividades.setValue(null);
        		}
    		}
    
    	}else {
			mostrarMensaje("Notificación creación", "Informacion invalida", "Informacion invalida", AlertType.ERROR);
		}
    }
    
    
    
	private void filtrarActividades(ActionEvent event) {
		String proceso= comboBoxProcesosA.getSelectionModel().getSelectedItem();
		getListaActividadesCBData(proceso);
		comboBoxActividades.setItems(getListaActividadesCBData(proceso));
	}
	
	private boolean datosValidosA(String nombre, String descripcion, String proceso,String seleccion) {
		if (nombre.equals("")) {
			return false;
		}
		if (proceso.equals("")) {
			return false;
		}
		if (descripcion.equals("")) {
			return false;
		}
		if (seleccion.equals("")) {
			return false;
		}
		if (grupoOpciones.getSelectedToggle() == null) {
			return false;
		}
		return true;

	}
    
    //----------------------------------
	
	//-------TAB BUSQUEDA-----------------
	
	


	public void setAplicacion(Aplicacion aplicacion, String usuarioAdmin) {
		this.aplicacion = aplicacion;
		this.usuarioAdmin = usuarioAdmin;
		tableProcesos.getItems().clear();
		tableProcesos.setItems(getListaProcesosData());
		//tableProcesos.refresh();
		tableActividades.getItems().clear();
//		tableProcesos.setItems(getListaProcesosData());
		
	}
	
	@FXML
	void initialize() {
		modelFactoryController = ModelFactoryController.getInstance();
		tableProcesos.setItems(getListaProcesosData());
		comboBoxProcesosA.setItems(getListaProcesosAData());
		comboBoxProcesosA.setOnAction(this::filtrarActividades);
		rBtnSi.setToggleGroup(grupoOpciones);
		rBtnNo.setToggleGroup(grupoOpciones);
		
		this.columnProceso.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		tableProcesos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

	   		procesoSeleccionado = newSelection;
	   	
	   		mostrarInformacionProceso(procesoSeleccionado);
	   	});
		
		this.columnActividad.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	}
	

	private void mostrarInformacionProceso(Proceso procesoSeleccionado2) {
		labelNombreP.setText(procesoSeleccionado2.getNombre());
		labelIdP.setText(procesoSeleccionado2.getId());
		labelDescripcionP.setText(procesoSeleccionado2.getDescripcion());
		
	}

	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {

		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}
	 @FXML
	    private TextField recepcionProcesos12;

	    @FXML
	    private TextField recepcionProcesos121;

	    @FXML
	    private Button btnIntercambiar;

	    @FXML
	    private Label labelDescripcion1;

	    @FXML
	    private Label labelDuracionMi1;

	    @FXML
	    private Label labelDuracionMa1;

	    @FXML
	    private Label labelDescripcion11;

	    @FXML
	    private Label labelDuracionMi11;

	    @FXML
	    private Label labelDuracionMa11;

	    @FXML
	    private TextField txtBuscarActividades;

	    @FXML
	    private Label labelDescripcion12;

	    @FXML
	    private Label labelDuracionMi12;

	    @FXML
	    private Label labelDuracionMa12;

	    @FXML
	    private Label labelObligatoria;

	    @FXML
	    private Label labelProceso;

	    @FXML
	    private TableView<?> tableTareas;

	    @FXML
	    private TableColumn<?, ?> columnTareas;

	    @FXML
	    private Label labelDescripcion121;

	    @FXML
	    private Label labelDuracionMi121;

	    @FXML
	    private Label labelDuracionMa121;

	    @FXML
	    private Label labelObligatoria1;

	    @FXML
	    private Label labelProceso1;


	    @FXML
	    void buscarEvent(KeyEvent event) {

	    }

	  

	    @FXML
	    void intercambiarEvent(ActionEvent event) {

	    }
	
	

}
