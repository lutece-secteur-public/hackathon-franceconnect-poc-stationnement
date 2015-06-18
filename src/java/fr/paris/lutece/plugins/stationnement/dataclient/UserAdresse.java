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
Sample from dgfip (mockup from opendatasoft)
{
   "parameters" : {
      "format" : "json",
      "timezone" : "UTC",
      "dataset" : [
         "dgfip_rp"
      ],
      "rows" : 1
   },
   "nhits" : 20,
   "records" : [
      {
         "fields" : {
            "lieu_de_naissance" : "49007",
            "codepostal" : 30100,
            "date_de_naissance" : "1985-10-10",
            "hypothese_dgfip_rp" : "Divorcé, 2 enfants (né en 2010 et 2013) en résidence alternée, revenu 30K€, né en 1985, vit en métropole",
            "nom_de_naissance" : "DURAND",
            "commune" : "ALÈS",
            "sexe" : "male",
            "adresse" : "68, rue de Geneve",
            "pays_de_naissance" : "99100",
            "prenoms" : "Thomas"
         },
         "record_timestamp" : "2015-06-16T15:49:13+00:00",
         "recordid" : "2c4a89a35f584d6f952d2d318a7a9561386c2907",
         "datasetid" : "dgfip_rp"
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
 * UserAdresse
 */
public class UserAdresse implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private Integer _nCodePostal;
    private String  _strCommune; 
    private String  _strAdresse; 

    /**
     * Sets the Records
     * @param records The Records
     */
    @JsonProperty( "records" )
    public void setRecords( Object[] records )
    {
        //"rfr" : 54000,
        //"nombre_de_parts" : 3,
        //
        Map<String, Object> record = ( Map<String,Object> ) records[0];
        Map<String, Object> fields = ( Map<String,Object> ) record.get( "fields" );
        _nCodePostal               = ( Integer )            fields.get( "codepostal" );
        _strCommune               = ( String )            fields.get( "commune" );
        _strAdresse               = ( String )            fields.get( "adresse" );
    }

    /**
     * Returns the CodePostal
     * @return The CodePostal
     */
    public Integer getCodePostal(  )
    {
        return _nCodePostal;
    }

    /**
     * Returns the Commune
     * @return The Commune
     */
    public String getCommune(  )
    {
        return _strCommune;
    }

    /**
     * Returns the Adresse
     * @return The Adresse
     */
    public String getAdresse(  )
    {
        return _strAdresse;
    }
}
