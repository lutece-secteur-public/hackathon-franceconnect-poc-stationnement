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
package fr.paris.lutece.plugins.stationnement.web;

import fr.paris.lutece.plugins.stationnement.business.FormData;
import fr.paris.lutece.plugins.stationnement.dataclient.AdresseDataClient;
import fr.paris.lutece.plugins.stationnement.dataclient.UserDataClient;
import fr.paris.lutece.plugins.stationnement.dataclient.UserAdresse;
import fr.paris.lutece.plugins.stationnement.service.RedirectUtils;
import fr.paris.lutece.plugins.franceconnect.oidc.UserInfo;
import fr.paris.lutece.plugins.franceconnect.service.DataClientService;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;
import fr.paris.lutece.portal.util.mvc.xpage.MVCApplication;
import fr.paris.lutece.portal.util.mvc.xpage.annotations.Controller;
import fr.paris.lutece.portal.web.xpages.XPage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * This class provides a simple implementation of an XPage
 */
@Controller( xpageName = "stationnement", pageTitleI18nKey = "stationnement.xpage.stationnement.pageTitle", pagePathI18nKey = "stationnement.xpage.stationnement.pagePathLabel" )
public class FranceConnectSampleApp extends MVCApplication
{
    // VIEWS
    public static final String VIEW_HOME = "home";
    public static final String VIEW_DEMARCHE_FORM = "demarcheForm";
    public static final String VIEW_DEMARCHE_ETAPE2 = "demarcheEtape2";
    public static final String VIEW_DEMARCHE_FIN = "demarcheFin";

    // ACTIONS
    public static final String ACTION_START_DEMARCHE = "startDemarche";
    public static final String ACTION_PROCESS_FORM = "processForm";

    // TEMPLATES
    private static final String TEMPLATE_XPAGE = "/skin/plugins/stationnement/home.html";
    private static final String TEMPLATE_DEMARCHE_FORM = "/skin/plugins/stationnement/demarche-form.html";
    private static final String TEMPLATE_DEMARCHE_ETAPE2 = "/skin/plugins/stationnement/demarche-etape2.html";
    private static final String TEMPLATE_DEMARCHE_FIN = "/skin/plugins/stationnement/demarche-etape-fin.html";
    private static final String MARK_LASTNAME = "lastname";
    private static final String MARK_FIRSTNAME = "firstname";
    private static final String MARK_ADRESSE = "adresse";
    private static final String MARK_FORM_DATA = "form";
    private static final String MARK_MONTANT = "montant";
    private static final String DATACLIENT_USER = "user";
    private static final String DATACLIENT_ADRESSE = "adresse";
    private static final long serialVersionUID = 1L;
    private UserInfo _userInfo;
    private UserAdresse _userAdresse;
    private FormData _formData;

    /**
     * Returns the content of the page stationnement.
     * @param request The HTTP request
     * @return The view
     */
    @View( value = VIEW_HOME, defaultView = true )
    public XPage viewHome( HttpServletRequest request )
    {
        return getXPage( TEMPLATE_XPAGE, request.getLocale(  ) );
    }

    /**
     * Start the process by connecting to FranceConnect
     * @param request The HTTP request
     * @return Redirection
     */
    @Action( ACTION_START_DEMARCHE )
    public XPage startDemarche( HttpServletRequest request )
    {
        _userInfo = (UserInfo) request.getSession(  ).getAttribute( UserDataClient.ATTRIBUTE_USERINFO );
        _userAdresse = (UserAdresse) request.getSession(  ).getAttribute( AdresseDataClient.ATTRIBUTE_USERADRESSE );

        if ( _userInfo == null )
        {
            String strUrl = DataClientService.instance(  ).getDataClientUrl( DATACLIENT_USER );

            return redirect( request, strUrl );
        }

        if ( _userAdresse == null )
        {
            String strUrl = DataClientService.instance(  ).getDataClientUrl( DATACLIENT_ADRESSE );

            return redirect( request, strUrl );
        }


        return redirectView( request, VIEW_DEMARCHE_FORM );
    }

    /**
     * Returns the content of the page stationnement.
     * @param request The HTTP request
     * @return The view
     */
    @View( VIEW_DEMARCHE_FORM )
    public XPage viewDemarcheForm( HttpServletRequest request )
    {
        _userInfo = (UserInfo) request.getSession(  ).getAttribute( UserDataClient.ATTRIBUTE_USERINFO );

        if ( _userInfo == null )
        {
            return redirect( request, RedirectUtils.getActionUrl( request, ACTION_START_DEMARCHE ) );
        }

        Map<String, Object> model = getModel(  );
        model.put( MARK_FIRSTNAME, _userInfo.getGivenName(  ) );
        model.put( MARK_LASTNAME, _userInfo.getFamilyName(  ) );
        model.put( MARK_ADRESSE, _userAdresse );

        return getXPage( TEMPLATE_DEMARCHE_FORM, request.getLocale(  ), model );
    }

    /**
     * Process the form
     * @param request The HTTP request
     * @return Redirection
     */
    @Action( ACTION_PROCESS_FORM )
    public XPage processForm( HttpServletRequest request )
    {
        _formData = new FormData(  );
        populate( _formData, request );

        if ( !validateBean( _formData ) )
        {
            // TODO Process error
        }

        System.out.println( "Données du formulaire : " + _formData.getPrenomEnfant(  ) + " " +
            _formData.getNomEnfant(  ) );

        //TODO fetch car immatriculations
        //return redirect( request, DataClientService.instance(  ).getDataClientUrl( DATACLIENT_ADRESSE ) );
        return redirectView( request, VIEW_DEMARCHE_ETAPE2 );
    }

    /**
     * Display the view Etape2
     * @param request The HTTP request
     * @return The XPage
     */
    @View( VIEW_DEMARCHE_ETAPE2 )
    public XPage viewDemarcheEtape2( HttpServletRequest request )
    {
        Map<String, Object> model = getModel(  );
        model.put( MARK_FORM_DATA, _formData );
        _userAdresse = (UserAdresse) request.getSession(  ).getAttribute( AdresseDataClient.ATTRIBUTE_USERADRESSE );
        model.put( MARK_MONTANT, _userAdresse.getCodePostal() );

        return getXPage( TEMPLATE_DEMARCHE_ETAPE2, request.getLocale(  ), model );
    }

    /**
     * Display the view Etape FIN
     * @param request The HTTP request
     * @return The XPage
     */
    @View( VIEW_DEMARCHE_FIN )
    public XPage viewDemarcheFin( HttpServletRequest request )
    {
        return getXPage( TEMPLATE_DEMARCHE_FIN, request.getLocale(  ) );
    }
}
