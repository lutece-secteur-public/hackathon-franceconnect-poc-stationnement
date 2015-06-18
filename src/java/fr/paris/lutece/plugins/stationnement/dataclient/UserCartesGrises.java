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

/*
Sample from mi_siv (mockup from opendatasoft)
{
   "parameters" : {
      "format" : "json",
      "timezone" : "UTC",
      "q" : "t_pp_nom:VOILE",
      "dataset" : [
         "mi_siv"
      ],
      "rows" : 10
   },
   "nhits" : 2,
   "records" : [
      {
         "fields" : {
            "v_numeroidentifiant" : "VF1JP0500301014",
            "t_pp_departement" : 75,
            "t_pp_datenaissance" : "1970-09-17",
            "t_pp_communenaissance" : "Paris",
            "v_typevarianteversion" : "JP0E05",
            "t_pp_nealetranger" : "non",
            "v_couleur" : "Beige",
            "t_pp_nom" : "VOILE",
            "v_denominationcommerciale" : "Modus",
            "t_pp_prenom" : "Laurence",
            "v_numero_immatriculation" : "AA-780-AA",
            "t_pp_sexe" : "M",
            "v_marque" : "Renault"
         },
         "record_timestamp" : "2015-06-18T12:21:41+00:00",
         "recordid" : "5ea3d4c6544b718e0514efb0f6f1a4172f965a43",
         "datasetid" : "mi_siv"
      },
      {
         "fields" : {
            "v_numeroidentifiant" : "7MSEA8TAWCX0NAGFA59B010S",
            "t_pp_nom" : "VOILE",
            "v_couleur" : "Gris",
            "v_denominationcommerciale" : "Sharan",
            "t_pp_prenom" : "Laurence",
            "t_pp_sexe" : "M",
            "v_numero_immatriculation" : "AA-711-AA",
            "v_marque" : "Volkswagen"
         },
         "record_timestamp" : "2015-06-18T12:21:41+00:00",
         "recordid" : "c5d0eccad560842feb236181bf7953e511d183ec",
         "datasetid" : "mi_siv"
      }
   ]
}
 *
 */

package fr.paris.lutece.plugins.stationnement.dataclient;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * UserCartesGrises
 */
public class UserCartesGrises implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private CarteGrise _CartesGrises[]; 

    /**
     * Sets the Records
     * @param records The Records
     */
    @JsonProperty( "records" )
    public void setRecords( Map<String, Object>[] records )
    {
        CarteGrise cartesGrises[] = new CarteGrise[records.length];
        for (int i = 0; i< records.length; i++) {
            Map<String,Object> record = records[i];
            CarteGrise carteGrise = new CarteGrise();
            Map<String, Object> fields = ( Map<String,Object> ) record.get( "fields" );
            carteGrise.setTPpNom( (String)fields.get( "t_pp_nom"));
            carteGrise.setTPpPrenom( (String)fields.get( "t_pp_prenom"));
            carteGrise.setTPpSexe( (String)fields.get( "t_pp_sexe"));
            carteGrise.setVCouleur( (String)fields.get( "v_couleur"));
            carteGrise.setVDenominationCommerciale( (String)fields.get( "v_denominationcommerciale"));
            carteGrise.setVNumeroIdentifiant( (String)fields.get( "v_numeroidentifiant"));
            carteGrise.setVNumeroImmatriculation( (String)fields.get( "v_numero_immatriculation"));
            carteGrise.setVMarque( (String)fields.get( "v_marque"));
            cartesGrises[i] = carteGrise;
        }
        _CartesGrises = cartesGrises;
    }

    public CarteGrise[] getCartesGrises(  )
    {
        return _CartesGrises;
    }
}
