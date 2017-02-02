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

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.paris.lutece.plugins.franceconnect.oidc.Token;
import fr.paris.lutece.plugins.franceconnect.oidc.dataclient.AbstractDataClient;
import fr.paris.lutece.plugins.franceconnect.service.MapperService;
import fr.paris.lutece.plugins.stationnement.service.RedirectUtils;
import fr.paris.lutece.plugins.stationnement.web.FranceConnectSampleApp;
import fr.paris.lutece.portal.service.util.AppLogService;


/**
 * AdresseDataClient.java
 */
public class AftDataClient extends AbstractDataClient
{

    public static final String ATTRIBUTE_USERADRESSEFISCALE = "stationnement-dc-useraft";

    @Override
    public void handleToken( Token token, HttpServletRequest request, HttpServletResponse response )
    {
        HttpSession session = request.getSession( true );
        UserAdresseFiscale userAdresseFiscale;
        try
        {
            userAdresseFiscale = MapperService.parse( getData( token ), UserAdresseFiscale.class );
        }
        catch ( Exception ex )
        {
            AppLogService.error( "Error DataClient Adresse Fiscale : " + ex.getMessage(  ), ex );
            userAdresseFiscale = new UserAdresseFiscale();
            userAdresseFiscale.setAft("BOUCHON - 5 rue cinq appartement5 Paris 75005 paris cinq");
        }

        session.setAttribute( ATTRIBUTE_USERADRESSEFISCALE, userAdresseFiscale );
        String strRedirectUrl = RedirectUtils.getViewUrl( request, FranceConnectSampleApp.VIEW_DEMARCHE_FORM );

        try {
            response.sendRedirect(strRedirectUrl);
        } catch (IOException ex) {
            AppLogService.error( "Error DataClient Adresse Fiscale send redirect: " + ex.getMessage(  ), ex );
            throw new RuntimeException(ex);
        }
    }

}
