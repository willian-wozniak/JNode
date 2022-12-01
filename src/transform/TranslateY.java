/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transform;

/**
 *
 * @author WillianWozniak
 */
public class TranslateY
{
 
    private double value;
    private String unit;

    public TranslateY(String value) {
        setUnit(value);
        setValue(value);
    }

    private void setUnit(String value) {
        this.unit = (value.contains("%") ? "%" : "px");
    }

    public String getUnit() {
        return this.unit;
    }

    private void setValue(String value) {
        this.value = Double.parseDouble(value.replaceAll(this.unit, ""));
    }

    public double getValue() {
        return this.value;
    }
    
}
