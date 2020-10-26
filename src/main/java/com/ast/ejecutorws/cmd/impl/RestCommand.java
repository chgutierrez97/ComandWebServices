package com.ast.ejecutorws.cmd.impl;

import org.springframework.web.client.RestTemplate;

import com.ast.ejecutorws.args.ArgsData;
import com.ast.ejecutorws.cmd.AbstractCommandWS;
import com.ast.ejecutorws.cmd.CommandWSException;
import com.ast.ejecutorws.model.ReturnRest;

import ar.com.osde.sujetovip.backend.services.impl.UpdateSocioVipRegistryService;
import ar.com.osde.sujetovip.backend.services.impl.UpdateSocioVipRegistryService_Service;

public class RestCommand extends AbstractCommandWS {
	private String url = "", urlStart = "", urlStatus = "", urlCancel = "";

	public RestCommand(ArgsData inputData) {
		super(inputData);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws CommandWSException {
		url = inData.getEndpoint().get();
		String[] auxUrl = url.split("start");
		url = auxUrl[0];
		urlStart = inData.getEndpoint().get();
		try {
			RestTemplate plantilla = new RestTemplate();
			ReturnRest resultado = plantilla.getForObject(urlStart, ReturnRest.class);
			System.out.println("idJop :"+resultado.getId());
			if (inData.getAuxiliar().get().equals("sts")) {				
				statusMonitor(resultado.getId());
			} else if (inData.getAuxiliar().get().equals("can")) {
				cancel(resultado.getId());
			}else {
				
				System.out.println("status :"+resultado.getStatus()+ "  codigo : 1");
			}
		} catch (Exception e) {
			String error = e.getMessage();
			System.out.println(error);
			// TODO: handle exception
		}
	}

	public void statusMonitor(String Id) {
	
		Boolean flag = Boolean.TRUE;
		urlStatus = url + Id + "/status";
		do {
			RestTemplate plantilla = new RestTemplate();
			ReturnRest resultado2 = plantilla.getForObject(urlStatus, ReturnRest.class);
			String status = resultado2.getStatus();

			if (status.equals("FINISHED")) {
				flag = Boolean.FALSE;
				System.out.println("status : " + status + "  codigo : 0");
			} else if (status.equals("CANCELED")) {
				flag = Boolean.FALSE;
				System.err.println("status : " + status + "  codigo : 1");
			} else if (status.equals("ERROR")) {
				flag = Boolean.FALSE;
				System.err.println("status : " + status + "  codigo : 1");
			} else if (status.equals("STARTED")) {
				flag = Boolean.TRUE;
			} else if (status.equals("RUNNING")) {
				flag = Boolean.TRUE;
			}

		} while (flag);

	}

	public void cancel(String Id) {
		urlCancel = url + Id + "/cancel";
		RestTemplate plantilla = new RestTemplate();
		ReturnRest resultado3 = plantilla.getForObject(urlCancel, ReturnRest.class);
		String status = resultado3.getStatus();
		
		if (status.equals("CANCELED")) {
			System.out.println("status : " + status + "  codigo : 2");
			
		}else {
			
			System.err.println("mensaje : " + resultado3.getErrorMessage());
		}
		
	}

	@Override
	protected boolean containsRequiredFields() {
		// TODO Auto-generated method stub
		return false;
	}

}
