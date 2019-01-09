/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spinner;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.util.Date;
import javax.swing.*;
import java.util.Calendar;

public class Spinner extends JFrame{
    

    public Spinner(){
        initComponents();
    }

    @SuppressWarnings("Convert2Lambda")
    void initComponents(){
        
        this.setTitle("spinnery");
        this.setBounds(300,300,300,300);
        this.setDefaultCloseOperation(3);
        String[] miesiace = new DateFormatSymbols().getMonths();
        
        MySpinnerListModel Listamiesiace = new MySpinnerListModel(cutArray(miesiace,0,11));   
        SpinnerNumberModel numberModel = new SpinnerNumberModel(1,1,5,2);
        
        
        Calendar calendar = Calendar.getInstance();
        Date initialValue=calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -28);
        Date minimalValuie = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 60);
        Date maximumValue=calendar.getTime();
        SpinnerDateModel dateModel = new SpinnerDateModel(initialValue,minimalValuie,maximumValue,Calendar.DAY_OF_MONTH);
        
        JSpinner spinner = new JSpinner(Listamiesiace);
        JSpinner spinner2 = new JSpinner(numberModel);
        JSpinner spinner3 = new JSpinner(dateModel);
        
        
        JButton button = new JButton("Whats the date");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println((Date)spinner3.getValue());
            }
        });
        
        this.getContentPane().add(button,BorderLayout.SOUTH);
        
        JPanel panel = new JPanel();
        panel.add(spinner);
        panel.add(spinner2);
        panel.add(spinner3);
       
        this.getContentPane().add(panel, BorderLayout.NORTH);
}
    
    Object[] cutArray(Object[] operatedArray, int initialValue, int lastValue)
    {
        
        lastValue=lastValue>operatedArray.length-1||lastValue<0?operatedArray.length-1:lastValue;
        initialValue=initialValue>operatedArray.length-1||initialValue<0?operatedArray.length-1:initialValue;
        
        Object[] cuttedArray = new Object[(lastValue+1)-initialValue];
        for(int i=initialValue,j=0;i<lastValue+1;i++,j++)
        {
            cuttedArray[j]=operatedArray[i];
        }
        
        return cuttedArray;
    }
    
    private class MySpinnerListModel extends SpinnerListModel{
    
        Object firstValue,lastValue;
        
        MySpinnerListModel(Object[] values){
            super(values);   
            firstValue = values[0];
            lastValue = values[values.length-1];
        }    
        
        @Override
        public Object getNextValue()
        {
            if(super.getNextValue()==null)
            {
                System.out.println("null");
                return firstValue;
            }
            return super.getNextValue();
        
        }
        
        @Override
         public Object getPreviousValue()
        {
            if(super.getPreviousValue()==null)
            {
                System.out.println("null");
                return lastValue;
            }
            return super.getPreviousValue();
        
        }
        
    }
    
    public static void main(String[] args) {
        Spinner okno = new Spinner();
        okno.setVisible(true);
    }
    
}
