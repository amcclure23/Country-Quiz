package edu.uga.countryquiz;

public class country {
    String countryName;
    String ContinentName;

    /* creates a country object*/
    public country()
    {
    }

    /*sets the continent name
    * param string
    */
    public void setContinentName(String continentName) {
        ContinentName = continentName;
    }

    /*sets the country name
     * param string
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

