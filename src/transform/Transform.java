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
public class Transform
{
    private String transform;
    public TranslateX translateX;
    public TranslateY translateY;

    public Transform(String transform) {
        this.transform = transform;
        this.set(transform);
    }

    private void set(String transform) {
        String[] listas = this.getListas(transform);

        for (String lista : listas) {
            String propriedade = this.getPropriedade(lista);
            String[] values = this.getValues(lista);
            this.setInstance(propriedade, values);
        }

    }

    private String[] getListas(String transform) {
        return transform.split(" ");
    }

    private String getPropriedade(String lista) {
        String propriedade = lista.split("\\(")[0].split(",")[0];
        return propriedade.trim();
    }

    private String[] getValues(String lista) {
        String valuesIn = lista.split("\\(")[1].split("\\)")[0].trim();
        valuesIn = valuesIn.split("\\)")[0].trim();

        String[] values = valuesIn.split(",");

        return values;
    }

    private void setInstance(String propriedade, String[] values) {
        switch (propriedade) {
            case "translateX": {
                this.translateX = new TranslateX(values[0]);
            }
            case "translateY": {
                this.translateY = new TranslateY(values[0]);
            }
        }
    }

}
