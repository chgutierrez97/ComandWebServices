package com.ast.ejecutorws.cmd.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.AbstractCommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.ast.ejecutorws.model.Respueta;


import ar.com.osde.sujetovip.backend.services.impl.UpdateSocioVipRegistryService;
import ar.com.osde.sujetovip.backend.services.impl.UpdateSocioVipRegistryService_Service;



/**
 * Ejecutor de comandos SOAP
 * 
 * @author franco.milanese
 *
 */
public class SoapCommand extends AbstractCommandWS {

	private static final Logger LOGGER = LoggerFactory.getLogger(SoapCommand.class);

	public SoapCommand(ArgsData inputData) {
		super(inputData);
	}

	@Override
	public void execute() throws CommandWSException {
		String respon ="";
		CloseableHttpClient httpClient = null;
		if (!containsRequiredFields()) {
			throw new CommandWSException("Faltan campos requeridos");
		}
		try {
			
			httpClient = HttpClients.custom().build();
			HttpPost httpPost = new HttpPost(inData.getEndpoint().get());
			httpPost.addHeader("SOAPAction", inData.getSoapAction().get());
			httpPost.setEntity(getHttpEntity());
			
			HttpResponse response = httpClient.execute(httpPost);
			
			
			respon = EntityUtils.toString(response.getEntity());
			System.out.println(respon);
			httpClient.close();
			
			if(inData.getAuxiliar().get().equals("Ax")) {
				final Pattern pattern = Pattern.compile("errorMessage\\>(.+?)\\</errorMessage");
				Respueta flag1 = valiRespond(pattern,respon);
				 if(flag1.getFlag()) {
					    System.err.println("Cod:1");
				 }else {			 
					 final Pattern pattern2 = Pattern.compile("id\\>(.+?)\\</id"); 
					 Respueta flag2 = valiRespond(pattern2,respon);
					 if(flag2.getFlag() && isNumeric(flag2.getMensaje().trim())) {
						 final Pattern pattern3 = Pattern.compile("status\\>(.+?)\\</status"); 
						 Respueta flag3 = valiRespond(pattern3,respon);
						 if(flag3.getFlag()) {
							 System.out.println(" status");
							 if(flag3.getMensaje().equals("STARTED") || flag3.getMensaje().equals("RUNNING") ) {
								 final Matcher matcher = pattern2.matcher(respon);
									matcher.find(); 
									respon=matcher.group(1);
									//accesoWebServices(respon);
									if(inData.getEndpoint2()==null || inData.getEndpoint2().equals("")){
										
										inData.setEndpoint2(inData.getEndpoint());
									}
									
									accesoWebServices(inData.getEndpoint2().toString(), inData.getSoapAction2().toString(),inData.getNamespace().toString(), inData.getMethod().toString(),respon);
									
							 }else if(flag3.getMensaje().equals("CANCELED")){
								 System.err.println("STATUS DE PROCESO --> "+flag3.getMensaje());
							 }else if(flag3.getMensaje().equals("ERROR")){
								 System.err.println("STATUS DE PROCESO --> "+flag3.getMensaje());
							 }else{
								 System.out.println("STATUS DE PROCESO --> "+flag3.getMensaje());
							 }
						 }
					 }else {						 
						 System.err.println("Error Id esta en blanco");						 
					 } 
					 
				 }
			}
				
			
				

		} catch (UnsupportedOperationException | IllegalStateException | IOException e) {
			throw new CommandWSException(e.getMessage(), e);
		}
	}
	
	private  Boolean isNumeric(String cadena){
		if (cadena == null) {
	        return false;
	    }
		
		if (cadena.equals("")) {
	        return false;
	    }
		
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public Respueta valiRespond(Pattern pat, String texto) {
        Respueta resp = new Respueta();
        resp.setFlag(Boolean.TRUE);
		final Matcher matcher = pat.matcher(texto);
		matcher.find(); 
		try {
			resp.setMensaje(matcher.group(1));
		} catch (Exception e) {
			resp.setFlag(Boolean.FALSE);
		}
		return resp;
	}
	
	
	public void accesoWebServices(String soapEndpointUrl, String soapAction,String myNamespaceURI, String method,String Id) {
		System.out.println(Id);
		UpdateSocioVipRegistryService servicess = new UpdateSocioVipRegistryService_Service().getUpdateSocioVipRegistryServiceImplPort();		

		Boolean flag = Boolean.TRUE;
		String status="";
		
		//Id = servicess.start().getId();
		//	
		do {
			String estatus =  callSoapWebService( soapEndpointUrl,  soapAction, myNamespaceURI,  method, Id);
			
			if(estatus.equals("FINISHED") ) {
				flag = Boolean.FALSE;
				status="FINISHED"; 
				System.out.println("status : " + status + "  codigo : 0");
			} else if(estatus.equals("CANCELED")) {
				flag = Boolean.FALSE;
				status="CANCELED";
				System.out.println("status : " + status + "  codigo : 1");
			} else if(estatus.equals("ERROR")) {
				flag = Boolean.FALSE;
				status="ERROR";
				System.out.println("status : " + status + "  codigo : 1");
			} else if(estatus.equals("STARTED")) {
				status="STARTED";
				flag = Boolean.TRUE;
			} else if(estatus.equals("RUNNING")){
				status="RUNNING";
				//servicess.cancel(id);
				flag = Boolean.TRUE;
			}
			
		}while(flag);

	}

	/**
	 * Devuelve una instancia {@link HttpEntity} a partir del request, que puede ser un archivo o una trama xml.
	 * 
	 * @return una instancia de {@link HttpEntity}
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	protected HttpEntity getHttpEntity() throws FileNotFoundException, UnsupportedEncodingException {
		File file = new File(inData.getRequest().get());
		if (file.isFile()) {
			return new FileEntity(file);
		} else {
			return new StringEntity(inData.getRequest().get());
		}
	}

	
	
	
	private  String callSoapWebService(String soapEndpointUrl, String soapAction,String myNamespaceURI, String method,String valueArgum) {
		String var="";		
		try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();           
            // Enviar el Mensaje al Servidor SOAP
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction, myNamespaceURI,  method, valueArgum), soapEndpointUrl);
            System.out.println("Response SOAP Message:");           
            SOAPBody soapBody;           
            soapBody = soapResponse.getSOAPBody();
             var = soapMessageToString(soapResponse);
            final Pattern pattern3 = Pattern.compile("status\\>(.+?)\\</status"); 
            Respueta flag3 = valiRespond(pattern3,var);
			 if(flag3.getFlag()) {
				 var = flag3.getMensaje();				 
			 }else {
				 var = "";				 
			 }            
            soapResponse.writeTo(System.out);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        
        return var;
    }
    

    
    public static String soapMessageToString(SOAPMessage message) 
    {
        String result = null;

        if (message != null) 
        {
            ByteArrayOutputStream baos = null;
            try 
            {
                baos = new ByteArrayOutputStream();
                message.writeTo(baos); 
                result = baos.toString();
            } 
            catch (Exception e) 
            {
            } 
            finally 
            {
                if (baos != null) 
                {
                    try 
                    {
                        baos.close();
                    } 
                    catch (IOException ioe) 
                    {
                    }
                }
            }
        }
        return result;
    }   
    

    private static SOAPMessage createSOAPRequest(String soapAction,String myNamespaceURI,String method, String valueArgum) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        createSoapEnvelope(soapMessage, myNamespaceURI, method, valueArgum);
        MimeHeaders headers = soapMessage.getMimeHeaders();

        headers.addHeader("SOAPAction", soapAction);
        soapMessage.saveChanges();

        /* Print the request message, just for debugging purposes */

        System.out.println("Request SOAP Message:");
        soapMessage. writeTo(System.out);
        System.out.println("\n");
        return soapMessage;

    }
    
	private static void createSoapEnvelope(SOAPMessage soapMessage,String myNamespaceURI, String metodo, String valueArgum) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();

 

        String myNamespace = "ser";

        // myNamespaceURI = "http://services.sujetovip.osde.com.ar/";

 

        // SOAP Envelope

        SOAPEnvelope envelope = soapPart.getEnvelope();

        envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // SOAP Body

        SOAPBody soapBody = envelope.getBody();

        SOAPElement soapBodyElem = soapBody.addChildElement(metodo, myNamespace);

        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("arg0");

        soapBodyElem1.addTextNode(valueArgum);

    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	protected boolean containsRequiredFields() {

		return inData.getEndpoint().isPresent() && inData.getRequest().isPresent() && inData.getSoapAction().isPresent();

	}

}
