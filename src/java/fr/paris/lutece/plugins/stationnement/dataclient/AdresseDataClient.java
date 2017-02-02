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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpMethodBase;

import fr.paris.lutece.plugins.franceconnect.oidc.Token;
import fr.paris.lutece.plugins.franceconnect.oidc.dataclient.AbstractDataClient;
import fr.paris.lutece.plugins.franceconnect.service.MapperService;
import fr.paris.lutece.plugins.stationnement.service.RedirectUtils;
import fr.paris.lutece.plugins.stationnement.web.FranceConnectSampleApp;
import fr.paris.lutece.portal.service.util.AppLogService;
import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import fr.paris.lutece.util.signrequest.RequestAuthenticator;


/**
 * AdresseDataClient.java
 */
public class AdresseDataClient extends AbstractDataClient
{

    public static final String ATTRIBUTE_USERCARTESGRISES = "stationnement-dc-usercartesgrises";

    @Override
    public void handleToken( Token token, HttpServletRequest request, HttpServletResponse response )
    {
        CarteGrise carteGrise;
        try
        {
            String strCarteGriseJSON = getData( token );
            carteGrise = MapperService.parse( strCarteGriseJSON, CarteGrise.class );
            carteGrise.setSource(strCarteGriseJSON);
        } catch ( Exception ex ) {
            AppLogService.error( "Error when fetching carte grise" + ex.getMessage(  ), ex );
            carteGrise = new CarteGrise();
            carteGrise.setAdresse("BOUCHON: 4 rue bouchon");
            carteGrise.setTPpNom("BOUCHON: DELL");
            carteGrise.setVNumeroImmatriculation("BOUCHON: AD-711-AF");
            carteGrise.setSource("BOUCHON: source");
        }

        HttpSession session = request.getSession( true );
        session.setAttribute( ATTRIBUTE_USERCARTESGRISES, carteGrise );

        try {
            String strRedirectUrl = RedirectUtils.getViewUrl( request, FranceConnectSampleApp.VIEW_DEMARCHE_ETAPE2 );
            response.sendRedirect( strRedirectUrl );
        }
        catch ( IOException ex )
        {
            AppLogService.error( "Error DataClient Adresse redirect : " + ex.getMessage(  ), ex );
        }
    }

    public String getData( Token token )
    {
        String strResponse = null;
        HttpAccess httpAccess = new HttpAccess(  );

        String strUrl = getDataServerUri();

        try
        {
            RequestAuthenticator authenticator = new SIVTokenAuthenticator( token.getAccessToken(  ) );
            strResponse = httpAccess.doGet( strUrl, authenticator, null );
            _logger.debug( "FranceConnect response : " + strResponse );
        }
        catch ( HttpAccessException ex )
        {
            _logger.error( "OAuth Login Error" + ex.getMessage(  ), ex );
        }

        return strResponse;
    }

    //TODO remove this when the http header is "Authorization: Bearer XXX"
    //currently, it is "Authorization: AccessToken XXX"
    //Also when the token is the one from franceconnect, not hardcoded
    class SIVTokenAuthenticator implements RequestAuthenticator
{
    private String _strAccessToken;

    /**
     * Constructor
     * @param strAccessToken The access token value
     */
    public SIVTokenAuthenticator( String strAccessToken )
    {
        _strAccessToken = strAccessToken;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isRequestAuthenticated( HttpServletRequest request )
    {
        return false; // not used
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void authenticateRequest( HttpMethodBase hmb, List<String> list )
    {
        hmb.addRequestHeader( "Authorization", String.format( "AccessToken %s", "895fae591ccae777094931e269e46447" ) );
        hmb.addRequestHeader( "Authorization", String.format( "Bearer %s", "895fae591ccae777094931e269e46447" ) );
    }
}
}
