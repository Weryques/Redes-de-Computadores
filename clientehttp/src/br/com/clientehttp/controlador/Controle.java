/**
 * 
 */
package br.com.clientehttp.controlador;

import br.com.clientehttp.modelo.ClienteHTTP;
import br.com.clientehttp.visao.InterfaceNavegador;

/**
 * @author weryquessantos
 *
 */
public class Controle {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		/** Estância a classe InterfaceNavegador*/
		InterfaceNavegador navegadorInterface = new InterfaceNavegador();

		/** Inicia a interface*/
		navegadorInterface.iniciarInterface();
	}

	public String requisitaConteudo(String urlBruta) throws Exception{
		/** Estância a classe ClienteHTTP*/
		ClienteHTTP clienteHTTP = new ClienteHTTP();
		
		/** Requisita o conteúdo */
		String conteudo = clienteHTTP.enviarGet(urlBruta);
		
		return conteudo;
	}
}
