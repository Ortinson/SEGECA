package ui;

import def.*;
import java.awt.Color;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class Controller {

	static Conector.ConectorBD bd;
	
	public static void main(String[] args) {
		
		UI window = new UI();
		window.getFrame().setVisible(true);
		
		bd = new Conector.ConectorBD("192.168.1.84:3306","SEGECA","admin","Grupo10");
		pruebas();
		bd.desconectar();
	}
	
	//Basurilla para comprobar el funcionamiento de ConectorBD
	private static void pruebas(){
		def.Agenda a = new def.Agenda();
		a.setCodAgenda(2);
		bd.extraerAgenda(a);
		System.out.println(a.toString());
		
	}
    
    public static int prepararAgenda(){
       // comprobamos que los datos recibidos sean correctos
        if(comprobarAgenda() == -1){
            return -1;
        }
        Agenda ag = new Agenda();
        
        // CCC agenda
        // Esta Jlist se refiere a los nombres de CCC
        // HAY QUE CREAR ESTA LISTA QUE CONTENDRA TODOS LOS NOMBRES DEL CCC
        // Cambiar null por la jlist correspondiente
        JList listaCCC = null;
        String nombreCCC = (String) listaCCC.getModel().getElementAt(listaCCC.getSelectedIndex());
        
        Ccc ccc = new Ccc();
        ccc.setNombreCCC(nombreCCC);
        
        // rellenamos los campos de la agenda
        ag.setCcc(ccc);
        ag.setFecha(null);
        ag.setLugar(null);
        ag.setHoraInicio(null);
        ag.setHoraFin(null);
        ag.setProposito(null);
        ag.setCodAgenda(0);
        ag.setParticipantes(null);
        
        return 0;
    }

    private static int comprobarAgenda() {
        
        // Esta Jlist se refiere a los nombres de CCC
        // HAY QUE CREAR ESTA LISTA QUE CONTENDRA TODOS LOS NOMBRES DEL CCC
        // Cambiar null por la jlist correspondiente
        JList listaCCC = null;
        
        // Si no hay seleccionado ningun CCC
        if(listaCCC.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún CCC", "Error", JOptionPane.ERROR_MESSAGE);           
        }
        
        // Comprobación Fecha
        // cambiar null por jTextField correspondiente a fecha agenda
        JTextField fechaAgenda = null;
        
        if(!isFechaValida(fechaAgenda.getText()))
            return -1;
        
        // Comprobacion lugar
        // cambiar null por jTexfield correspondiente a lugar de Agenda
        JTextField lugarAgenda = null;
        
        if(lugarAgenda.getText().length() > 20)
            JOptionPane.showMessageDialog(null, "Error al introducir el lugar de la Agenda", "Error", JOptionPane.ERROR_MESSAGE); 
        
        //Comprobamos hora inicio/fin
        //cambiar nulls por jtexfield correspondiente a las horas de inicio y fin de agenda
        JTextField horaInicio =null;
        JTextField horaFin = null;
        
        if(!isHoraValida(horaInicio.getText()) || !isHoraValida(horaFin.getText()))
            return -1;
            
        
    
        return 0;
    
    
    }
    
    // metodo que comprueba el formato de la fecha de la Agenda
    private static boolean isFechaValida(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        }
        catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha no es correcto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    // metodo que comprueba el formato de la hora de la Agenda
    public static boolean isHoraValida(String hora) {
        try {
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formatoHora.setLenient(false);
            formatoHora.parse(hora);
        }
        catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(null, "El formato de la hora no es correcto", "Error", JOptionPane.ERROR_MESSAGE);             
            return false;
        }
        return true;
    }
}