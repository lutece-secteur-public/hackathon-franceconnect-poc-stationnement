/*
 * Copyright (c) 2002-2015, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */

package fr.paris.lutece.plugins.stationnement.dataclient;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
   Sample from siv https://siv-vabf-part.interieur.gouv.fr/vehicule/AD-208-AM
   {
     "vehicule": {
       "classeEnvironnementale": "70/220*2001/100EURO4",
       "co2": 160,
       "energie": "GO",
       "cylindree": 1870,
       "numImmat": "AD-208-AM",
       "datePremImmat": "2017-02-01",
       "marque": "RENAULT",
       "typeVarianteVersion": "JMGDN6",
       "denominationCommerciale": "MEGANE SCENIC",
       "ptac": 2130,
       "categorie": "M1",
       "genre": "VP"
     },
     "personne": {
       "adresse": {
         "commune": "MARSEILLE",
         "etageEscalierAppartement": null,
         "complement": null,
         "numeroDeVoie": "12",
         "extention": null,
         "typeDeVoie": "RUE",
         "nomDeLaVoie": "LA CANEBIERE",
         "lieuDit": null,
         "codePostal": "13001"
       },
       "raisonSociale": null,
       "prenom": "PATRICK",
       "nom": "DELL"
     }
   }
 *
 */

/**
 * CartesGrise
 */
public class CarteGrise implements Serializable
 {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String _strAdresse;
    private String _strTPpNom;
    private String _strTPpPrenom;
    private String _strVDenominationCommerciale;
    private String _strVNumeroImmatriculation;
    private String _strVMarque;

    private String _strSource;

    public void setTPpPrenom( String strTPpPrenom  )
    {
        _strTPpPrenom = strTPpPrenom;
    }
    public String getTPpPrenom(  )
    {
        return _strTPpPrenom;
    }

    public void setTPpNom( String strTPpNom  )
    {
        _strTPpNom = strTPpNom;
    }
    public String getTPpNom(  )
    {
        return _strTPpNom;
    }

    public void setVDenominationCommerciale( String strVDenominationCommerciale  )
    {
        _strVDenominationCommerciale = strVDenominationCommerciale;
    }
    public String getVDenominationCommerciale(  )
    {
        return _strVDenominationCommerciale;
    }

    public void setVNumeroImmatriculation( String strVNumeroImmatriculation  )
    {
        _strVNumeroImmatriculation = strVNumeroImmatriculation;
    }
    public String getVNumeroImmatriculation(  )
    {
        return _strVNumeroImmatriculation;
    }

    public void setVMarque( String strVMarque  )
    {
        _strVMarque = strVMarque;
    }
    public String getVMarque(  )
    {
        return _strVMarque;
    }

    public void setAdresse( String strAdresse  )
    {
        _strAdresse = strAdresse;
    }
    public String getAdresse(  )
    {
        return _strAdresse;
    }

    @JsonProperty( "personne" )
    public void setPersonne( Map<String, Object> personne ) {
        setTPpNom((String)personne.get("nom"));
        setTPpPrenom((String)personne.get("prenom"));
        Map<String, String> mapAdresse =(Map<String,String>) personne.get("adresse");
        setAdresse(
                mapAdresse.get("numeroDeVoie") + " " +
                mapAdresse.get("typeDeVoie") + " " +
                mapAdresse.get("nomDeLaVoie") + ", " +
                mapAdresse.get("codePostal") + " " +
                mapAdresse.get("commune")
                );
    }

    @JsonProperty( "vehicule" )
    public void setVehicule( Map<String, String> vehicule ) {
        setVNumeroImmatriculation(vehicule.get("numImmat"));
        setVDenominationCommerciale(vehicule.get("denominationCommerciale"));
        setVMarque(vehicule.get("marque"));
    }

    public void setSource( String strSource  )
    {
        _strSource = strSource;
    }
    public String getSource(  )
    {
        return _strSource;
    }
}
