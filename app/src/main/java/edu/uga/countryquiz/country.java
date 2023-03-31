package edu.uga.countryquiz;

public class country {
    String countryName;
    String continentName;

    /* creates a country object*/
    public country()
    {
    }

    /*sets the continent name
    * param string
    */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    /*sets the country name
     * param string
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

