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


/**
 * CartesGrise
 */
public class CarteGrise implements Serializable
 {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String _strTPpNom = "VOILE";
    private String _strTPpPrenom = "Laurence";
    private String _strTPpSexe = "M";
    private String _strVCouleur = "Gris";
    private String _strVDenominationCommerciale = "Sharan";
    private String _strVNumeroIdentifiant = "7MSEA8TAWCX0NAGFA59B010S";
    private String _strVNumeroImmatriculation = "AA-711-AA";
    private String _strVMarque = "Volkswagen";

    public void setTPpSexe( String strTPpSexe  )
    {
        _strTPpSexe = strTPpSexe;
    }
    public String getTPpSexe(  )
    {
        return _strTPpSexe;
    }

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

    public void setVCouleur( String strVCouleur  )
    {
        _strVCouleur = strVCouleur;
    }
    public String getVCouleur(  )
    {
        return _strVCouleur;
    }

    public void setVDenominationCommerciale( String strVDenominationCommerciale  )
    {
        _strVDenominationCommerciale = strVDenominationCommerciale;
    }
    public String getVDenominationCommerciale(  )
    {
        return _strVDenominationCommerciale;
    }

    public void setVNumeroIdentifiant( String strVNumeroIdentifiant  )
    {
        _strVNumeroIdentifiant = strVNumeroIdentifiant;
    }
    public String getVNumeroIdentifiant(  )
    {
        return _strVNumeroIdentifiant;
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
}
